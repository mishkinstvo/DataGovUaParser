package com.mishkinstvo;

import com.mishkinstvo.exceptions.FaultyItemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class Parser {
	
	private OptionsStorage optionsStorage;
	private Writer outputStream;
	
	private Parser() {}
	
	/* Parser strategy */
	public static int perform(OptionsStorage optionsStorage) throws IOException, InterruptedException {
		SINGLETON.setOptionsStorage(optionsStorage);
		SINGLETON.writeHeader();
		return SINGLETON.parseAll();
	}
	
	public void setOptionsStorage(OptionsStorage optionsStorage) throws IOException {
		this.optionsStorage = optionsStorage;
		this.outputStream = new FileWriter(optionsStorage.getOutputPath());
	}
	
	private void writeHeader() throws IOException {
		outputStream.write(HEADER_TEXT);
	}
	
	private int parseAll() throws IOException, InterruptedException {
		LOG.debug("Main parse loop was started");
		
		int total = 0;
		int page = optionsStorage.getStartingPage();
		
		for (int parsed = 1; parsed > 0; page++) {
			parsed = parsePage(page);
			total += parsed;
		}
		
		LOG.debug("Main parse loop was finished. Total elements parsed: {}", total);
		return total;
	}
	
	private int parsePage(int page) throws IOException {
		LOG.debug("Parsing of page {} was started", page);
		
		NetworkErrorHandler<List<String>> datastoreHandler = new NetworkErrorHandler<>(
			new PageIdsFetcher(page, optionsStorage), optionsStorage
		);
		
		List<String> ids = ids = datastoreHandler.handle();
		
		for (String id : ids) {
			NetworkErrorHandler<Dataset> datasetHandler = new NetworkErrorHandler<>(
				new DatasetFetcher(id, optionsStorage), optionsStorage
			);
			
			Dataset dataset = null;
			
			try {
				dataset = datasetHandler.handle();
			}
			catch (FaultyItemException e) {
				continue;
			}
			
			outputStream.append(dataset.toString());
			outputStream.flush();
		}
		
		LOG.debug("Parsing of page {} was finished. Total elements parsed: {}", page, ids.size());
		return ids.size();
	}
	
	final static Logger LOG = LogManager.getLogger(Parser.class);
	
	public static final Parser SINGLETON = new Parser();
	public static final String HEADER_TEXT = "\"ID\",\"Revision ID\",\"Last Revision ID\",\"Language\",\"Category\",\"Responsible Person\",\"Responsible Person Email\",\"Keywords\",\"Created At\",\"Updated At\",\"Dataset name\",\"Description\",\"File name\",\"File format\",\"File url\"" + System.lineSeparator();
}

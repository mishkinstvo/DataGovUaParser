package com.mishkinstvo.uadataparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

public final class PageIdsFetcher implements Erroneous<List<String>> {
	
	private OptionsStorage optionsStorage;
	private int page;
	
	public PageIdsFetcher(int page, OptionsStorage optionsStorage) {
		this.optionsStorage = optionsStorage;
		this.page = page;
	}
	
	public List<String> execute() throws Exception {
		LOG.debug("Parsing page with url {} was started", optionsStorage.getPageUrl(page));
		
		List<String> ids = new LinkedList<>();
		
		Connection.Response response = Jsoup.connect(optionsStorage.getPageUrl(page))
			.userAgent(optionsStorage.getUserAgent())
			.timeout(optionsStorage.getConnectionTimeout())
			.ignoreContentType(true)
			.followRedirects(true)
			.execute();
		
		Document doc = response.parse();
		Elements datasetElements = doc.select(optionsStorage.getSelector());
		
		for (Element element : datasetElements) {
			String unformatted = element.attr("href").split("/")[2];
			String trimmed = unformatted.trim().substring(0, 36);
			LOG.debug("Datastore ID was fetched. Unformatted: {}, trimmed: {}", unformatted, trimmed);
			ids.add(trimmed);
		}
		
		LOG.debug("Parsing page with url {} was successfully finished. Parsed {} ids", optionsStorage.getPageUrl(page), ids.size());
		
		return ids;
	}
	
	final static Logger LOG = LogManager.getLogger(PageIdsFetcher.class);
}

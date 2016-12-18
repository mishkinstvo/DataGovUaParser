package com.mishkinstvo.uadataparser;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Init {
	private OptionsStorage optionsStorage;
	
	public Init(String args[]) {
		try {
			optionsStorage = new OptionsStorage(args);
		}
		catch (ParseException e) {
			LOG.error("Command line arguments parser error");
			System.exit(1);
		}
		
		if (optionsStorage.isHelp()) {
			optionsStorage.printHelp();
			return;
		}
		
		LOG.debug("Application execution started");
		LOG.debug("Starting page: {}", optionsStorage.getStartingPage());
		LOG.debug("Ending page: {}", optionsStorage.getEndingPage());
		LOG.debug("URL: {}", optionsStorage.getUrl());
		LOG.debug("Output CSV path: {}", optionsStorage.getOutputPath());
		
		try {
			Parser.perform(optionsStorage);
		}
		catch (IOException | InterruptedException e) {
			LOG.error("Parser error");
			System.exit(2);
		}
		
		LOG.debug("Application execution finished");
	}
	
	public static void main(String args[]) throws Exception {
		new Init(args);
	}
	
	final static Logger LOG = LogManager.getLogger(Init.class);
}

package com.mishkinstvo;

import org.apache.commons.cli.*;

public class OptionsStorage {
	
	public CommandLineParser parser;
	public CommandLine cmd;
	
	public OptionsStorage(String args[]) throws ParseException {
		parser = new DefaultParser();
		cmd = parser.parse(options, args);
	}
	
	public String getUrl() {
		return getOption(CommandLineOptions.URL);
	}
	
	public String getSelector() {
		return getOption(CommandLineOptions.SELECTOR);
	}
	
	public String getUserAgent() {
		return getOption(CommandLineOptions.USER_AGENT);
	}
	
	public int getConnectionTimeout() {
		return Integer.parseInt(getOption(CommandLineOptions.CONNECTION_TIMEOUT));
	}
	
	public Integer getStartingPage() {
		return Integer.parseInt(getOption(CommandLineOptions.STARTING_PAGE));
	}
	
	public String getPageUrl(int page) {
		StringBuilder sb = new StringBuilder(getUrl());
		sb.append(page);
		return sb.toString();
	}
	
	public String getDatasetUrl(String dsId) {
		StringBuilder sb = new StringBuilder(getOption(CommandLineOptions.DATASET_URL));
		sb.append(dsId);
		return sb.toString();
	}
	
	public String getOutputPath() {
		return getOption(CommandLineOptions.OUTPUT);
	}
	
	public Integer getDeepSleepLimit() {
		return Integer.parseInt(getOption(CommandLineOptions.DEEP_SLEEP_LIMIT));
	}
	
	public long getDeepSleepTime() {
		return 60 * 1000 * Integer.parseInt(getOption(CommandLineOptions.DEEP_SLEEP_TIME));
	}
	
	public long getMinMildSleepTime() {
		return Integer.parseInt(getOption(CommandLineOptions.MIN_MILD_SLEEP_TIME));
	}
	public long getMaxMildSleepTime() {
		return Integer.parseInt(getOption(CommandLineOptions.MAX_MILD_SLEEP_TIME));
	}
	
	public boolean isHelp() {
		return hasOption(CommandLineOptions.HELP);
	}
	
	public void printHelp() {
		HelpFormatter hf = new HelpFormatter();
		hf.printHelp(CALL_CMD, options, true);
	}
	
	public boolean hasOption(CommandLineOptions option) {
		return cmd.hasOption(option.getShortCmd());
	}
	
	public String getOption(CommandLineOptions option) {
		if (hasOption(option)) {
			return cmd.getOptionValue(option.getShortCmd());
		}
		return option.getDefaultValue();
	}
	
	public static final String CALL_CMD = "java -jar <app_name>.jar";
	
	public static final Options options = new Options() {{
		for (CommandLineOptions opt : CommandLineOptions.values()) {
			addOption(opt.toOption());
		}
	}};
}

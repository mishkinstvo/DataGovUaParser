package com.mishkinstvo;

import org.apache.commons.cli.Option;

public enum CommandLineOptions {
	STARTING_PAGE("sp", "starting-page", true, "Number of the page, from which parsing will be started", "0"),
	ENDING_PAGE("ep", "ending-page", true, "Number of the page, which will be parsed last", "-1"),
	SELECTOR("s", "selector", true, "DOM path to dataset HTML element", "div.top.views-fieldset div.views-field-field-big-title a"),
	USER_AGENT("ua", "user-agent", true, "User agent, specified in connection header", "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"),
	CONNECTION_TIMEOUT("ct", "connection-timeout", true, "HTTP connection timeout, milliseconds", "12000"),
	OUTPUT("o", "output", true, "Path to the output CSV file (including *.csv extension)", "output.csv"),
	URL("u", "url", true, "Base parsing URL", "http://data.gov.ua/datasets/?page="),
	DATASET_URL("dsu", "dataset-url", true, "Dataset passport base URL", "http://data.gov.ua/view-dataset/dataset?dataset-id="),
	HELP("h", "help", false, "Output this help message", null),
	MIN_MILD_SLEEP_TIME("minmst", "min-mild-sleep-time", true, "Minimum mild sleep time, milliseconds", "3000"),
	MAX_MILD_SLEEP_TIME("maxmst", "max-mild-sleep-time", true, "Maximum mild sleep time, milliseconds", "5000"),
	DEEP_SLEEP_TIME("dst", "deep-sleep-time", true, "Deep sleep time, minutes", "60"),
	DEEP_SLEEP_LIMIT("dsl", "deep-sleep-limit", true, "Possible amount of errors before deep sleep invocation", "15");
	
	CommandLineOptions(String shortCmd, String longCmd, boolean hasArg, String description, String defValue) {
		this.shortCmd = shortCmd;
		this.longCmd = longCmd;
		this.hasArg = hasArg;
		this.description = description;
		this.defaultValue = defValue;
	}
	
	public Option toOption() {
		if (option == null) {
			option = new Option(getShortCmd(), getLongCmd(), isHasArg(),getDescription());
		}
		return option;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public String getShortCmd() {
		return shortCmd;
	}
	
	public String getLongCmd() {
		return longCmd;
	}
	
	public boolean isHasArg() {
		return hasArg;
	}
	
	public String getDescription() {
		return description;
	}
	
	private String shortCmd;
	private String longCmd;
	private boolean hasArg;
	private String description;
	private String defaultValue;
	private Option option = null;
}

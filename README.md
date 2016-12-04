# Data.gov.ua parser [![Build Status](https://travis-ci.org/mishkinstvo/DataGovUaParser.svg?branch=master)](https://travis-ci.org/mishkinstvo/DataGovUaParser)

## Table of Contents
* [How to Run it](#how-to-run-it)
	* Command-Line Options
		* [`--help` or `-h`] - displays help message
		* [`--starting-page` or `-sp`] - sets the starting page. Default value: `0`
		* [`--ending-page` or `-ep`] - sets the ending page. Default value: `-1`
		* [`--selector` or `-s`] - set the selector. Default value: `div.top.views-fieldset div.views-field-field-big-title a`
		* [`--user-agent` or `-ua`] - sets the user agent string. Default value: `Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6`
		* [`--connection-timeout` or `-ct`] - sets the connection time limit in milliseconds. Default value: `12000`
		* [`--output` or `-o`] - sets the path to output CSV data file. Default value: `output.csv`
		* [`--url` or `-u`] - sets the base url to datastore. Default value: `http://data.gov.ua/datasets/?page=`
		* [`--dataset-url` or `-du`] sets the base url to dataset-related info. Default value: `http://data.gov.ua/view-dataset/dataset?dataset-id=`
		* [`--min-mild-sleep-time` (`-minmst`) and `--max-mild-sleep-time` (`-maxmst`)] - set the boundaries for generator of mild sleep time in milliseconds. Default values: `3000` and `5000` respectively.
		* [`--deep-sleep-limit` or `-dsl`] - sets the limit of exception before parser goes to deep sleep. Default value: `15`
		* [`--deep-sleep-time` or `-dst`] - deep sleep time in minutes. Default value: `60`
* [How to Build it](#how-to-build-it)

## How to Run it
You need to have `JRE >= 1.8` installed on your machine. The minimum required command to execute the parser is:
``` console
java -jar <app_name>.jar
```
It is highly advisable to customize the parsing cycle by using different command line options:
``` console
usage: java -jar <app_name>.jar [-ct <arg>] [-dsl <arg>] [-dst <arg>]
       [-dsu <arg>] [-h] [-maxmst <arg>] [-minmst <arg>] [-o <arg>] [-s
       <arg>] [-sp <arg>] [-u <arg>] [-ua <arg>]
```

## How to Build it
You need to have `JDK >= 1.8` latest version of `maven` installed on your machine. To build the artifact, just execute `clean` and `package` goals from the sources root:
``` console
mvn clean package
```
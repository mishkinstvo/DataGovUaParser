package com.mishkinstvo.uadataparser;

import com.mishkinstvo.uadataparser.exceptions.DdosSecurityException;
import com.mishkinstvo.uadataparser.exceptions.FaultyItemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.HttpStatusException;

import java.util.concurrent.ThreadLocalRandom;

public class NetworkErrorHandler<T> {
	private Erroneous<T> erroneousProcess;
	private OptionsStorage optionsStorage;
	
	public NetworkErrorHandler(Erroneous<T> erroneousProcess, OptionsStorage optionsStorage) {
		this.erroneousProcess = erroneousProcess;
		this.optionsStorage = optionsStorage;
	}
	
	public T handle() {
		T result = null;
		int thrownExceptionCount = 0;
		
		LOG.debug("Network Handler loop init");
		
		do {
			try {
				result = erroneousProcess.execute();
				LOG.debug("Network handler fetching was successful");
			}
			catch (Exception ex) {
				if (ex instanceof HttpStatusException) {
					HttpStatusException httpEx = (HttpStatusException) ex;
					
					switch (httpEx.getStatusCode()) {
						case 500:
							LOG.warn("Skipping item parsing, it is probably faulty");
							throw new FaultyItemException();
						case 403:
							LOG.warn("Server DDoS security is triggered. Further execution of the application is impossible.");
							throw new DdosSecurityException();
					}
				}
				
				LOG.debug(
					"Network handler fetching error No. {}: {}", ++thrownExceptionCount, ex.getClass()
				);
				
				if (thrownExceptionCount == optionsStorage.getDeepSleepLimit()) {
					LOG.error(
						"Network handler DDoS bypass ({} requests failed) - initiating deep sleep for {} min",
						optionsStorage.getDeepSleepLimit(),
						optionsStorage.getDeepSleepLimit() / (60 * 1000)
					);
					sleep(optionsStorage.getDeepSleepLimit());
					thrownExceptionCount = 0;
				}
				
				result = null;
			}
			finally {
				long sleepTime = ThreadLocalRandom.current().nextLong(optionsStorage.getMinMildSleepTime(), optionsStorage.getMaxMildSleepTime());
				LOG.warn("Time for mild sleep ({} msec)", sleepTime);
				sleep(sleepTime);
			}
		}
		while(result == null);
		 
		LOG.debug("Network Handler loop completed");
		
		return result;
	}
	
	private static void sleep(long time) {
		try {
			Thread.sleep(time);
		}
		catch (InterruptedException e) {
			LOG.error("Critical error! Thread sleep was interrupted. Exiting...");
			System.exit(1);
		}
	}
	
	final static Logger LOG = LogManager.getLogger(NetworkErrorHandler.class);
}

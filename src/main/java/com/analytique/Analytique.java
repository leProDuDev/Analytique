package com.analytique;

import com.analytique.rest.RestManager;
import com.analytique.storage.LogsStorage;

public class Analytique {
	
	private static Analytique instance;
	
	private final LogsStorage logsStorage;
	private final RestManager restManager;
	
	public Analytique() {
		
		logsStorage = new LogsStorage();
		restManager = new RestManager();
		
		instance = this;
		
	}
	
	public LogsStorage getLogsStorage() {
		
		return logsStorage;
		
	}
	
	public RestManager getRestManager() {
		
		return restManager;
		
	}
	
	public static Analytique getInstance() {
		
		return instance;
		
	}

}

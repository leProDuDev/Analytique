package com.analytique.storage;

public class TrafficHTTP {
	
	private final String ip;
	private final String userAgent;
	private final String url;
	
	public TrafficHTTP(String ip, String userAgent, String url) {
		
		this.ip = ip;
		this.userAgent = userAgent;
		this.url = url;
		
	}

	public String getIp() {
		
		return ip;
		
	}

	public String getUserAgent() {
		
		return userAgent;
		
	}

	public String getUrl() {
		
		return url;
		
	}

}

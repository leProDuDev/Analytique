package com.analytique.rest;

import com.analytique.Analytique;
import com.analytique.storage.TrafficHTTP;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class TrafficRest implements Handler<RoutingContext>  {
	
	public TrafficRest(Router router) {
		
		router.route(HttpMethod.PUT, "/traffic/add/:ip/:useragent/:url").handler(this);
		
	}

	@Override
	public void handle(RoutingContext event) {
		
		try {
			
			String ip = event.request()
					.getParam("ip");
			String userAgent = event.request()
					.getParam("useragent");
			String url = event.request().getParam("url");
			
			TrafficHTTP traffic = new TrafficHTTP(ip, userAgent, url);
			Analytique.getInstance().getLogsStorage().getTrafficList().add(traffic);
			
			JsonObject success = new JsonObject();
            success.add("success", new JsonPrimitive("is good !"));
			
			event.response().setStatusCode(200);
			event.response().end(success.toString());
			event.response().close();
			
		} catch (Exception e) {
			
			sendError(e.getMessage(), event);
			
		}
		
	}
	
	private void sendError(String error, RoutingContext event) {
		
		JsonObject errorJson = new JsonObject();
		errorJson.add("error", new JsonPrimitive(error));
		
		event.response().setStatusCode(400);
		event.response().end(errorJson.toString());
		
	}

}

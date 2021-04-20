package com.analytique.rest;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class RestManager {
	
	private final Vertx vertx;
	private final HttpServer server;
	private final Router router;
	private final Router apiRouter;
	
	public RestManager() {
		
		vertx = Vertx.vertx();
		
		server = vertx.createHttpServer();
		
		router = Router.router(vertx);
		apiRouter = Router.router(vertx);
		
		router.mountSubRouter("/analyse", apiRouter);
		
		apiRouter.route().handler(routingContext -> {
			
			System.out.println("REST | Request: " + routingContext.request().absoluteURI());
			
			routingContext.response().putHeader("Server", "AnalyseV1");
			routingContext.response().putHeader("Content-Type", "application/json;charset=UTF-8");
			routingContext.next();
			
		});
		
		new TrafficRest(apiRouter);
		
		server.requestHandler(router::accept);
		server.listen(1567);
		
	}

}

package com.globant.jabsorb;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jabsorb.JSONRPCBridge;

import com.globant.jabsorb.spring.SpringServiceFinder;

public class JabsorbExporterContextListener implements ServletContextListener {

	private ServiceFinder serviceFinder;

	public void contextInitialized(ServletContextEvent sce) {
		final JSONRPCBridge bridge = findBridge();
		buildServiceFinder();
		registerServices(bridge, sce.getServletContext());
	}

	private void registerServices(final JSONRPCBridge bridge,
			ServletContext context) {
		serviceFinder.registerServices(bridge, context);
	}

	private void buildServiceFinder() {
		this.serviceFinder = new SpringServiceFinder();
	}

	private JSONRPCBridge findBridge() {
		return JSONRPCBridge.getGlobalBridge();
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

}

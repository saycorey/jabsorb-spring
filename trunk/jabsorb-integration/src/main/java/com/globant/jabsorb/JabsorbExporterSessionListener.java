package com.globant.jabsorb;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.jabsorb.JSONRPCBridge;

import com.globant.jabsorb.spring.SpringServiceFinder;

/**
 * Servlet HttpSessionListener that exposes services like jabsorb services.
 * 
 * @author julian.gutierrez
 *
 */
public final class JabsorbExporterSessionListener implements HttpSessionListener {

	private static final String JABSORB_BRIDGE_SESSION_KEY = "JSONRPCBridge";

	private ServiceFinder serviceFinder;

	public void sessionCreated(final HttpSessionEvent se) {
		final HttpSession session = getSession(se);
		final JSONRPCBridge bridge = createBridge();
		buildServiceFinder();
		registerServices(session.getServletContext(), bridge);
		registerBridge(session, bridge);
	}

	private void registerServices(final ServletContext servletContext,
			final JSONRPCBridge bridge) {
		serviceFinder.registerServices(bridge, servletContext);
	}

	private void buildServiceFinder() {
		this.serviceFinder = new SpringServiceFinder();
	}

	private void registerBridge(final HttpSession session, final JSONRPCBridge bridge) {
		session.setAttribute(JABSORB_BRIDGE_SESSION_KEY, bridge);
	}

	private JSONRPCBridge createBridge() {
		final JSONRPCBridge bridge = new JSONRPCBridge();
		return bridge;
	}

	private HttpSession getSession(final HttpSessionEvent se) {
		return se.getSession();
	}

	public void sessionDestroyed(final HttpSessionEvent se) {
		//Do nothing.
	}

}

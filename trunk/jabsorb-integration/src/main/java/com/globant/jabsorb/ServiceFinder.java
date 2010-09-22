package com.globant.jabsorb;

import javax.servlet.http.HttpSession;

import org.jabsorb.JSONRPCBridge;

/**
 * Interface for jabsorb services discovery and registration.
 * 
 * @author julian.gutierrez
 *
 */
public interface ServiceFinder {

	/**
	 * @param bridge
	 * 		Jabsorb JSON RPC bridge.
	 * @param httpSession
	 * 		HTTP session.
	 */
	void registerServices(JSONRPCBridge bridge, HttpSession httpSession);

}
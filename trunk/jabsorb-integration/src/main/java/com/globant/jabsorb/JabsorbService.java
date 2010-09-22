package com.globant.jabsorb;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that an annotated Spring bean will be exported like an jabsorb service.
 * 
 * @author julian.gutierrez
 *
 */
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JabsorbService {

	/**
	 * The service alias. 
	 * 
	 * @return
	 */
	String getAlias();

	/**
	 * The exported service interface.
	 * 
	 * @return
	 */
	Class<?> getInterface();
	
}

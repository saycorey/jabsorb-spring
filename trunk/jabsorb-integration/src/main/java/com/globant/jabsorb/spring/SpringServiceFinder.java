package com.globant.jabsorb.spring;

import javax.servlet.ServletContext;

import org.jabsorb.JSONRPCBridge;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.globant.jabsorb.JabsorbService;
import com.globant.jabsorb.ServiceFinder;

/**
 * Spring-aware service finder.
 * 
 * @author julian.gutierrez
 *
 */
public final class SpringServiceFinder implements ServiceFinder {

	public void registerServices(final JSONRPCBridge bridge, final ServletContext servletContext) {
		final WebApplicationContext ctx = findSpringContext(servletContext);
		for (String beanName : ctx.getBeanDefinitionNames()) {
			final Class<?> beanClass = ctx.getType(beanName);
			if (isService(beanClass)) {
				registerBean(bridge, ctx, beanName, beanClass);
			}
		}
	}

	private void registerBean(final JSONRPCBridge bridge,
			final WebApplicationContext ctx, String beanName,
			final Class<?> beanClass) {
		final JabsorbService beanMetadata = beanClass
				.getAnnotation(JabsorbService.class);
		final String serviceAlias = beanMetadata.getAlias();
		final Class<?> serviceInterface = beanMetadata.getInterface();
		final Object beanService = ctx.getBean(beanName);
		bridge.registerObject(serviceAlias, beanService,
				serviceInterface);
	}

	private boolean isService(final Class<?> beanClass) {
		return beanClass.isAnnotationPresent(JabsorbService.class);
	}

	private WebApplicationContext findSpringContext(final ServletContext servletContext) {
		return WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}

}

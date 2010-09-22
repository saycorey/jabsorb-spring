package com.globant.jabsorb.spring;

import javax.servlet.http.HttpSession;

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

	public void registerServices(final JSONRPCBridge bridge, final HttpSession httpSession) {
		final WebApplicationContext ctx = findSpringContext(httpSession);
		for (String beanName : ctx.getBeanDefinitionNames()) {
			final Class<?> beanClass = ctx.getType(beanName);
			if (beanClass.isAnnotationPresent(JabsorbService.class)) {
				final JabsorbService beanMetadata = beanClass
						.getAnnotation(JabsorbService.class);
				final String serviceAlias = beanMetadata.getAlias();
				final Class<?> serviceInterface = beanMetadata.getInterface();
				final Object beanService = ctx.getBean(beanName);
				bridge.registerObject(serviceAlias, beanService,
						serviceInterface);
			}
		}
	}

	private WebApplicationContext findSpringContext(final HttpSession session) {
		return WebApplicationContextUtils.getWebApplicationContext(session
				.getServletContext());
	}

}

package com.concretepage.config;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/jax/*"}, initParams = {
	@WebInitParam(name = "com.sun.jersey.config.property.packages",
            value = "com.concretepage.controller"),
	@WebInitParam(name = "com.sun.jersey.config.property.JSPTemplatesBasePath",
    value = "/WEB-INF/view"),
	@WebInitParam(name = "com.sun.jersey.config.property.WebPageContentRegex",
    value = "/resources/.*"),
	@WebInitParam(name = "com.sun.jersey.api.json.POJOMappingFeature",
    value = "true")
	})
public class JerseyServlet extends SpringServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
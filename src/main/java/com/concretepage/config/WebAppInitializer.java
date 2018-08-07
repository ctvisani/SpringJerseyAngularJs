package com.concretepage.config;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class};
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	    super.onStartup(servletContext);
	    servletContext.addFilter("myTestFilter", DelegatingFilterProxy.class)
	                  .addMappingForUrlPatterns(null, false, "/*");
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}

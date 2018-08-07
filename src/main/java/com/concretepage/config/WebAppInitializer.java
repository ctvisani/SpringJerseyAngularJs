package com.concretepage.config;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.concretepage.controller.MyFilter;
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class};
	}

//	@Override
//	protected Filter[] getServletFilters() {
//	      DelegatingFilterProxy filterProxy = new DelegatingFilterProxy();
//	      filterProxy.setBeanName("myTestFilter");
////	      filterProxy.setTargetBeanName("myTestFilter");
//	      return new Filter[]{filterProxy};
//	}

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

package com.concretepage.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component("myTestFilter")
public class MyFilter implements Filter {

  @Override
  public void init (FilterConfig filterConfig) throws ServletException {
	  
  }
  
  @Override
  @PersonTx
  public void doFilter (ServletRequest request, ServletResponse response,
                        FilterChain chain)
            throws IOException, ServletException {
	  
      HttpServletRequest req = (HttpServletRequest) request;
      String url = req.getRequestURL().toString();
      System.out.println("-- In MyFilter --" + url);
      chain.doFilter(request, response);
  }
  
  @Override
  public void destroy () {
      
  }
}
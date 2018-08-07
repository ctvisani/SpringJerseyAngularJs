package com.concretepage.controller;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonValue;

@JsonAutoDetect
public class JsonWrapper {
	private String url;
	private Object model;

	public JsonWrapper(String url, Object model) {
		this.url = url;
		this.model = model;
	}

	public JsonWrapper(HttpServletRequest request, Object model) {
		this(request.getRequestURL() +
				(request.getQueryString() != null ? "?" +
						request.getQueryString() : ""), model);
	}

	public String getUrl() {
		return url;
	}

	@JsonValue
	public Object getModel() {
		return model;
	}
}

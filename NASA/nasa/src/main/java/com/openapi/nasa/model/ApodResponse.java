package com.openapi.nasa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApodResponse {
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("explanation")
	private String explanation;
	
	
	@JsonProperty("hdurl")
	private String hdurl;
	
	
	@JsonProperty("media_type")
	private String mediatype;
	
	
	@JsonProperty("service_version")
	private String serviceversion;
	
	
	@JsonProperty("title")
	private String title;
	
	
	@JsonProperty("url")
	private String url;


	public ApodResponse() {
		super();
	}


	public ApodResponse(String date, String explanation, String hdurl, String mediatype, String serviceversion,
			String title, String url) {
		super();
		this.date = date;
		this.explanation = explanation;
		this.hdurl = hdurl;
		this.mediatype = mediatype;
		this.serviceversion = serviceversion;
		this.title = title;
		this.url = url;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getExplanation() {
		return explanation;
	}


	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}


	public String getHdurl() {
		return hdurl;
	}


	public void setHdurl(String hdurl) {
		this.hdurl = hdurl;
	}


	public String getMediatype() {
		return mediatype;
	}


	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}


	public String getServiceversion() {
		return serviceversion;
	}


	public void setServiceversion(String serviceversion) {
		this.serviceversion = serviceversion;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
	

}

package com.openapi.nasa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "apod")
public class NasaApod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	
	@Column(name = "copyright")
	@Size(min = 1,message = "is required")
	private String copyright;
	
	
	
	@NotNull(message = "is required")
	@Size(min = 1,message = "is required")
	@Column(name = "date")
	private String date;
	
	
	@Column(columnDefinition = "TEXT")
	@Lob
	@NotNull(message = "is required")
	@Size(min = 1,message = "is required")
	private String explanation;
	
	
	
	@Size(min = 1,message = "is required")
	@Column(name = "hdurl")
	private String hdurl;
	
	
	@NotNull(message = "is required")
	@Size(min = 1,message = "is required")
	@Column(name = "title")
	private String title;
	
	
	@NotNull(message = "is required")
	@Size(min = 1,message = "is required")
	@Column(name = "url")
	private String url;

	public NasaApod() {
		super();
	}


	public NasaApod(int id, String copyright, String date, String explanation, String hdurl, String title, String url) {
		super();
		this.id = id;
		this.copyright = copyright;
		this.date = date;
		this.explanation = explanation;
		this.hdurl = hdurl;
		this.title = title;
		this.url = url;
	}
	
	
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCopyright() {
		return copyright;
	}


	public void setCopyright(String copyright) {
		this.copyright = copyright;
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


	@Override
	public String toString() {
		return "NasaApod [id=" + id + ", copyright=" + copyright + ", date=" + date + ", explanation=" + explanation
				+ ", hdurl=" + hdurl + ", title=" + title + ", url=" + url + "]";
	}

	
	
	
}

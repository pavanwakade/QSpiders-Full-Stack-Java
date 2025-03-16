package com.openapi.nasa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarsRoverPhoto {

	@JsonProperty("id")
	private long id;
	
	
	@JsonProperty("sol")
	private long sol;
	
	
	@JsonProperty("camera")
	private MarseRoverCamera MarseRoverCamera;
	
	
	@JsonProperty("img_src")
	private String imageSource;
	
	
	@JsonProperty("earth_date")
	private String  earthDate;
	
	
	@JsonProperty("rover")
	private MarsRover MarsRover;

	
	public MarsRoverPhoto() {
		super();
	}

	public MarsRoverPhoto(long id, long sol, com.openapi.nasa.model.MarseRoverCamera marseRoverCamera,
			String imageSource, String earthDate, com.openapi.nasa.model.MarsRover marsRover) {
		super();
		this.id = id;
		this.sol = sol;
		MarseRoverCamera = marseRoverCamera;
		this.imageSource = imageSource;
		this.earthDate = earthDate;
		MarsRover = marsRover;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSol() {
		return sol;
	}

	public void setSol(long sol) {
		this.sol = sol;
	}

	public MarseRoverCamera getMarseRoverCamera() {
		return MarseRoverCamera;
	}

	public void setMarseRoverCamera(MarseRoverCamera marseRoverCamera) {
		MarseRoverCamera = marseRoverCamera;
	}

	public String getImageSource() {
		return imageSource;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	public String getEarthDate() {
		return earthDate;
	}

	public void setEarthDate(String earthDate) {
		this.earthDate = earthDate;
	}

	public MarsRover getMarsRover() {
		return MarsRover;
	}

	public void setMarsRover(MarsRover marsRover) {
		MarsRover = marsRover;
	}
	
	
	
	
}

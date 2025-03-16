package com.openapi.nasa.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarsRover {

	@JsonProperty("id")
	private int id;
	
	
	@JsonProperty("name")
	private String roverName;
	
	
	@JsonProperty("landing_date")
	private int roverLandingDate;
	
	
	@JsonProperty("launch_date")
	private String roverLaunchgDate;
	
	
	@JsonProperty("status")
	private String roverStatus;
	
	@JsonProperty("max_sol")
	private long maxSol;
	
	
	
	@JsonProperty("max_date")
	private String maxDate;
	
	
	
	@JsonProperty("total_photos")
	private long totalPhotos;
	
	
	@JsonProperty("cameras")
	private List<MarseRoverCamera> roverCameras;

	
	
	

	public MarsRover() {
		super();
	}

	
	
	

	public MarsRover(int id, String roverName, int roverLandingDate, String roverLaunchgDate, String roverStatus,
			long maxSol, String maxDate, long totalPhotos, List<MarseRoverCamera> roverCameras) {
		super();
		this.id = id;
		this.roverName = roverName;
		this.roverLandingDate = roverLandingDate;
		this.roverLaunchgDate = roverLaunchgDate;
		this.roverStatus = roverStatus;
		this.maxSol = maxSol;
		this.maxDate = maxDate;
		this.totalPhotos = totalPhotos;
		this.roverCameras = roverCameras;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRoverName() {
		return roverName;
	}


	public void setRoverName(String roverName) {
		this.roverName = roverName;
	}


	public int getRoverLandingDate() {
		return roverLandingDate;
	}


	public void setRoverLandingDate(int roverLandingDate) {
		this.roverLandingDate = roverLandingDate;
	}


	public String getRoverLaunchgDate() {
		return roverLaunchgDate;
	}


	public void setRoverLaunchgDate(String roverLaunchgDate) {
		this.roverLaunchgDate = roverLaunchgDate;
	}


	public String getRoverStatus() {
		return roverStatus;
	}


	public void setRoverStatus(String roverStatus) {
		this.roverStatus = roverStatus;
	}


	public long getMaxSol() {
		return maxSol;
	}


	public void setMaxSol(long maxSol) {
		this.maxSol = maxSol;
	}


	public String getMaxDate() {
		return maxDate;
	}


	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}


	public long getTotalPhotos() {
		return totalPhotos;
	}


	public void setTotalPhotos(long totalPhotos) {
		this.totalPhotos = totalPhotos;
	}


	public List<MarseRoverCamera> getRoverCameras() {
		return roverCameras;
	}


	public void setRoverCameras(List<MarseRoverCamera> roverCameras) {
		this.roverCameras = roverCameras;
	}
	
	
	
	
}

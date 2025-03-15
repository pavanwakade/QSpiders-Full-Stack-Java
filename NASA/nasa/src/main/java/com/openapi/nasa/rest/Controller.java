package com.openapi.nasa.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.openapi.nasa.model.ApodResponse;

@RestController
public class Controller {

	private final String APOD_URL = "https://api.nasa.gov/planetary/apod";

	private final String API_KEY = "xVvoipUWaHfS8Mu5PgqO6T9WEZf2c4bDBIP5CE1s";

	private RestTemplate templet = new RestTemplate();

              //http://localhost:5000/apod?date=2025-03-01
	@GetMapping("/apod")
	public ApodResponse getJson(@RequestParam(required = false, defaultValue = "2024-03-13") String date) {

		ApodResponse response = templet.getForObject(APOD_URL + "?api_key=" + API_KEY + "&date=" + date,
				ApodResponse.class);
		return response;

	}

                     //hi
	@GetMapping("/hi")
	public String msg() {
		return "by";
	}

                 //userdetails?id=101&name=pavan
	@GetMapping("/userdetails")
	public String getIdAndName(@RequestParam int id, @RequestParam String name) {
		return "ID: " + id + ", Name: " + name;
	}
}

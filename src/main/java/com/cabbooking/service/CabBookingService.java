package com.cabbooking.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cabbooking.model.CabBooking;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class CabBookingService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${app.url}")
	private String appUrl;

	@Value("${app.bookservice}")
	private String bookServiceEndpoint;

	@Value("${app.fareService}")
	private String fareServiceEndpoint;

	public void bookCab(CabBooking cabBooking) throws JsonProcessingException, UnsupportedEncodingException {

		String url = appUrl + bookServiceEndpoint;

		// Build the URL with query parameters
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("fromLocation", URLEncoder.encode(cabBooking.getFromLocation(), "UTF-8"))
				.queryParam("typeOfCab", cabBooking.getTypeOfCab())
				.queryParam("toLocation", URLEncoder.encode(cabBooking.getToLocation(), "UTF-8"));

		// Set the Content-Type header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// Create an HttpEntity with headers
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);

		// Send the request and get the response
		ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(builder.toUriString(), requestEntity,
				String.class);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			System.out.println("Cab Booked Succes");
		} else {

			System.out.println("Cab Booked Failed");
		}
	}

	public double calculateFare(double distance) {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		// Add request parameters
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(appUrl + fareServiceEndpoint)
				.queryParam("distance", distance);

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Double> responseEntity = new RestTemplate().postForEntity(builder.toUriString(), requestEntity,
				Double.class);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity.getBody();
		} else {

			throw new RuntimeException("Failed to calculate fare");
		}
	}

}
package com.cabbooking.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cabbooking.model.CabBooking;
import com.cabbooking.service.CabBookingService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class CabBookingController {

	private static final String CAB_BOOKED_SUCCESSFULLY = "Cab booked successfully!";

	@Autowired
	private CabBookingService cabBookingService;

	@GetMapping("/")
	public ModelAndView redirectToHomePage() {

		ModelAndView modelAndView = new ModelAndView("travelManagement");
		modelAndView.setStatus(HttpStatus.OK);

		return modelAndView;
	}

	@GetMapping("/book")
	public ModelAndView redirectToBookingPage() {
		ModelAndView modelAndView = new ModelAndView("booking");
		modelAndView.setStatus(HttpStatus.OK);

		return modelAndView;
	}

	@GetMapping("/fare")
	public ModelAndView redirectToFarePage() {
		ModelAndView modelAndView = new ModelAndView("fare");
		modelAndView.addObject("fare", 0);

		modelAndView.setStatus(HttpStatus.OK);

		return modelAndView;
	}

	@PostMapping(value = "/book", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ModelAndView bookCab(@ModelAttribute CabBooking cabBooking, Model model) throws JsonProcessingException, UnsupportedEncodingException {
		cabBookingService.bookCab(cabBooking);

		ModelAndView modelAndView = new ModelAndView("booking");

		modelAndView.setStatus(HttpStatus.OK);
		modelAndView.addObject("message", CAB_BOOKED_SUCCESSFULLY);
		return modelAndView;
	}

	@PostMapping(value = "/fare", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ModelAndView calculateFare(@RequestParam("distance") double distance, Model model) {
		double fare = 0;
		if (distance != 0) {
			fare = cabBookingService.calculateFare(distance);
		}

		ModelAndView modelAndView = new ModelAndView("fare");

		modelAndView.setStatus(HttpStatus.OK);
		modelAndView.addObject("fare", fare);
		return modelAndView;
	}

}

package com.cabbooking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.cabbooking.model.CabBooking;
import com.cabbooking.service.CabBookingService;

@ExtendWith(value= {MockitoExtension.class})
public class CabBookingControllerTests {
	
	@Test
	void contextLoads() {
	}
	
	@Mock
    private RestTemplate restTemplate;

	@Mock
	private CabBookingService cabBookingService;

	@InjectMocks
	private CabBookingController cabBookingController;

	private MockMvc mockMvc;

	@BeforeEach
	public void before() {
		mockMvc = MockMvcBuilders.standaloneSetup(cabBookingController)
				.setViewResolvers(new InternalResourceViewResolver()).build();

	}

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	void testRedirectToHomePage() {
		ModelAndView modelAndView = cabBookingController.redirectToHomePage();
		assertEquals("travelManagement", modelAndView.getViewName());
		assertEquals(HttpStatus.OK, modelAndView.getStatus());
	}


	@Test
	public void testRedirectToBookingPage() throws Exception {

		mockMvc.perform(get("/book")).andExpect(status().isOk()).andExpect(model().size(0)); // No model attributes
																								// expected
	}

	@Test
	void testRedirectToFarePage() {
		ModelAndView modelAndView = cabBookingController.redirectToFarePage();
		assertEquals("fare", modelAndView.getViewName());
		assertEquals(0, modelAndView.getModel().get("fare"));
		assertEquals(HttpStatus.OK, modelAndView.getStatus());
	}
	

	@Test
	void testBookCab() throws Exception {

		CabBooking cabBooking = new CabBooking();
		ModelAndView modelAndView = cabBookingController.bookCab(cabBooking, null);
		verify(cabBookingService, times(1)).bookCab(cabBooking);
		assertEquals("booking", modelAndView.getViewName());
		assertEquals(HttpStatus.OK, modelAndView.getStatus());
	}

	@Test
	void testCalculateFare() {
		double distance = 10.0;
		ModelAndView modelAndView = cabBookingController.calculateFare(distance, null);
		verify(cabBookingService, times(1)).calculateFare(distance);
		assertEquals("fare", modelAndView.getViewName());
		assertEquals(0.0, modelAndView.getModel().get("fare"));
		assertEquals(HttpStatus.OK, modelAndView.getStatus());
	}
	
	   
}




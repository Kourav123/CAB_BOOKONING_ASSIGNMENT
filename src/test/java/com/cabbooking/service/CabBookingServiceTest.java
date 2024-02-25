package com.cabbooking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cabbooking.model.CabBooking;

@SpringBootTest()
@TestPropertySource(properties = {
        "app.url=http://localhost:8083",
        "app.bookService=/book",
        "app.fareService=/fare"
})
class CabBookingServiceTest {


    @Mock
    private RestTemplate restTemplate;

    @SpyBean
    private CabBookingService cabBookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBookCab() throws Exception {
    	
        // Mock the response entity
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Success", HttpStatus.OK);
        when(restTemplate.postForEntity(
                new URI("http://localhost:8083/book?fromLocation=" + URLEncoder.encode("Silk Board", "UTF-8") +
                    "&typeOfCab=1&toLocation=" + URLEncoder.encode("City Center", "UTF-8")),
                new HttpEntity<>(new HttpHeaders()), String.class))
                        .thenReturn(responseEntity);
        // Create a CabBooking object
        CabBooking cabBooking = new CabBooking();
        cabBooking.setFromLocation("Silk Board");
        cabBooking.setTypeOfCab(1);
        cabBooking.setToLocation("City Center");

        // Call the bookCab method and assert the result
        cabBookingService.bookCab(cabBooking);
    }

    @Test
    public void testCalculateFare() throws RestClientException, URISyntaxException {
        // Mock the response entity
        ResponseEntity<Double> responseEntity = new ResponseEntity<>(100.0, HttpStatus.OK);
        when(restTemplate.postForEntity(
                new URI("http://localhost:8083/fare?distance=10.0"), new HttpEntity<>(new HttpHeaders()), Double.class))
                        .thenReturn(responseEntity);

        // Call the calculateFare method and assert the result
        double fare = cabBookingService.calculateFare(10.0);
        assertEquals(100.0, fare);
    }
}

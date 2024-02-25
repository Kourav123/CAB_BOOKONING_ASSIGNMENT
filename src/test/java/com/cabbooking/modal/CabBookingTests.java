package com.cabbooking.modal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import com.cabbooking.model.CabBooking;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class CabBookingTests {
	
	 @Test
	    public void testCabBookingConstructor() {
	        // Arrange
	        String fromLocation = "A";
	        String toLocation = "B";
	        int typeOfCab = 0;

	        // Act
	        CabBooking cabBooking = new CabBooking(fromLocation, toLocation, typeOfCab);

	        // Assert
	        assertNotNull("CabBooking object is null", "cabBooking");
	        assertEquals( fromLocation, cabBooking.getFromLocation());
	        assertEquals( toLocation, cabBooking.getToLocation());
	        assertEquals( typeOfCab, cabBooking.getTypeOfCab());
	        assertNotNull( cabBooking.getBookingTime());
	    }

	    @Test
	    public void testCabBookingSettersAndGetters() {
	        // Arrange
	        CabBooking cabBooking = new CabBooking();
	        Long id = 1L;
	        String fromLocation = "Location A";
	        String toLocation = "Location B";
	        int typeOfCab = 1;
	        Date bookingTime = new Date();

	        
	        cabBooking.setId(id);
	        cabBooking.setFromLocation(fromLocation);
	        cabBooking.setToLocation(toLocation);
	        cabBooking.setTypeOfCab(typeOfCab);
	        cabBooking.setBookingTime(bookingTime);

	        
	        assertEquals( id, cabBooking.getId());
	        assertEquals( fromLocation, cabBooking.getFromLocation());
	        assertEquals( toLocation, cabBooking.getToLocation());
	        assertEquals( typeOfCab, cabBooking.getTypeOfCab());
	        assertEquals( bookingTime, cabBooking.getBookingTime() );
	    }
	}



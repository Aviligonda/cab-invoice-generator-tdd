package com.bridzelab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class InvoiceGeneratorTest {
    InvoiceGenerator generator;

    @Before
    public void setUp() {
        generator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = generator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceOrTime_ShouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = generator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);

    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(10.0, 15),
                new Ride(0.1, 1)
        };
        InvoiceSummary invoiceSummary = generator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 120);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);

    }

    // Same time and distance but total fare is different Because first one is normal ride second one is premium ride
    @Test
    public void givenTwoDifferentRidesPremiumAndNormalRide_FindTotalFareSummary() {
        double distance = 10;
        int time = 15;
        Assert.assertEquals(115, generator.calculateFare(distance, time), 0.0);
        Assert.assertEquals(230, generator.calculatePremiumFare(distance, time), 0.0);
    }

    // Total fare value of premium ride is less than 20 should return 20;
    @Test
    public void premiumMinimumFare() {
        double distance = 0.1;
        int time = 1;
        Assert.assertEquals(20, generator.calculatePremiumFare(distance, time), 0.0);
    }

    // Given a UserID the invoice service get list of rides from the ride repository
    @Test
    public void givenUserIDShouldReturnInvoiceSummary() {
        HashMap<Integer, Ride[]> rideRepo = new HashMap<>();
        Ride[] rides1 = {new Ride(2.0, 5), new Ride(3.0, 7)};
        Ride[] rides2 = {new Ride(4.0, 10), new Ride(7.0, 12)};
        Ride[] rides3 = {new Ride(3.0, 9), new Ride(10.0, 15)};
        Ride[] rides4 = {new Ride(5.5, 5), new Ride(12.0, 20)};
        rideRepo.put(1, rides1);
        rideRepo.put(2, rides2);
        rideRepo.put(3, rides3);
        rideRepo.put(4, rides4);
        int userID = 2;
        RideRepository rideRepository = new RideRepository(rideRepo, userID);
        InvoiceSummary invoiceSummary = rideRepository.calculateFare();
        InvoiceSummary expected = new InvoiceSummary(2, 132);
        Assert.assertEquals(expected, invoiceSummary);

    }
}

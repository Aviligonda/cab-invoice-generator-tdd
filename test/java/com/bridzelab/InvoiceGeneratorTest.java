package com.bridzelab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceGeneratorTest {
    InvoiceGenerator generator;

    @Before
    public void setUp() {
        new InvoiceGenerator();
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
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(10.0, 15),
                new Ride(0.1, 1)
        };
        double fare = generator.calculateFare(rides);
        Assert.assertEquals(120, fare, 0.0);
    }
}

package com.bridzelab;

import org.junit.Assert;
import org.junit.Test;

public class InvoiceGeneratorTest {
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        InvoiceGenerator generator = new InvoiceGenerator();
        double distance = 2.0;
        int time = 5;
        double fare = generator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceOrTime_ShouldReturnMinFare() {
        InvoiceGenerator generator = new InvoiceGenerator();
        double distance = 0.1;
        int time = 1;
        double fare = generator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);

    }
}

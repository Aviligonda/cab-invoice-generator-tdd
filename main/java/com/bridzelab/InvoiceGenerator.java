package com.bridzelab;

public class InvoiceGenerator {
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_COST_PER_KILOMETER = 10.0;
    private static final double MINIMUM_FARE = 5;
    private static final int PREMIUM_COST_PER_TIME = 2;
    private static final double PREMIUM_MINIMUM_COST_PER_KILOMETER = 20.0;
    private static final double PREMIUM_MINIMUM_FARE = 20;

    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return Math.max(totalFare, MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public double calculatePremiumFare(double distance, int time) {
        double totalFare = distance * PREMIUM_MINIMUM_COST_PER_KILOMETER + time * PREMIUM_COST_PER_TIME;
        return Math.max(totalFare, PREMIUM_MINIMUM_FARE);
    }

}

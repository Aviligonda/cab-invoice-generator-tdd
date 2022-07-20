package com.bridzelab;

import java.util.HashMap;

public class RideRepository {
    HashMap<Integer, Ride[]> rideRepository;
    Integer userID;

    public RideRepository(HashMap<Integer, Ride[]> rideRepository, Integer userID) {
        this.rideRepository = rideRepository;
        this.userID = userID;
    }

    public InvoiceSummary calculateFare() {
        if (this.userID == null || this.rideRepository.get(userID) == null) {
            throw new NullPointerException("Choose correct ID");
        }
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Ride[] userDetails = this.rideRepository.get(userID);
        double totalFare = 0;
        for (Ride ride : userDetails) {
            totalFare += invoiceGenerator.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(userDetails.length, totalFare);
    }
}

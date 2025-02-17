package com.zhaqsylyq.trips.service;

import com.zhaqsylyq.trips.dto.TripDto;

public interface ITripsService {
    void createTrip(TripDto tripDto);

    TripDto fetchTrip(String tripId);

    TripDto fetchTripByPassenger(String passengerId);

    TripDto fetchTripByDriver(String driverId);

//    boolean updateTrip(String , TripDto );
}

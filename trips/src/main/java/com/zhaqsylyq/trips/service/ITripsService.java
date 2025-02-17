package com.zhaqsylyq.trips.service;

import com.zhaqsylyq.trips.constants.TripStatus;
import com.zhaqsylyq.trips.dto.CreateRequestDto;
import com.zhaqsylyq.trips.dto.TripDto;

import java.util.List;

public interface ITripsService {
    void createTrip(CreateRequestDto createRequestDto);

    TripDto fetchTrip(Long tripId);

    TripDto fetchTripByPassenger(String passengerId);

    TripDto fetchTripByDriver(String driverId);

    TripDto acceptTrip(Long tripId, String driverId);

    List<TripDto> fetchTripsByStatus(TripStatus tripStatus);

//    boolean updateTrip(String , TripDto );
}

package com.zhaqsylyq.trips.mapper;

import com.zhaqsylyq.trips.dto.CreateRequestDto;
import com.zhaqsylyq.trips.dto.TripDto;
import com.zhaqsylyq.trips.entity.Trips;

public class TripsMapper {

    public static TripDto mapToTripDto(Trips trip, TripDto tripDto) {
        tripDto.setId(trip.getId());
        tripDto.setPassengerId(trip.getPassengerId());
        tripDto.setDriverId(trip.getDriverId());
        tripDto.setStatus(trip.getStatus());
        tripDto.setStartLocation(trip.getStartLocation());
        tripDto.setEndLocation(trip.getEndLocation());
        tripDto.setStartTime(trip.getStartTime());
        tripDto.setEndTime(trip.getEndTime());
        tripDto.setFareAmount(trip.getFareAmount());
        return tripDto;
    }

    public static Trips mapToTrips(CreateRequestDto createRequestDto, Trips trips) {
        trips.setPassengerId(createRequestDto.getPassengerId());
        trips.setStartLocation(createRequestDto.getStartLocation());
        trips.setEndLocation(createRequestDto.getEndLocation());
        return trips;
    }



}

package com.zhaqsylyq.trips.mapper;

import com.zhaqsylyq.trips.dto.TripDto;
import com.zhaqsylyq.trips.entity.Trips;

public class TripsMapper {

    public static TripDto mapToTripDto(Trips trip, TripDto tripDto) {
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

    public static Trips mapToTrips(TripDto tripDto, Trips trips) {
        trips.setPassengerId(tripDto.getPassengerId());
        trips.setDriverId(tripDto.getDriverId());
        trips.setStatus(tripDto.getStatus());
        trips.setStartLocation(tripDto.getStartLocation());
        trips.setEndLocation(tripDto.getEndLocation());
        trips.setStartTime(tripDto.getStartTime());
        trips.setEndTime(tripDto.getEndTime());
        trips.setFareAmount(tripDto.getFareAmount());
        return trips;
    }

}

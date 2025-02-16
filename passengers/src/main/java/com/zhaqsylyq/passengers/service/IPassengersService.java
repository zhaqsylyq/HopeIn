package com.zhaqsylyq.passengers.service;

import com.zhaqsylyq.passengers.dto.PassengerDto;
import com.zhaqsylyq.passengers.entity.PreferredLocation;

import java.util.List;

public interface IPassengersService {
    void createPassenger(PassengerDto passengerDto);

    PassengerDto getPassenger(String passengerId);

    List<PassengerDto> getAllPassengers();

    boolean updatePassenger(PassengerDto passengerDto);

    boolean deletePassenger(String passengerId);

    boolean updatePreferredLocations(String passengerId, List<PreferredLocation> preferredLocations);
}

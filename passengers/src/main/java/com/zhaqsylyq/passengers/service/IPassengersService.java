package com.zhaqsylyq.passengers.service;

import com.zhaqsylyq.passengers.dto.PassengerDto;

import java.util.List;

public interface IPassengersService {
    void createPassenger(PassengerDto passengerDto);

    PassengerDto getPassenger(String email);

    List<PassengerDto> getAllPassengers();

    boolean updatePassenger(PassengerDto passengerDto);

    boolean deletePassenger(String email);
}

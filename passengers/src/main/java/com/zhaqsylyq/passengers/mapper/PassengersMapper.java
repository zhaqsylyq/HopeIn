package com.zhaqsylyq.passengers.mapper;

import com.zhaqsylyq.passengers.dto.PassengerDto;
import com.zhaqsylyq.passengers.entity.Passenger;

public class PassengersMapper {

    public static PassengerDto mapToPassengerDto(Passenger passenger, PassengerDto passengerDto) {
        passengerDto.setName(passenger.getName());
        passengerDto.setEmail(passenger.getEmail());
        passengerDto.setPhoneNumber(passenger.getPhoneNumber());
        passengerDto.setRating(passenger.getRating());
        return passengerDto;
    }

    public static Passenger mapToPassenger(PassengerDto passengerDto, Passenger passenger) {
        passenger.setName(passengerDto.getName());
        passenger.setEmail(passengerDto.getEmail());
        passenger.setPhoneNumber(passengerDto.getPhoneNumber());
        passenger.setRating(passengerDto.getRating() == null ? 0.0 : passengerDto.getRating());
        return passenger;
    }
}

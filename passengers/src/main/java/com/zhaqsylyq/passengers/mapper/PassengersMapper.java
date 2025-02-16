package com.zhaqsylyq.passengers.mapper;

import com.zhaqsylyq.passengers.dto.PassengerDto;
import com.zhaqsylyq.passengers.entity.Passenger;

public class PassengersMapper {

    public static PassengerDto mapToPassengerDto(Passenger passenger, PassengerDto passengerDto) {
        passengerDto.setPassengerId(passenger.getPassengerId());
        passengerDto.setFirstName(passenger.getFirstName());
        passengerDto.setLastName(passenger.getLastName());
        passengerDto.setEmail(passenger.getEmail());
        passengerDto.setPhoneNumber(passenger.getPhoneNumber());
        passengerDto.setPreferredLocations(passenger.getPreferredLocations());
        passengerDto.setRatings(passenger.getRatings());
        return passengerDto;
    }

    public static Passenger mapToPassenger(PassengerDto passengerDto, Passenger passenger) {
        passenger.setFirstName(passengerDto.getFirstName());
        passenger.setLastName(passengerDto.getLastName());
        passenger.setEmail(passengerDto.getEmail());
        passenger.setPhoneNumber(passengerDto.getPhoneNumber());
        passenger.setPreferredLocations(passengerDto.getPreferredLocations());
        passenger.setRatings(passengerDto.getRatings());
        return passenger;
    }
}

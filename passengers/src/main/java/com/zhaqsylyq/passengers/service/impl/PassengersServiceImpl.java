package com.zhaqsylyq.passengers.service.impl;

import com.zhaqsylyq.passengers.dto.PassengerDto;
import com.zhaqsylyq.passengers.entity.Passenger;
import com.zhaqsylyq.passengers.entity.PreferredLocation;
import com.zhaqsylyq.passengers.exception.PassengerAlreadyExistsException;
import com.zhaqsylyq.passengers.exception.ResourceNotFoundException;
import com.zhaqsylyq.passengers.mapper.PassengersMapper;
import com.zhaqsylyq.passengers.repository.PassengerRepository;
import com.zhaqsylyq.passengers.service.IPassengersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PassengersServiceImpl implements IPassengersService {

    private PassengerRepository passengerRepository;
    @Override
    public void createPassenger(PassengerDto passengerDto) {
        Passenger passenger = PassengersMapper.mapToPassenger(passengerDto, new Passenger());
        Optional<Passenger> optionalPassenger = passengerRepository.findByEmail(passengerDto.getEmail());
        if(optionalPassenger.isPresent()){
            throw new PassengerAlreadyExistsException("Passenger already registered with given email "+passengerDto.getEmail());
        }
        // Generate a unique passengerId
        passenger.setPassengerId("P" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        passengerRepository.save(passenger);
    }

    @Override
    public PassengerDto getPassenger(String passengerId) {
        Passenger passenger = passengerRepository.findByPassengerId(passengerId).orElseThrow(() -> new ResourceNotFoundException("Passenger", "passengerId", passengerId));
        return PassengersMapper.mapToPassengerDto(passenger, new PassengerDto());
    }

    @Override
    public List<PassengerDto> getAllPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        return passengers.stream()
                .map(passenger -> PassengersMapper.mapToPassengerDto(passenger, new PassengerDto()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updatePassenger(PassengerDto passengerDto) {
        boolean isUpdated = false;
        Passenger passenger = passengerRepository.findByPassengerId(passengerDto.getPassengerId()).orElseThrow(() -> new ResourceNotFoundException("Passenger", "passengerId", passengerDto.getPassengerId()));

        if(passengerDto.getFirstName() != null && !passengerDto.getLastName().isEmpty()){
            passenger.setFirstName(passengerDto.getFirstName());
            passenger.setLastName(passengerDto.getLastName());
            passenger.setPhoneNumber(passengerDto.getPhoneNumber());
            passengerRepository.save(passenger);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deletePassenger(String passengerId) {
        Passenger passenger = passengerRepository.findByPassengerId(passengerId).orElseThrow(() -> new ResourceNotFoundException("Passenger", "passengerId", passengerId));
        passengerRepository.deleteById(passenger.getId());
        return true;
    }

    @Override
    public boolean updatePreferredLocations(String passengerId, List<PreferredLocation> preferredLocations) {
        Passenger passenger = passengerRepository.findByPassengerId(passengerId)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger", "passengerId", passengerId));
        passenger.setPreferredLocations(preferredLocations);
        passengerRepository.save(passenger);
        return true;
    }

}

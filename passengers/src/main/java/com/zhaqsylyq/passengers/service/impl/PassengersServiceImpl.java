package com.zhaqsylyq.passengers.service.impl;

import com.zhaqsylyq.passengers.dto.PassengerDto;
import com.zhaqsylyq.passengers.entity.Passenger;
import com.zhaqsylyq.passengers.exception.PassengerAlreadyExistsException;
import com.zhaqsylyq.passengers.exception.ResourceNotFoundException;
import com.zhaqsylyq.passengers.mapper.PassengersMapper;
import com.zhaqsylyq.passengers.repository.PassengerRepository;
import com.zhaqsylyq.passengers.service.IPassengersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        passengerRepository.save(passenger);
    }

    @Override
    public PassengerDto getPassenger(String email) {
        Passenger passenger = passengerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Passenger", "email", email));
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
        Passenger passenger = passengerRepository.findByEmail(passengerDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Passenger", "email", passengerDto.getEmail()));

        if(passengerDto.getName() != null && !passengerDto.getName().isEmpty()){
            passenger.setName(passengerDto.getName());
            passenger.setPhoneNumber(passengerDto.getPhoneNumber());
            passengerRepository.save(passenger);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deletePassenger(String email) {
        Passenger passenger = passengerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Passenger", "email", email));
        passengerRepository.deleteById(passenger.getId());
        return true;
    }
}

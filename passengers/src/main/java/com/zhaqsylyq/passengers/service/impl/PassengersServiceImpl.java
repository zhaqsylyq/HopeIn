package com.zhaqsylyq.passengers.service.impl;

import com.zhaqsylyq.passengers.dto.PassengerDto;
import com.zhaqsylyq.passengers.repository.PassengerRepository;
import com.zhaqsylyq.passengers.service.IPassengersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PassengersServiceImpl implements IPassengersService {

    private PassengerRepository passengerRepository;
    @Override
    public void createPassenger(PassengerDto passengerDto) {

    }
}

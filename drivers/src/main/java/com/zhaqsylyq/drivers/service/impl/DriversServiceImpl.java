package com.zhaqsylyq.drivers.service.impl;

import com.zhaqsylyq.drivers.dto.DriverDto;
import com.zhaqsylyq.drivers.entity.Driver;
import com.zhaqsylyq.drivers.exception.DriverAlreadyExistsException;
import com.zhaqsylyq.drivers.exception.ResourceNotFoundException;
import com.zhaqsylyq.drivers.mapper.DriversMapper;
import com.zhaqsylyq.drivers.repository.DriverRepository;
import com.zhaqsylyq.drivers.service.IDriversService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriversServiceImpl implements IDriversService {

    private DriverRepository driverRepository;
    @Override
    public void createDriver(DriverDto driverDto) {
        Driver driver = DriversMapper.mapToDriver(driverDto, new Driver());
        Optional<Driver> optionalDriver = driverRepository.findByEmail(driverDto.getEmail());
        if(optionalDriver.isPresent()){
            throw new DriverAlreadyExistsException("Driver already registered with given email "+driverDto.getEmail());
        }
        driverRepository.save(driver);
    }

    @Override
    public DriverDto getDriver(String email) {
        Driver driver = driverRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Driver", "email", email));
        return DriversMapper.mapToDriverDto(driver, new DriverDto());
    }

    @Override
    public List<DriverDto> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        return drivers.stream().map(driver -> DriversMapper.mapToDriverDto(driver, new DriverDto())).collect(Collectors.toList());
    }

    @Override
    public boolean updateDriver(DriverDto driverDto) {
        boolean isUpdated = false;
        Driver driver = driverRepository.findByEmail(driverDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Driver", "email", driverDto.getEmail()));
        if(driverDto.getName() != null && !driverDto.getName().isEmpty()){
            driver.setName(driverDto.getName());
            driver.setPhoneNumber(driverDto.getPhoneNumber());
            driver.setVehicleType(driverDto.getVehicleType());
            driver.setVehiclePlateNumber(driverDto.getVehiclePlateNumber());
            driverRepository.save(driver);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteDriver(String email) {
        Driver driver = driverRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Driver", "email", email));
        driverRepository.deleteById(driver.getId());
        return true;
    }
}

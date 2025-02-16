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
import java.util.UUID;
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
        driver.setDriverId("D" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        driver.setStatus("AVAILABLE"); // Default status

        driverRepository.save(driver);
    }

    @Override
    public DriverDto getDriver(String driverId) {
        Driver driver = driverRepository.findByDriverId(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver", "driverId", driverId));
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
        Driver driver = driverRepository.findByDriverId(driverDto.getDriverId()).orElseThrow(() -> new ResourceNotFoundException("Driver", "driverId", driverDto.getDriverId()));
        if(driverDto.getName() != null && !driverDto.getName().isEmpty()){
            driver.setName(driverDto.getName());
            driver.setPhoneNumber(driverDto.getPhoneNumber());
            driver.setVehicleInfo(driverDto.getVehicleInfo());
            driverRepository.save(driver);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteDriver(String driverId) {
        Driver driver = driverRepository.findByDriverId(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver", "driverId", driverId));
        driverRepository.deleteById(driver.getId());
        return true;
    }

    @Override
    public boolean updateStatus(String driverId, String status) {
        Driver driver = driverRepository.findByDriverId(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver", "driverId", driverId));

        driver.setStatus(status);
        driverRepository.save(driver);
        return true;
    }

    @Override
    public List<DriverDto> getAvailableDrivers() {
        List<Driver> drivers = driverRepository.findByStatus("AVAILABLE");
        return drivers.stream().map(driver -> DriversMapper.mapToDriverDto(driver, new DriverDto())).collect(Collectors.toList());
    }
}

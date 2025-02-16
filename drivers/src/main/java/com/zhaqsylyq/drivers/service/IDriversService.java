package com.zhaqsylyq.drivers.service;

import com.zhaqsylyq.drivers.dto.DriverDto;

import java.util.List;

public interface IDriversService {
    void createDriver(DriverDto driverDto);

    DriverDto getDriver(String driverId);
    List<DriverDto> getAllDrivers();

    boolean updateDriver(DriverDto driverDto);

    boolean deleteDriver(String driverId);

    boolean updateStatus(String driverId, String status);

    List<DriverDto> getAvailableDrivers();
}

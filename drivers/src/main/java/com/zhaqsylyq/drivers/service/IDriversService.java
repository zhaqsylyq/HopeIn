package com.zhaqsylyq.drivers.service;

import com.zhaqsylyq.drivers.dto.DriverDto;

import java.util.List;

public interface IDriversService {
    void createDriver(DriverDto driverDto);

    DriverDto getDriver(String email);
    List<DriverDto> getAllDrivers();

    boolean updateDriver(DriverDto driverDto);

    boolean deleteDriver(String email);
}

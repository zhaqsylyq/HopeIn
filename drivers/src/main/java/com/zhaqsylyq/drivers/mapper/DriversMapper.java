package com.zhaqsylyq.drivers.mapper;

import com.zhaqsylyq.drivers.dto.DriverDto;
import com.zhaqsylyq.drivers.entity.Driver;

public class DriversMapper {

    public static DriverDto mapToDriverDto(Driver driver, DriverDto driverDto) {
        driverDto.setDriverId(driver.getDriverId());
        driverDto.setName(driver.getName());
        driverDto.setEmail(driver.getEmail());
        driverDto.setPhoneNumber(driver.getPhoneNumber());
        driverDto.setVehicleInfo(driver.getVehicleInfo());
        driverDto.setStatus(driver.getStatus());
        driverDto.setRatings(driver.getRatings());
        return driverDto;
    }

    public static Driver mapToDriver(DriverDto driverDto, Driver driver) {
        driver.setName(driverDto.getName());
        driver.setEmail(driverDto.getEmail());
        driver.setPhoneNumber(driverDto.getPhoneNumber());
        driver.setVehicleInfo(driverDto.getVehicleInfo());
        driver.setStatus(driverDto.getStatus());
        driver.setRatings(driverDto.getRatings());
        return driver;
    }

}

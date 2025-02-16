package com.zhaqsylyq.drivers.mapper;

import com.zhaqsylyq.drivers.dto.DriverDto;
import com.zhaqsylyq.drivers.entity.Driver;

public class DriversMapper {

    public static DriverDto mapToDriverDto(Driver driver, DriverDto driverDto) {
        driverDto.setName(driver.getName());
        driverDto.setEmail(driver.getEmail());
        driverDto.setPhoneNumber(driver.getPhoneNumber());
        driverDto.setVehicleType(driver.getVehicleType());
        driverDto.setVehiclePlateNumber(driver.getVehiclePlateNumber());
        driverDto.setRating(driver.getRating());
        return driverDto;
    }

    public static Driver mapToDriver(DriverDto driverDto, Driver driver) {
        driver.setName(driverDto.getName());
        driver.setEmail(driverDto.getEmail());
        driver.setPhoneNumber(driverDto.getPhoneNumber());
        driver.setVehicleType(driverDto.getVehicleType());
        driver.setVehiclePlateNumber(driverDto.getVehiclePlateNumber());
        driver.setRating(driverDto.getRating() == null ? 0.0 : driverDto.getRating());
        return driver;
    }

}

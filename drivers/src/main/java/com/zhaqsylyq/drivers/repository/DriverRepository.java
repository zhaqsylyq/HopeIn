package com.zhaqsylyq.drivers.repository;

import com.zhaqsylyq.drivers.entity.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {
    Optional<Driver> findByEmail(String email);

    Optional<Driver> findByDriverId(String driverId);

    List<Driver> findByStatus(String available);

}

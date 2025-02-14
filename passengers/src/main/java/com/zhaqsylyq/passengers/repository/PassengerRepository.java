package com.zhaqsylyq.passengers.repository;

import com.zhaqsylyq.passengers.entity.Passenger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends MongoRepository<Passenger, String> {
}

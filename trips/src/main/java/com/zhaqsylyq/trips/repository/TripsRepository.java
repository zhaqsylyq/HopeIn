package com.zhaqsylyq.trips.repository;

import com.zhaqsylyq.trips.constants.TripStatus;
import com.zhaqsylyq.trips.entity.Trips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripsRepository extends JpaRepository<Trips, String> {

    @Query("SELECT t FROM Trips t WHERE t.status IN :statuses AND (t.passengerId = :passengerId OR t.driverId = :driverId)")
    Optional<Trips> findByStatusInAndPassengerIdOrDriverId(
            List<TripStatus> statuses, String passengerId, String driverId
    );
    Optional<Trips> findByPassengerId(String passengerId);

    Optional<Trips> findByDriverId(String driverId);
}

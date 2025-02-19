package com.zhaqsylyq.trips.service.impl;

import com.zhaqsylyq.trips.constants.TripStatus;
import com.zhaqsylyq.trips.dto.CreateRequestDto;
import com.zhaqsylyq.trips.dto.TripDto;
import com.zhaqsylyq.trips.entity.Trips;
import com.zhaqsylyq.trips.exception.ResourceNotFoundException;
import com.zhaqsylyq.trips.exception.TripAlreadyInProgressException;
import com.zhaqsylyq.trips.mapper.TripsMapper;
import com.zhaqsylyq.trips.repository.TripsRepository;
import com.zhaqsylyq.trips.service.FareCalculator;
import com.zhaqsylyq.trips.service.ITripsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.table.TableRowSorter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TripsServiceImpl implements ITripsService {

    private TripsRepository tripsRepository;
    @Override
    @Transactional
    public void createTrip(CreateRequestDto createRequestDto) {
        Trips trips = TripsMapper.mapToTrips(createRequestDto, new Trips());

        List<TripStatus> activeStatuses = List.of(TripStatus.REQUESTED, TripStatus.ACCEPTED, TripStatus.IN_PROGRESS);


        Optional<Trips> optionalTrips = tripsRepository.findByStatusInAndPassengerId(activeStatuses, createRequestDto.getPassengerId());
        if(optionalTrips.isPresent()){
            throw new TripAlreadyInProgressException("Trip already in progress for this passenger");
        }

        trips.setStartTime(LocalDateTime.now());
        trips.setStatus(TripStatus.REQUESTED);

        BigDecimal fareAmount = FareCalculator.calculateFare(trips.getStartLocation(), trips.getEndLocation(), trips.getStartTime());
        trips.setFareAmount(fareAmount);

        tripsRepository.save(trips);
    }

    @Override
    public TripDto fetchTrip(Long tripId) {
        Trips trip = tripsRepository.findById(tripId).orElseThrow(() -> new ResourceNotFoundException("Trip", "tripId", tripId.toString()));
        return TripsMapper.mapToTripDto(trip, new TripDto());
    }

    @Override
    public TripDto fetchTripByPassenger(String passengerId) {
        Trips trip = tripsRepository.findByPassengerId(passengerId).orElseThrow(() -> new ResourceNotFoundException("Trip", "passengerId", passengerId));
        return TripsMapper.mapToTripDto(trip, new TripDto());
    }

    @Override
    public TripDto fetchTripByDriver(String driverId) {
        Trips trips = tripsRepository.findByDriverId(driverId).orElseThrow(() -> new ResourceNotFoundException("Trip", "driverId", driverId));
        return TripsMapper.mapToTripDto(trips, new TripDto());
    }

    @Override
    @Transactional
    public TripDto acceptTrip(Long tripId, String driverId) {
        Trips trip = tripsRepository.findById(tripId).orElseThrow(() -> new ResourceNotFoundException("Trip", "tripId", tripId.toString()));
        if (trip.getStatus() != TripStatus.REQUESTED) {
            throw new TripAlreadyInProgressException("Trip is already in progress for this passenger");
        } else{
            trip.setStatus(TripStatus.ACCEPTED);
            trip.setDriverId(driverId);
            tripsRepository.save(trip);
        }
        return TripsMapper.mapToTripDto(trip, new TripDto());
    }

    @Override
    public List<TripDto> fetchTripsByStatus(TripStatus tripStatus) {
        List<Trips> trips = tripsRepository.findByStatus(tripStatus);

        return trips.stream().map(trip -> TripsMapper.mapToTripDto(trip, new TripDto())).toList();
    }

    @Override
    @Transactional
    public TripDto completeTrip(Long tripId) {
        Trips trip = tripsRepository.findById(tripId).orElseThrow(() -> new ResourceNotFoundException("Trip", "tripId", tripId.toString()));

        if(trip.getStatus() != TripStatus.IN_PROGRESS){
            throw new IllegalStateException("Trip is not in progress, so cannot be completed");
        }

        trip.setStatus(TripStatus.COMPLETED);
        trip.setEndTime(LocalDateTime.now());
        tripsRepository.save(trip);
        return TripsMapper.mapToTripDto(trip, new TripDto());
    }

    @Override
    @Transactional
    public TripDto startTrip(Long tripId) {
        //  Fetch trip by ID
        Trips trip = tripsRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "tripId", tripId.toString()));

        //  Ensure the trip is currently "ACCEPTED"
        if (trip.getStatus() != TripStatus.ACCEPTED) {
            throw new IllegalStateException("Trip cannot be started as it is not in ACCEPTED state.");
        }

        //  Mark the trip as "IN_PROGRESS"
        trip.setStatus(TripStatus.IN_PROGRESS);
        tripsRepository.save(trip);

        return TripsMapper.mapToTripDto(trip, new TripDto());
    }

}

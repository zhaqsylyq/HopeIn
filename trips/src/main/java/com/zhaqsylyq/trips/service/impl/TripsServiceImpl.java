package com.zhaqsylyq.trips.service.impl;

import com.zhaqsylyq.trips.constants.TripStatus;
import com.zhaqsylyq.trips.dto.TripDto;
import com.zhaqsylyq.trips.entity.Trips;
import com.zhaqsylyq.trips.exception.ResourceNotFoundException;
import com.zhaqsylyq.trips.exception.TripAlreadyInProgressException;
import com.zhaqsylyq.trips.mapper.TripsMapper;
import com.zhaqsylyq.trips.repository.TripsRepository;
import com.zhaqsylyq.trips.service.ITripsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TripsServiceImpl implements ITripsService {

    private TripsRepository tripsRepository;
    @Override
    public void createTrip(TripDto tripDto) {
        Trips trips = TripsMapper.mapToTrips(tripDto, new Trips());

        List<TripStatus> activeStatuses = List.of(TripStatus.REQUESTED, TripStatus.ACCEPTED, TripStatus.IN_PROGRESS);


        Optional<Trips> optionalTrips = tripsRepository.findByStatusInAndPassengerIdOrDriverId(activeStatuses, tripDto.getPassengerId(), tripDto.getDriverId());
        if(optionalTrips.isPresent()){
            throw new TripAlreadyInProgressException("Trip already in progress for this passenger or driver");
        }
        tripsRepository.save(trips);
    }

    @Override
    public TripDto fetchTrip(String tripId) {
        Trips trip = tripsRepository.findById(tripId).orElseThrow(() -> new ResourceNotFoundException("Trip", "tripId", tripId));
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

}

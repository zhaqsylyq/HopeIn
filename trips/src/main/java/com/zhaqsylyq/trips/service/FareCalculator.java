package com.zhaqsylyq.trips.service;

import com.zhaqsylyq.trips.entity.Location;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

// Distance Calculation (Haversine Formula)
public class FareCalculator {
    private static final double BASE_FARE = 5.0; // Base fare in $
    private static final double COST_PER_KM = 2.0; // Cost per km in $
    private static final double SURGE_MULTIPLIER = 1.5; // 50% extra charge during peak hours
    private static final double EARTH_RADIUS_KM = 6371; // Earth's radius in km

    // Define peak hours
    private static final LocalTime PEAK_START_MORNING = LocalTime.of(7, 0);
    private static final LocalTime PEAK_END_MORNING = LocalTime.of(10, 0);
    private static final LocalTime PEAK_START_EVENING = LocalTime.of(17, 0);
    private static final LocalTime PEAK_END_EVENING = LocalTime.of(20, 0);

    public static double calculateDistance(Location start, Location end) {
        double lat1 = Math.toRadians(start.getLatitude());
        double lon1 = Math.toRadians(start.getLongitude());
        double lat2 = Math.toRadians(end.getLatitude());
        double lon2 = Math.toRadians(end.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c; // Distance in km
    }

    public static BigDecimal calculateFare(Location start, Location end, LocalDateTime startTime) {
        double distance = calculateDistance(start, end);
        double fare = BASE_FARE + (distance * COST_PER_KM);

        // Check if the startTime falls within peak hours
        LocalTime tripTime = startTime.toLocalTime();
        boolean isPeakTime = (tripTime.isAfter(PEAK_START_MORNING) && tripTime.isBefore(PEAK_END_MORNING))
                || (tripTime.isAfter(PEAK_START_EVENING) && tripTime.isBefore(PEAK_END_EVENING));

        if (isPeakTime) {
            fare *= SURGE_MULTIPLIER; // Apply 1.5x surge pricing
        }

        return BigDecimal.valueOf(fare);
    }

}

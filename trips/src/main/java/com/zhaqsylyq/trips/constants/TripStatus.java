package com.zhaqsylyq.trips.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TripStatus {
    REQUESTED,
    ACCEPTED,
    COMPLETED,
    IN_PROGRESS,
    CANCELLED;

    @JsonCreator
    public static TripStatus fromString(String value) {
        for (TripStatus status : TripStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) { // Allows case-insensitive input
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid trip status: " + value);
    }

    @JsonValue
    public String toJson() {
        return name();
    }
}

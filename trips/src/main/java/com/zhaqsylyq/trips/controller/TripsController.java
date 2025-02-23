package com.zhaqsylyq.trips.controller;

import com.zhaqsylyq.trips.constants.TripStatus;
import com.zhaqsylyq.trips.dto.*;
import com.zhaqsylyq.trips.service.ITripsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Trips  in HopIn",
        description = "CRUD REST APIs in HopIn to CREATE, UPDATE, FETCH and DELETE Trips"
)
@RestController
@RequestMapping(value = "api/v1/trips", produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
@Validated
public class TripsController {

    private final ITripsService iTripsService;

    public TripsController(ITripsService iTripsService) {
        this.iTripsService = iTripsService;
    }

    @Autowired
    private TripsContactInfoDto tripsContactInfoDto;

    @Operation(
            summary = "Create Trip REST API",
            description = "REST API to create a new Trip in HopIn"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status 201 CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP STATUS 500 INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createTrip(@Valid @RequestBody CreateRequestDto createRequestDto) {
        iTripsService.createTrip(createRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Trip created successfully"));
    }

    @Operation(
            summary = "Accept Trip REST API",
            description = "REST API to accept a Trip in HopIn (Performed by the Driver)"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP STATUS 500 INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })
    @PostMapping("/accept")
    public ResponseEntity<TripDto> acceptTrip(@RequestParam Long tripId,
                                              @RequestParam String driverId) {
        TripDto tripDto = iTripsService.acceptTrip(tripId, driverId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripDto);
    }

    @Operation(
            summary = "Fetch trip REST API",
            description = "REST API to fetch a Trip in HopIn"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status 500 INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })
    @GetMapping("/fetch/{tripId}")
    public ResponseEntity<TripDto> fetchTrip(@PathVariable Long tripId) {
        TripDto tripDto = iTripsService.fetchTrip(tripId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripDto);
    }

    @GetMapping("/fetch-by-status/{tripStatus}")
    public ResponseEntity<List<TripDto>> fetchTripsByStatus(@PathVariable TripStatus tripStatus) {
        List<TripDto> tripDtos = iTripsService.fetchTripsByStatus(tripStatus);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripDtos);
    }

    @Operation(
            summary = "Fetch trip using passenger REST API",
            description = "REST API to fetch a Trip using Passenger in HopIn"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status 500 INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })
    @GetMapping("/fetch/passenger/{passengerId}")
    public ResponseEntity<TripDto> fetchTripByPassenger(@PathVariable String passengerId) {
        TripDto tripDto = iTripsService.fetchTripByPassenger(passengerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripDto);
    }


    @Operation(
            summary = "Fetch trip using driver REST API",
            description = "REST API to fetch a Trip using Driver in HopIn"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status 500 INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })
    @GetMapping("/fetch/driver/{driverId}")
    public ResponseEntity<TripDto> fetchTripByDriver(@PathVariable String driverId) {
        TripDto tripDto = iTripsService.fetchTripByDriver(driverId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripDto);
    }

    @Operation(
            summary = "Mark a trip as completed",
            description = "Updates the trip status to COMPLETED and sets the endTime"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trip marked as completed"),
            @ApiResponse(responseCode = "404", description = "Trip not found"),
            @ApiResponse(responseCode = "400", description = "Trip is not in progress")
    })
    @PutMapping("/complete/{tripId}")
    public ResponseEntity<TripDto> completeTrip(@PathVariable Long tripId) {
        TripDto tripDto = iTripsService.completeTrip(tripId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripDto);
    }

    @Operation(
            summary = "Start a trip",
            description = "Updates the trip status to IN_PROGRESS"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trip started successfully"),
            @ApiResponse(responseCode = "404", description = "Trip not found"),
            @ApiResponse(responseCode = "400", description = "Trip is not in ACCEPTED state")
    })
    @PutMapping("/start/{tripId}")
    public ResponseEntity<TripDto> startTrip(@PathVariable Long tripId) {
        TripDto trip = iTripsService.startTrip(tripId);
        return ResponseEntity.ok(trip);
    }

    @Operation(
            summary = "Get Contact Info",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<TripsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripsContactInfoDto);
    }
//    @PutMapping("/update/{tripId}")
//    public ResponseEntity<ResponseDto> updateTrip(@PathVariable String tripId, @RequestBody TripDto tripDto) {
//        boolean tripUpdated = iTripsService.updateTrip(tripId, tripDto);
//        if(!tripUpdated) {
//            return ResponseEntity
//                    .status(HttpStatus.EXPECTATION_FAILED)
//                    .body(new ResponseDto("417", "Update operation failed. Please try again or contact Dev team"));
//        }else {
//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body(new ResponseDto("200", "Trip updated successfully"));
//        }
//    }
}

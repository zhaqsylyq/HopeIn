package com.zhaqsylyq.passengers.controller;

import com.zhaqsylyq.passengers.constants.PassengersConstants;
import com.zhaqsylyq.passengers.dto.ErrorResponseDto;
import com.zhaqsylyq.passengers.dto.PassengerDto;
import com.zhaqsylyq.passengers.dto.PassengersContactInfoDto;
import com.zhaqsylyq.passengers.dto.ResponseDto;
import com.zhaqsylyq.passengers.entity.PreferredLocation;
import com.zhaqsylyq.passengers.service.IPassengersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Passengers in HopIn",
        description = "CRUD REST APIs in HopIn to CREATE, UPDATE, FETCH and DELETE Passengers"
)
@RestController
@RequestMapping(value = "/api/v1/passengers", produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
@Validated
public class PassengersController {

    private final IPassengersService iPassengersService;

    public PassengersController(IPassengersService iPassengersService) {
        this.iPassengersService = iPassengersService;
    }

    @Autowired
    private PassengersContactInfoDto passengersContactInfoDto;

    @Operation(
            summary = "Create passenger REST API",
            description = "REST API to create a new Passenger in HopIn"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status 201 CREATED"
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
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createPassenger(@Valid @RequestBody PassengerDto passengerDto) {
        iPassengersService.createPassenger(passengerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(PassengersConstants.STATUS_201, PassengersConstants.MESSAGE_201));
    }


    @Operation(
            summary = "Fetch passenger REST API",
            description = "REST API to fetch a Passenger in HopIn"
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
    @GetMapping("/fetch")
    public ResponseEntity<PassengerDto> getPassenger(@RequestParam String passengerId) {
        PassengerDto passengerDto = iPassengersService.getPassenger(passengerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(passengerDto);
    }

    @Operation(
            summary = "Fetch all passengers REST API",
            description = "REST API to fetch all Passengers in HopIn"
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

    @GetMapping("/fetch-all")
    public ResponseEntity<List<PassengerDto>> getAllPassengers() {
        List<PassengerDto> passengerDtos = iPassengersService.getAllPassengers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(passengerDtos);
    }


    @Operation(
            summary = "Update a passenger REST API",
            description = "REST API to update Passenger in HopIn"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updatePassenger(@Valid @RequestBody PassengerDto passengerDto) {
        boolean isUpdated = iPassengersService.updatePassenger(passengerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PassengersConstants.STATUS_200, PassengersConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(PassengersConstants.STATUS_417, PassengersConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete a passenger REST API",
            description = "REST API to delete Passenger in HopIn"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 OK"
            ),

            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deletePassenger(@RequestParam
//                                                       @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Email should be in format: 0qoZ6@example.com")
                                                       String passengerId) {
        boolean isDeleted = iPassengersService.deletePassenger(passengerId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PassengersConstants.STATUS_200, PassengersConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(PassengersConstants.STATUS_417, PassengersConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Update preferred locations",
            description = "REST API to update the preferred locations of a Passenger"
    )
        @PutMapping("/{passengerId}/preferred-locations")
    public ResponseEntity<ResponseDto> updatePreferredLocations(
            @PathVariable String passengerId,
            @RequestBody List<PreferredLocation> preferredLocations) {
        boolean isUpdated = iPassengersService.updatePreferredLocations(passengerId, preferredLocations);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(PassengersConstants.STATUS_200, "Preferred locations updated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(PassengersConstants.STATUS_417, "Failed to update preferred locations"));
        }
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
    public ResponseEntity<PassengersContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(passengersContactInfoDto);
    }

}
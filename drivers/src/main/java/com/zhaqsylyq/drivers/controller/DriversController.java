package com.zhaqsylyq.drivers.controller;

import com.zhaqsylyq.drivers.constants.DriversConstants;
import com.zhaqsylyq.drivers.dto.DriverDto;
import com.zhaqsylyq.drivers.dto.DriversContactInfoDto;
import com.zhaqsylyq.drivers.dto.ErrorResponseDto;
import com.zhaqsylyq.drivers.dto.ResponseDto;
import com.zhaqsylyq.drivers.service.IDriversService;
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
        name = "CRUD REST APIs for Drivers in HopIn",
        description = "CRUD REST APIs in HopIn to CREATE, READ, UPDATE and DELETE Drivers"
)
@RestController
@RequestMapping(value = "api/v1/drivers", produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
@Validated
public class DriversController {

    private final IDriversService iDriversService;

    public DriversController(IDriversService iDriversService) {
        this.iDriversService = iDriversService;
    }

    @Autowired
    private DriversContactInfoDto driversContactInfoDto;

    @Operation(
            summary = "Create Driver REST API",
            description = "REST API to create a new Driver in HopIn"
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
    public ResponseEntity<ResponseDto> createDriver(@Valid @RequestBody DriverDto driverDto) {
        iDriversService.createDriver(driverDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(DriversConstants.STATUS_201, DriversConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Driver REST API",
            description = "REST API to fetch a Driver in HopIn"
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
    public ResponseEntity<DriverDto> getDriver(@RequestParam
                                               //@Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Email should be in format: 0qoZ6@example.com")
                                               String driverId) {
        DriverDto driverDto = iDriversService.getDriver(driverId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(driverDto);
    }

    @Operation(
            summary = "Fetch All Drivers REST API",
            description = "REST API to fetch all Drivers in HopIn"
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
    public ResponseEntity<List<DriverDto>> getAllDrivers() {
        List<DriverDto> driverDtos = iDriversService.getAllDrivers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(driverDtos);
    }

    @Operation(
            summary = "Update Driver REST API",
            description = "REST API to update a Driver in HopIn"
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
    public ResponseEntity<ResponseDto> updateDriver(@Valid @RequestBody DriverDto driverDto) {
        boolean isUpdated = iDriversService.updateDriver(driverDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(DriversConstants.STATUS_200, DriversConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(DriversConstants.STATUS_417, DriversConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Driver REST API",
            description = "REST API to delete a Driver in HopIn"
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
    public ResponseEntity<ResponseDto> deleteDriver(@RequestParam
                                                    //@Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Email should be in format: 0qoZ6@example.com")
                                                    String driverId) {
        boolean isDeleted = iDriversService.deleteDriver(driverId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(DriversConstants.STATUS_200, DriversConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(DriversConstants.STATUS_417, DriversConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Update Driver Status REST API",
            description = "REST API to update the status of a driver in HopIn"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
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
    @PutMapping("/update-status/{driverId}")
    public ResponseEntity<ResponseDto> updateDriverStatus(
            @PathVariable String driverId,
            @RequestParam @Pattern(regexp = "AVAILABLE|OFFLINE|ON_TRIP", message = "Status should be AVAILABLE, OFFLINE, or ON_TRIP") String status) {
        boolean isUpdated = iDriversService.updateStatus(driverId, status);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(DriversConstants.STATUS_200, DriversConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(DriversConstants.STATUS_417, DriversConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Fetch Available Drivers REST API",
            description = "REST API to fetch all available drivers in HopIn"
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
    @GetMapping("/available")
    public ResponseEntity<List<DriverDto>> getAvailableDrivers() {
        List<DriverDto> drivers = iDriversService.getAvailableDrivers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(drivers);
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
    public ResponseEntity<DriversContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(driversContactInfoDto);
    }

}

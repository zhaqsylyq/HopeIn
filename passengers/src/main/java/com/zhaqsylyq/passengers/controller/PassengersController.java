package com.zhaqsylyq.passengers.controller;

import com.zhaqsylyq.passengers.constants.PassengersConstants;
import com.zhaqsylyq.passengers.dto.PassengerDto;
import com.zhaqsylyq.passengers.dto.ResponseDto;
import com.zhaqsylyq.passengers.service.IPassengersService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/passengers", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class PassengersController {

    private IPassengersService iPassengersService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createPassenger(@Valid @RequestBody PassengerDto passengerDto) {
        iPassengersService.createPassenger(passengerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(PassengersConstants.STATUS_201, PassengersConstants.MESSAGE_201));
    }


    @GetMapping("/fetch")
    public ResponseEntity<PassengerDto> getPassenger(@RequestParam
                                                         @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Email should be in format: 0qoZ6@example.com")
                                                         String email) {
        PassengerDto passengerDto = iPassengersService.getPassenger(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(passengerDto);
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<List<PassengerDto>> getAllPassengers() {
        List<PassengerDto> passengerDtos = iPassengersService.getAllPassengers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(passengerDtos);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updatePassenger(@Valid @RequestBody PassengerDto passengerDto) {
        boolean isUpdated = iPassengersService.updatePassenger(passengerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PassengersConstants.STATUS_200, PassengersConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(PassengersConstants.STATUS_500, PassengersConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deletePassenger(@RequestParam
                                                           @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Email should be in format: 0qoZ6@example.com")
                                                           String email) {
        boolean isDeleted = iPassengersService.deletePassenger(email);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PassengersConstants.STATUS_200, PassengersConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(PassengersConstants.STATUS_500, PassengersConstants.MESSAGE_500));
        }
    }
}

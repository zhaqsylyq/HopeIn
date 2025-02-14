package com.zhaqsylyq.passengers.controller;

import com.zhaqsylyq.passengers.constants.PassengersConstants;
import com.zhaqsylyq.passengers.dto.PassengerDto;
import com.zhaqsylyq.passengers.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/passengers", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassengersController {

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createPassenger(@RequestBody PassengerDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(PassengersConstants.STATUS_201, PassengersConstants.MESSAGE_201));
    }
}

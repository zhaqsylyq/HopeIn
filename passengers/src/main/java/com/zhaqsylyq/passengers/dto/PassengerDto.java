package com.zhaqsylyq.passengers.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PassengerDto {
    @NotEmpty(message = "Name should not be empty")
    @NotNull
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @NotNull
    @Email(message = "Email should be in format: 0qoZ6@example.com")
    private String email;

    @NotEmpty(message = "Phone number should not be empty")
    @NotNull
    @Pattern(regexp = "[0-9]{10}", message = "Phone number should be 10 digits")
    private String phoneNumber;
    private Double rating;
}

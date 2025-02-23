package com.zhaqsylyq.notifications.controller;

import com.zhaqsylyq.notifications.dto.NotificationDto;
import com.zhaqsylyq.notifications.dto.NotificationsContactInfoDto;
import com.zhaqsylyq.notifications.exception.ResourceNotFoundException;
import com.zhaqsylyq.notifications.service.INotificationService;
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

@Tag(name = "REST API for Notifications", description = "REST API for Notifications in HopIn")
@RestController
@RequestMapping(value = "/api/v1/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
@Validated
public class NotificationController {

    private final INotificationService notificationService;

    public NotificationController(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    private NotificationsContactInfoDto notificationsContactInfoDto;

    @Operation(summary = "Create Notification", description = "Creates a new notification for a user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Notification Created"),
            @ApiResponse(responseCode = "400", description = "Validation Error"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/create")
    public ResponseEntity<Void> createNotification(@Valid @RequestBody NotificationDto notificationDto) {
        notificationService.createNotification(notificationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get Notifications", description = "Fetch notifications for a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notifications Retrieved"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/fetch/{recipientId}")
    public ResponseEntity<List<NotificationDto>> getNotifications(@PathVariable String recipientId) {
        List<NotificationDto> notifications = notificationService.getNotificationsForUser(recipientId);
        if (notifications.isEmpty()) {
            throw new ResourceNotFoundException("Notifications", "recipientId", recipientId);
        }
        return ResponseEntity.ok(notifications);
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
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<NotificationsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationsContactInfoDto);
    }
}
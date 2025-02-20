package com.zhaqsylyq.notifications.entity;

import com.zhaqsylyq.notifications.constants.NotificationType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;
    private String recipientId;
    private NotificationType type;
    private String message;
    @CreatedDate
    private Instant timestamp = Instant.now();
}

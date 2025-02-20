package com.zhaqsylyq.notifications.service;

import com.zhaqsylyq.notifications.dto.NotificationDto;

import java.util.List;

public interface INotificationService {
    void createNotification(NotificationDto notificationDto);

    List<NotificationDto> getNotificationsForUser(String recipientId);
}

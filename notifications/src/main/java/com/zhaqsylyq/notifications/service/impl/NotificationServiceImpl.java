package com.zhaqsylyq.notifications.service.impl;

import com.zhaqsylyq.notifications.dto.NotificationDto;
import com.zhaqsylyq.notifications.entity.Notification;
import com.zhaqsylyq.notifications.repository.NotificationRepository;
import com.zhaqsylyq.notifications.service.INotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements INotificationService {

    private final NotificationRepository notificationRepository;
    @Override
    public void createNotification(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setRecipientId(notificationDto.getRecipientId());
        notification.setType(notificationDto.getType());
        notification.setMessage(notificationDto.getMessage());
        notification.setTimestamp(notificationDto.getTimestamp());

        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationDto> getNotificationsForUser(String recipientId) {
        return notificationRepository.findByRecipientIdOrderByTimestampDesc(recipientId)
                .stream()
                .map(notification -> new NotificationDto(
                        notification.getRecipientId(),
                        notification.getType(),
                        notification.getMessage(),
                        notification.getTimestamp()
                ))
                .collect(Collectors.toList());
    }
}

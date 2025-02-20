package com.zhaqsylyq.notifications.repository;

import com.zhaqsylyq.notifications.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByRecipientIdOrderByTimestampDesc(String recipientId);
}

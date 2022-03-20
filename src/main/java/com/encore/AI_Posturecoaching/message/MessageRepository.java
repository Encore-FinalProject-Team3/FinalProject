package com.encore.AI_Posturecoaching.message;


import com.encore.AI_Posturecoaching.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}

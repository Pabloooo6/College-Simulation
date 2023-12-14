package com.tecnocampus.grouppablo.persistence;

import com.tecnocampus.grouppablo.application.dto.MessageDTO;
import com.tecnocampus.grouppablo.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

    @Query("""
            SELECT NEW com.tecnocampus.grouppablo.application.dto.MessageDTO(m)
            FROM Message m
            WHERE m.sender.username = :id
            ORDER BY m.date ASC""")
    List<MessageDTO> findBySender(String id);

    @Query("""
            SELECT NEW com.tecnocampus.grouppablo.application.dto.MessageDTO(m)
            FROM Message m
            WHERE m.receiver.username = :id
            ORDER BY m.date ASC""")
    List<MessageDTO> findByReceiver(String id);

    @Query("""
            SELECT NEW com.tecnocampus.grouppablo.application.dto.MessageDTO(m)
            FROM Message m
            WHERE m.receiver.username = :id AND m.read = :isRead
            ORDER BY m.date ASC""")
    List<MessageDTO> findByRead(String id, boolean isRead);

    @Query("""
            SELECT NEW com.tecnocampus.grouppablo.application.dto.MessageDTO(m)
            FROM Message m
            WHERE (m.sender.username = :id AND m.receiver.username = :userId)
               OR (m.sender.username = :userId AND m.receiver.username = :id)
            ORDER BY m.date ASC""")
    List<MessageDTO> findBySenderAndReceiver(String id, String userId);
}

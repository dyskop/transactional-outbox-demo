package by.skopinau.transactional.outbox.demo.entity;

import by.skopinau.transactional.outbox.demo.util.JsonToMapConverter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Data
public class Outbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String event;

    @Column(name = "event_id")
    private int eventId;

    @Convert(converter = JsonToMapConverter.class)
    @SuppressWarnings("JpaAttributeTypeInspection")
    private Map<String, Object> payload;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

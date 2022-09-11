package by.skopinau.transactional.outbox.demo.repository;

import by.skopinau.transactional.outbox.demo.entity.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepository extends JpaRepository<Outbox, Integer> {
}

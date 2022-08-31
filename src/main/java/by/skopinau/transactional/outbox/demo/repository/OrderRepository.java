package by.skopinau.transactional.outbox.demo.repository;

import by.skopinau.transactional.outbox.demo.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Integer> {
}

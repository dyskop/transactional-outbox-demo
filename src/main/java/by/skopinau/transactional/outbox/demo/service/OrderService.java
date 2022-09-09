package by.skopinau.transactional.outbox.demo.service;

import by.skopinau.transactional.outbox.demo.entity.CustomerOrder;
import by.skopinau.transactional.outbox.demo.entity.Outbox;
import by.skopinau.transactional.outbox.demo.repository.OrderRepository;
import by.skopinau.transactional.outbox.demo.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final OutboxRepository outboxRepository;

    @Transactional
    public Map<String, Object> createOrder(Map<String, Object> orderMap) {
        CustomerOrder order = new CustomerOrder();
        order.setName(orderMap.get("name").toString());
        order.setQuantity(Integer.parseInt(String.valueOf(orderMap.get("quantity"))));
        orderRepository.save(order);

        Outbox outbox = new Outbox();

        outbox.setEvent("order_created");
        outbox.setEventId(order.getId());

        outbox.setPayload(orderMap);

        outbox.setCreatedAt(LocalDateTime.now());

        log.info(outbox.toString());
        outboxRepository.save(outbox);
        outboxRepository.delete(outbox);

        return Map.of("orderId", order.getId());
    }
}

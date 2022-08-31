package by.skopinau.transactional.outbox.demo.service.impl;

import by.skopinau.transactional.outbox.demo.entity.CustomerOrder;
import by.skopinau.transactional.outbox.demo.entity.Outbox;
import by.skopinau.transactional.outbox.demo.repository.OrderRepository;
import by.skopinau.transactional.outbox.demo.repository.OutboxRepository;
import by.skopinau.transactional.outbox.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OutboxRepository outboxRepository;

    @Override
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

        System.out.println(outbox);
        outboxRepository.save(outbox);
        outboxRepository.delete(outbox);

        return Map.of("orderId", order.getId());
    }
}

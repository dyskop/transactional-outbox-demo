package by.skopinau.transactional.outbox.demo.service;

import java.util.Map;

public interface OrderService {
    Map<String, Object> createOrder(Map<String, Object> orderMap);
}

package by.skopinau.transactional.outbox.demo.web;

import by.skopinau.transactional.outbox.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrdersController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> order) {
        return this.orderService.createOrder(order);
    }
}
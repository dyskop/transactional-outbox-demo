package by.skopinau.transactional.outbox.demo.web;

import by.skopinau.transactional.outbox.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @PostMapping
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> order) {
        return orderService.createOrder(order);
    }
}

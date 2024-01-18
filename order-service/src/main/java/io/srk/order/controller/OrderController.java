package io.srk.order.controller;

import io.srk.order.model.OrderDto;
import io.srk.order.model.OrderStatus;
import io.srk.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @PostMapping("/{bookId}/order")
    public OrderDto placeOrder(@PathVariable Long bookId) {
        return orderService.placeOrder(bookId);
    }

    @PatchMapping("/{id}/status")
    public OrderDto updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        return orderService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

    @DeleteMapping("/{bookId}/by-book")
    public void deleteByBook(@PathVariable Long bookId) {
        orderService.deleteByBook(bookId);
    }
}

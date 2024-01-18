package io.srk.order.controller;

import io.srk.order.model.OrderDto;
import io.srk.order.model.OrderStatus;
import io.srk.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order API")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Get all orders")
    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAll();
    }

    @Operation(summary = "Get order by id")
    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @Operation(summary = "Place order")
    @PostMapping("/{bookId}/order")
    public OrderDto placeOrder(@PathVariable Long bookId) {
        return orderService.placeOrder(bookId);
    }

    @Operation(summary = "Update order status")
    @PatchMapping("/{id}/status")
    public OrderDto updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        return orderService.updateStatus(id, status);
    }

    @Operation(summary = "Delete order by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

    @Operation(summary = "Delete orders by book id")
    @DeleteMapping("/{bookId}/by-book")
    public void deleteByBook(@PathVariable Long bookId) {
        orderService.deleteByBook(bookId);
    }
}

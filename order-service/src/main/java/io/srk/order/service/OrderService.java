package io.srk.order.service;

import io.srk.order.model.OrderDto;
import io.srk.order.model.OrderStatus;

import java.util.List;

public interface OrderService {
    
    List<OrderDto> getAll();

    OrderDto getById(Long id);

    OrderDto placeOrder(Long bookId);

    void delete(Long id);

    void deleteByBook(Long bookId);

    OrderDto updateStatus(Long id, OrderStatus status);

    List<OrderDto> getCurrentUserOrders();
}

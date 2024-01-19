package io.srk.order.service.impl;

import io.srk.order.client.BookServiceClient;
import io.srk.order.exception.ClientException;
import io.srk.order.exception.EntityNotFoundException;
import io.srk.order.exception.ExceptionCode;
import io.srk.order.model.Order;
import io.srk.order.model.OrderDto;
import io.srk.order.model.OrderMapper;
import io.srk.order.model.OrderStatus;
import io.srk.order.model.external.book.BookDto;
import io.srk.order.model.external.book.BookStatus;
import io.srk.order.repository.OrderRepository;
import io.srk.order.service.OrderService;
import io.srk.order.util.EntityConstants;
import io.srk.order.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BookServiceClient bookServiceClient;

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderDto getById(Long id) {
        return orderMapper.toDto(getEntity(id));
    }

    @Override
    public OrderDto placeOrder(Long bookId) {
        BookDto book = bookServiceClient.getById(bookId);
        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new ClientException(ExceptionCode.BOOK_HAS_WRONG_STATUS);
        }
        Order order = new Order();
        order.setBookId(book.getId());
        order.setPrice(book.getPrice());
        order.setStatus(OrderStatus.PLACED);
        UUID userId = SecurityUtil.getCurrentUserId();
        order.setUserId(String.valueOf(userId));
        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteByBook(Long bookId) {
        orderRepository.deleteAllByBookId(bookId);
    }

    @Override
    public OrderDto updateStatus(Long id, OrderStatus status) {
        Order order = getEntity(id);
        order.setStatus(status);
        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

    @Override
    public List<OrderDto> getCurrentUserOrders() {
        UUID userId = SecurityUtil.getCurrentUserId();
        return orderRepository.findAllByUserId(String.valueOf(userId)).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    private Order getEntity(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityConstants.ORDER, id));
    }
}

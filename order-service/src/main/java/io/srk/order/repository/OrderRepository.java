package io.srk.order.repository;

import io.srk.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    void deleteAllByBookId(Long id);

    List<Order> findAllByUserId(String userId);

    Long countByBookId(Long bookId);
}

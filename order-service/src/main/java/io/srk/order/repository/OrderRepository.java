package io.srk.order.repository;

import io.srk.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {

    void deleteAllByBookId(Long id);

    List<Order> findAllByUserId(String userId);
}

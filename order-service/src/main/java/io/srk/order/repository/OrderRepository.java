package io.srk.order.repository;

import io.srk.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    void deleteAllByBookId(Long id);
}

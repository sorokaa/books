package io.srk.order.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class OrderDto {

    private Long id;

    private Long bookId;

    private UUID userId;

    private OrderStatus status;

    private BigDecimal price;

    private Instant created;

    private Instant updated;
}

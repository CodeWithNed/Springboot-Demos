package com.nadun.ims.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private String orderName;

    private LocalDateTime orderDate;

    private String customerName;

    private double totalAmount;

    @ElementCollection
    private List<String> itemNames;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}

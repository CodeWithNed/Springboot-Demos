package com.nadun.ims.dto;

import com.nadun.ims.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int orderId;
    private String orderName;
    private LocalDateTime orderDate;
    private String customerName;
    private double totalAmount;
    private List<String> itemNames;
    private OrderStatus status;
}

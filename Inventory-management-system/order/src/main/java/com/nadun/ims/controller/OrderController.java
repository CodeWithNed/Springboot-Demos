package com.nadun.ims.controller;

import com.nadun.ims.dto.OrderDTO;
import com.nadun.ims.model.Order;
import com.nadun.ims.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        return convertToDTO(orderService.createOrder(order));
    }

    @PutMapping("/{orderId}")
    public OrderDTO updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        Order updatedOrder = convertToEntity(orderDTO);
        return convertToDTO(orderService.updateOrder(orderId, updatedOrder));
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    private OrderDTO convertToDTO(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .orderName(order.getOrderName())
                .orderDate(order.getOrderDate())
                .customerName(order.getCustomerName())
                .totalAmount(order.getTotalAmount())
                .itemNames(order.getItemNames())
                .status(order.getStatus())
                .build();
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        return Order.builder()
                .orderId(orderDTO.getOrderId())
                .orderName(orderDTO.getOrderName())
                .orderDate(orderDTO.getOrderDate())
                .customerName(orderDTO.getCustomerName())
                .totalAmount(orderDTO.getTotalAmount())
                .itemNames(orderDTO.getItemNames())
                .status(orderDTO.getStatus())
                .build();
    }
}

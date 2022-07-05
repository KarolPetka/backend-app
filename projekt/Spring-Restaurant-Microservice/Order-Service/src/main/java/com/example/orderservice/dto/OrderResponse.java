package com.example.orderservice.dto;

import com.example.orderservice.model.OrderItems;

import java.util.List;

public class OrderResponse {
    private Long id;
    private int orderNumber;
    private List<OrderItems> orderItems;
}

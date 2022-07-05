package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(OrderRequest orderRequest) {
        Order newOrder = Order.builder()
                .dish(orderRequest.getDish())
                .price(orderRequest.getPrice())
                .quantity(orderRequest.getQuantity())
                .build();
        orderRepository.save(newOrder);
    }

    public void updateMenu(Long id, OrderRequest orderRequest) {
        orderRepository.updateOrder(id, orderRequest.getDish(), orderRequest.getPrice(), orderRequest.getQuantity());
    }
}

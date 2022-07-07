package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    public final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequest orderRequest){
        return orderService.addOrder(orderRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDish(@PathVariable Long id, @RequestBody OrderRequest orderRequest){
        return orderService.updateDish(id, orderRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteDish(@PathVariable Long id){
        orderService.deleteDish(id);
    }
}

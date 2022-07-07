package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public ResponseEntity<?> addOrder(OrderRequest orderRequest) {
        Integer stockQuantity = getQuantityForDish(orderRequest.getDish());
        Integer dishPrice = getPriceForDish(orderRequest.getDish());

        if (stockQuantity == null || dishPrice == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order save fail, please try again");
        } else {
            int newQuantity = stockQuantity - orderRequest.getQuantity();
            Integer response = updateDishQuantityToDeduct(orderRequest.getDish(), newQuantity);

            int price = dishPrice * orderRequest.getQuantity();
            Order newOrder = Order.builder()
                    .dish(orderRequest.getDish())
                    .price(price)
                    .quantity(orderRequest.getQuantity())
                    .build();

            orderRepository.save(newOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order saved successfully");
        }
    }

    public ResponseEntity<?> updateDish(Long id, OrderRequest orderRequest) {
        if (orderRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to find dish with ID " + id);
        } else {
            int oldQuantity = orderRepository.findById(id).get().getQuantity();
            int newQuantity = orderRequest.getQuantity();

            if (oldQuantity < newQuantity) {
                Integer stockQuantity = getQuantityForDish(orderRequest.getDish());

                int updatedQuantity = stockQuantity - (newQuantity - oldQuantity);

                Integer response = updateDishQuantityToDeduct(orderRequest.getDish(), updatedQuantity);
                Integer dishPrice = getPriceForDish(orderRequest.getDish());

                int price = dishPrice * orderRequest.getQuantity();
                orderRepository.updateOrder(id, orderRequest.getDish(), price, orderRequest.getQuantity());
            } else if (oldQuantity > newQuantity) {
                Integer stockQuantity = getQuantityForDish(orderRequest.getDish());

                int updatedQuantity = stockQuantity + (oldQuantity - newQuantity);
                Integer response = updateDishQuantityToAdd(orderRequest.getDish(), updatedQuantity);

                Integer dishPrice = getPriceForDish(orderRequest.getDish());

                int price = dishPrice * orderRequest.getQuantity();
                orderRepository.updateOrder(id, orderRequest.getDish(), price, orderRequest.getQuantity());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantity did not changed, nothing to update");
            }
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dish updated successful");
    }

    public void deleteDish(Long id) {
        orderRepository.deleteById(id);
    }

    private Integer getQuantityForDish(String dish) {
        return webClient.get()
                .uri("http://localhost:8080/api/stock/quantity/" + dish)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    private Integer getPriceForDish(String dish) {
        return webClient.get()
                .uri("http://localhost:8080/api/menu/price/" + dish)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    private Integer updateDishQuantityToDeduct(String dish, int quantity){
        return webClient.put()
                .uri("http://localhost:8080/api/stock/deductQuantity/" + dish + "/" + quantity + "")
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }
    private Integer updateDishQuantityToAdd(String dish, int quantity){
        return webClient.put()
                .uri("http://localhost:8080/api/stock/addQuantity/" + dish + "/" + quantity + "")
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }
}

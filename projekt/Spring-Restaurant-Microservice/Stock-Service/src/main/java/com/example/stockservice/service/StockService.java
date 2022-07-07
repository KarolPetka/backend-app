package com.example.stockservice.service;

import com.example.stockservice.dto.StockRequest;
import com.example.stockservice.model.Stock;
import com.example.stockservice.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final WebClient webClient;

    public List<Stock> getStock() {
        return stockRepository.findAll();
    }

    public Integer getQuantityForDish(String dish) {
        return stockRepository.getQuantityForDish(dish);
    }

    public void addOrder(StockRequest stockRequest) {
        Stock newDish = Stock.builder()
                .dish(stockRequest.getDish())
                .quantity(stockRequest.getQuantity())
                .build();

        stockRepository.save(newDish);
    }

    public void updateStock(String dishToUpdate, StockRequest stockRequest) {
        stockRepository.updateStock(dishToUpdate, stockRequest.getDish(), stockRequest.getQuantity());
    }

    public void deductQuantity(String dish, Integer quantity) {
        stockRepository.deductQuantity(dish, quantity);
    }

    public void addQuantity(String dish, Integer quantity) {
        stockRepository.addQuantity(dish, quantity);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}

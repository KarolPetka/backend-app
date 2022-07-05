package com.example.stockservice.service;

import com.example.stockservice.dto.StockRequest;
import com.example.stockservice.model.Stock;
import com.example.stockservice.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public List<Stock> getStock() {
        return stockRepository.findAll();
    }

    public void addOrder(StockRequest stockRequest) {
        Stock newDish = Stock.builder()
                .dish(stockRequest.getDish())
                .quantity(stockRequest.getQuantity())
                .build();

        stockRepository.save(newDish);
    }

    public void updateStock(Long id, StockRequest stockRequest) {
        stockRepository.updateStock(id, stockRequest.getDish(), stockRequest.getQuantity());
    }
}

package com.example.stockservice.controller;

import com.example.stockservice.dto.StockRequest;
import com.example.stockservice.model.Stock;
import com.example.stockservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Stock> getStock(){
        return stockService.getStock();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@RequestBody StockRequest stockRequest){
        stockService.addOrder(stockRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateDish(@PathVariable Long id, @RequestBody StockRequest stockRequest){
        stockService.updateMenu(id, stockRequest);
    }
}

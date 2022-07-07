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

    @GetMapping("/quantity/{dish}")
    @ResponseStatus(HttpStatus.OK)
    public Integer getQuantityForDish(@PathVariable String dish){
        return stockService.getQuantityForDish(dish);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@RequestBody StockRequest stockRequest){
        stockService.addOrder(stockRequest);
    }

    @PutMapping("/update/{dish}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateDish(@PathVariable String dish, @RequestBody StockRequest stockRequest){
        stockService.updateStock(dish, stockRequest);
    }

    @PutMapping("/deductQuantity/{dish}/{quantity}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Integer deductQuantity(@PathVariable String dish, @PathVariable Integer quantity){
        stockService.deductQuantity(dish, quantity);
        return quantity;
    }

    @PutMapping("/addQuantity/{dish}/{quantity}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Integer addQuantity(@PathVariable String dish, @PathVariable Integer quantity){
        stockService.addQuantity(dish, quantity);
        return quantity;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteDish(@PathVariable Long id){
        stockService.deleteStock(id);
    }
}

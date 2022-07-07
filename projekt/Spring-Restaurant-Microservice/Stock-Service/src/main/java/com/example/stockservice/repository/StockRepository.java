package com.example.stockservice.repository;

import com.example.stockservice.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s.quantity FROM Stock s WHERE s.dish = :dish")
    Integer getQuantityForDish(String dish);

    @Modifying
    @Transactional
    @Query("UPDATE Stock s SET s.dish = :dish, s.quantity = :quantity WHERE s.dish = :dishToUpdated")
    void updateStock(String dishToUpdated, String dish, int quantity);

    @Modifying
    @Transactional
    @Query("UPDATE Stock s SET s.quantity = :quantity WHERE s.dish = :dish")
    void deductQuantity(String dish, Integer quantity);

    @Modifying
    @Transactional
    @Query("UPDATE Stock s SET s.quantity = :quantity WHERE s.dish = :dish")
    void addQuantity(String dish, Integer quantity);
}

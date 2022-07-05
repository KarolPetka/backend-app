package com.example.stockservice.repository;

import com.example.stockservice.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Stock s SET s.dish = :dish, s.quantity = :quantity WHERE s.id = :id")
    void updateStock(Long id, String dish, int quantity);
}

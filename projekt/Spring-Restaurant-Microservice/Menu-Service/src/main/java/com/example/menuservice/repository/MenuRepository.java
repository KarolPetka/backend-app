package com.example.menuservice.repository;

import com.example.menuservice.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Menu m SET m.dish = :dish, m.description = :description, m.price = :price WHERE m.id = :id")
    void updateDish(Long id, String dish, String description, int price);
}

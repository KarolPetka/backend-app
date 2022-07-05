package com.example.menuservice.repository;

import com.example.menuservice.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Modifying
    @Query("UPDATE Menu m SET m.dish = :dish, m.description = :description, m.price = :price WHERE m.id = :id")
    void updateDish(Long id, String dish, String description, int price);

    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id = :id")
    void deleteDish(Long id);
}

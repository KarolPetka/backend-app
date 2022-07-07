package com.example.menuservice.controller;

import com.example.menuservice.dto.MenuRequest;
import com.example.menuservice.dto.MenuResponse;
import com.example.menuservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MenuResponse> getMenu(){
        return menuService.getMenu();
    }

    @GetMapping("/price/{dish}")
    @ResponseStatus(HttpStatus.OK)
    public Integer getDishPrice(@PathVariable String dish){
        return menuService.getDishPrice(dish);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDish(@RequestBody MenuRequest menuRequest){
        menuService.addDish(menuRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateDish(@PathVariable Long id, @RequestBody MenuRequest menuRequest){
        menuService.updateMenu(id, menuRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteDish(@PathVariable Long id){
        menuService.deleteDish(id);
    }
}

package com.example.menuservice.service;

import com.example.menuservice.dto.MenuResponse;
import com.example.menuservice.model.Menu;
import com.example.menuservice.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuResponse> getMenu() {
        List<Menu> dishes = menuRepository.findAll();

        return dishes.stream().map(this::mapToMenuResponse).toList();
    }

    private MenuResponse mapToMenuResponse(Menu menu) {
        return MenuResponse.builder()
                .dish(menu.getDish())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .build();
    }
}

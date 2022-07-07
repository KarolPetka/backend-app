package com.example.menuservice.service;

import com.example.menuservice.dto.MenuRequest;
import com.example.menuservice.dto.MenuResponse;
import com.example.menuservice.model.Menu;
import com.example.menuservice.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuService {

    private final MenuRepository menuRepository;
    private final WebClient webClient;

    public List<MenuResponse> getMenu() {
        List<Menu> dishes = menuRepository.findAll();

        return dishes.stream().map(this::mapToMenuResponse).toList();
    }

    public Integer getDishPrice(String dish) {
        return menuRepository.getDishPrice(dish);
    }

    public void addDish(MenuRequest menuRequest) {
        Menu newDish = Menu.builder()
                .dish(menuRequest.getDish())
                .description(menuRequest.getDescription())
                .price(menuRequest.getPrice())
                .build();

        try {
            menuRepository.save(newDish);
            log.info("Dish with name " + newDish.getDish() + " has been saved with ID number " + newDish.getId());
        } catch (Exception e) {
            log.error("Error occurred while saving dish named " + newDish.getDish());
            e.printStackTrace();
        }
    }

    public void updateMenu(Long id, MenuRequest menuRequest) {
        try {
            menuRepository.updateDish(id, menuRequest.getDish(), menuRequest.getDescription(), menuRequest.getPrice());
            log.info("Dish with name " + menuRequest.getDish() + " has been updated");
        } catch (Exception e) {
            log.error("Error occurred while updating dish named " + menuRequest.getDish());
            e.printStackTrace();
        }
    }

    public void deleteDish(Long id) {
        try {
            menuRepository.deleteById(id);
            log.info("Dish with id " + id + " has been deleted");
        } catch (Exception e) {
            log.error("Error occurred while deleting dish with id " + id);
            e.printStackTrace();
        }
    }

    private MenuResponse mapToMenuResponse(Menu menu) {
        return MenuResponse.builder()
                .id(menu.getId())
                .dish(menu.getDish())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .build();
    }
}

package com.example.menuservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    private Long id;
    private String dish;
    private String description;
    private int price;
}

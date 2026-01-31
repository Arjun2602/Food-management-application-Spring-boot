package com.example.food_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FoodDto {
    private String id;

    @NotBlank(message = "name cannot be blank")
    private String name;
    private String description;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotNull(message = "Price cannot be null")
    private Double price;

    private Date created_date;

    private String image;

    public FoodDto(String id, String name, String description, String category, Double price, Date created_date, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.created_date = created_date;
        this.image = image;
    }
}

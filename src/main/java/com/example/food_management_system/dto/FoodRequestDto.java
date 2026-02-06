package com.example.food_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodRequestDto {

    private String id;
    @NotBlank(message = "name cannot be blank")
    private String name;
    private String description;
    @NotBlank(message = "Category cannot be blank")
    private String category;
    @NotNull(message = "Price cannot be null")
    private Double price;
    private String image;

}

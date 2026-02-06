package com.example.food_management_system.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
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
public class FoodResponseDto {

    private String id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Date created_date;
    private String image;
}

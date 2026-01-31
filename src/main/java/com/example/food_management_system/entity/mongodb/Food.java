package com.example.food_management_system.entity.mongodb;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "foods")
@Getter
@Setter
@NoArgsConstructor
public class Food {
    @Id
    private String id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Date created_date;
    private String image;

    public Food(String id, String name, String description, String category, Double price, Date created_date, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.created_date = created_date;
        this.image = image;
    }
}

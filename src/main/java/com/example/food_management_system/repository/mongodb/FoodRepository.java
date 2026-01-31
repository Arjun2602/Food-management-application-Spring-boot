package com.example.food_management_system.repository.mongodb;

import com.example.food_management_system.entity.mongodb.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends MongoRepository<Food, String> {
}

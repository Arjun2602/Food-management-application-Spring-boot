package com.example.food_management_system.controllers;


import com.example.food_management_system.dto.FoodDto;
import com.example.food_management_system.services.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/add")
    public ResponseEntity<?> saveFood(@RequestBody @Valid FoodDto foodDto){
        foodService.addFood(foodDto);
        return ResponseEntity.ok().body("Body created");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFood(@RequestBody FoodDto foodDto, @PathVariable String id){
        foodService.updateFood(foodDto, id);
        return ResponseEntity.ok().body("Updated");
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFood(@PathVariable String id){
        foodService.deleteFood(id);
    }

    @GetMapping
    public Map<String, Object> getAllFoods(){
        return foodService.getAllFoods();
    }
}

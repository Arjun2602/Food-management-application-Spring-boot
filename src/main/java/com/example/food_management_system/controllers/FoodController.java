package com.example.food_management_system.controllers;


import com.example.food_management_system.dto.FoodRequestDto;
import com.example.food_management_system.dto.FoodResponseDto;
import com.example.food_management_system.services.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllFoods(){
        List<FoodResponseDto> foodResponseDtoList = foodService.getAllFoods();
        Map<String, Object> res = new HashMap<>();
        res.put("foods", foodResponseDtoList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> saveFood(@RequestBody @Valid FoodRequestDto foodRequestDto){
        FoodResponseDto foodResponseDto =  foodService.addFood(foodRequestDto);
        Map<String, Object> res = new HashMap<>();
        res.put("food", foodResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateFood(@RequestBody FoodRequestDto foodRequestDto, @PathVariable String id){
        FoodResponseDto foodResponseDto = foodService.updateFood(foodRequestDto, id);
        Map<String, Object> res = new HashMap<>();
        res.put("food", foodResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteFood(@PathVariable String id){
        FoodResponseDto foodResponseDto =  foodService.deleteFood(id);
        Map<String, Object> res = new HashMap<>();
        res.put("food", foodResponseDto);
        res.put("message", "Food deleted");
        res.put("status code", HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}

package com.example.food_management_system.services;

import com.example.food_management_system.dto.FoodDto;
import com.example.food_management_system.entity.mongodb.Food;
import com.example.food_management_system.exception.ResourceNotFountException;
import com.example.food_management_system.repository.mongodb.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public void addFood(FoodDto foodDto){
        Food food = new Food();

        food.setName(foodDto.getName());
        food.setDescription(foodDto.getDescription());
        food.setCategory(foodDto.getCategory());
        food.setPrice(foodDto.getPrice());
        food.setImage(foodDto.getImage());
        food.setCreated_date(foodDto.getCreated_date());

        foodRepository.save(food);
    }

    public void updateFood(FoodDto foodDto, String id){
        Food food = foodRepository.findById(id).orElseThrow(()->new ResourceNotFountException("Food is not found for "+ id));

        food.setCreated_date(foodDto.getCreated_date());

        food.setName(foodDto.getName());
        food.setDescription(foodDto.getDescription());
        food.setCategory(foodDto.getCategory());
        food.setPrice(foodDto.getPrice());
        food.setImage(foodDto.getImage());
        food.setCreated_date(foodDto.getCreated_date());

        foodRepository.save(food);
    }

    public void deleteFood(String id){
        Food food = foodRepository.findById(id).orElseThrow(()->new ResourceNotFountException("Food not found for this "+ id));

        foodRepository.delete(food);
    }

    public Map<String, Object> getAllFoods(){
        List<Food> foodList = foodRepository.findAll();
        List<FoodDto> foodDtoList  = foodList.stream().map(this::convertToFoodDto).toList();
        Map<String, Object> response = new HashMap<>();
        response.put("foods", foodDtoList);
        return response;
    }

    public FoodDto convertToFoodDto(Food food){
        FoodDto foodDto = new FoodDto();
        foodDto.setId(food.getId());
        foodDto.setName(food.getName());
        foodDto.setDescription(food.getDescription());
        foodDto.setCategory(food.getCategory());
        foodDto.setCreated_date(food.getCreated_date());
        foodDto.setPrice(food.getPrice());
        foodDto.setImage(food.getImage());
        return foodDto;
    }

}

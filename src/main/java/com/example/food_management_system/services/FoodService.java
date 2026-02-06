package com.example.food_management_system.services;

import com.example.food_management_system.dto.FoodRequestDto;
import com.example.food_management_system.dto.FoodResponseDto;
import com.example.food_management_system.entity.mongodb.Food;
import com.example.food_management_system.exception.ResourceNotFountException;
import com.example.food_management_system.repository.mongodb.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public FoodResponseDto convertToResponseFoodDto(Food food){
        FoodResponseDto foodResponseDto = new FoodResponseDto();
        foodResponseDto.setId(food.getId());
        foodResponseDto.setName(food.getName());
        foodResponseDto.setDescription(food.getDescription());
        foodResponseDto.setCategory(food.getCategory());
        foodResponseDto.setCreated_date(food.getCreated_date());
        foodResponseDto.setPrice(food.getPrice());
        foodResponseDto.setImage(food.getImage());
        return foodResponseDto;
    }

    public FoodResponseDto addFood(FoodRequestDto foodRequestDto){
        Food food = new Food();

        food.setName(foodRequestDto.getName());
        food.setDescription(foodRequestDto.getDescription());
        food.setCategory(foodRequestDto.getCategory());
        food.setPrice(foodRequestDto.getPrice());
        food.setImage(foodRequestDto.getImage());

        Food savedFood = foodRepository.save(food);

        return convertToResponseFoodDto(savedFood);
    }

    public FoodResponseDto updateFood(FoodRequestDto foodRequestDto, String id){
        Food food = foodRepository.findById(id).orElseThrow(()->new ResourceNotFountException("Food is not found for id: "+ id));

        food.setName(foodRequestDto.getName());
        food.setDescription(foodRequestDto.getDescription());
        food.setCategory(foodRequestDto.getCategory());
        food.setPrice(foodRequestDto.getPrice());
        if(foodRequestDto.getImage() != null && !foodRequestDto.getImage().isBlank()){
            food.setImage(foodRequestDto.getImage());
        }
        Food savedFood = foodRepository.save(food);
        return convertToResponseFoodDto(savedFood);
    }

    public FoodResponseDto deleteFood(String id){
        Food food = foodRepository.findById(id).orElseThrow(()->new ResourceNotFountException("Food not found for this id:"+ id));
        FoodResponseDto foodResponseDto = convertToResponseFoodDto(food);
        foodRepository.delete(food);
        return foodResponseDto;
    }

    public List<FoodResponseDto> getAllFoods(){
        List<Food> foodList = foodRepository.findAll();
        List<FoodResponseDto> foodResponseDtoList = foodList.stream().map(this::convertToResponseFoodDto).toList();

        return foodResponseDtoList;
    }



}

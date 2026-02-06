package com.example.food_management_system.controllers;

import com.example.food_management_system.dto.UserRequestDto;
import com.example.food_management_system.dto.UserResponseDto;
import com.example.food_management_system.entity.mysql.User;
import com.example.food_management_system.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers(){
        List<UserResponseDto> userResponseDtoList =  userService.getAllUsers();
        Map<String, Object> res = new HashMap<>();
        res.put("users", userResponseDtoList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id){
        UserResponseDto userResponseDto = userService.getUserById(id);
        Map<String, Object> res = new HashMap<>();
        res.put("user", userResponseDto);
        return ResponseEntity.status(HttpStatus.FOUND).body(res);
    }

    @PostMapping("/addUser")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        Map<String, Object> res = new HashMap<>();
        res.put("user",userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("updateUser/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.updateUser(id, userRequestDto);
        Map<String, Object> res = new HashMap<>();
        res.put("user",userResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User "+ id+ " deleted");
    }
}

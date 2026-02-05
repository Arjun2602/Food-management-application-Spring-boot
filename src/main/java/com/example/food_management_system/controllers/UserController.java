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

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @PutMapping("updateUser/{id}")
    public ResponseEntity<UserResponseDto> updateuser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
         UserResponseDto userResponseDto = userService.updateUser(id, userRequestDto);
         return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User "+ id+ " deleted");
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> userResponseDtoList = userService.getAllUsers();
        return ResponseEntity.ok(userResponseDtoList);
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }


}

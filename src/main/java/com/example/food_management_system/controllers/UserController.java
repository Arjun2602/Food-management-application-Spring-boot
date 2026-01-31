package com.example.food_management_system.controllers;

import com.example.food_management_system.dto.UserDto;
import com.example.food_management_system.entity.mysql.User;
import com.example.food_management_system.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @PutMapping("/{id}")
    public User updateuser(@PathVariable Long id, @RequestBody UserDto userDto){
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User "+ id+ " deleted");
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

}

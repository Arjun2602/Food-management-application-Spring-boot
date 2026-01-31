package com.example.food_management_system.services;

import com.example.food_management_system.dto.UserDto;
import com.example.food_management_system.entity.mysql.User;
import com.example.food_management_system.exception.ResourceNotFountException;
import com.example.food_management_system.repository.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(UserDto userDto){
        User user = new User();

        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setCreated_date(userDto.getCreated_date());
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDto userDto){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFountException("User not fount with this "+ id));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setCreated_date(userDto.getCreated_date());
        //user.setId(userDto.getId());

        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFountException("User not fount with this "+ id));
        userRepository.delete(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFountException("User not found with this id: "+ id));
    }


}

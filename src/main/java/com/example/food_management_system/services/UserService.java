package com.example.food_management_system.services;

import com.example.food_management_system.dto.UserRequestDto;
import com.example.food_management_system.dto.UserResponseDto;
import com.example.food_management_system.entity.mysql.User;
import com.example.food_management_system.exception.DuplicateResourceException;
import com.example.food_management_system.exception.ResourceNotFountException;
import com.example.food_management_system.repository.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    public UserResponseDto convertToUserResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setRole(user.getRole());
        userResponseDto.setCreated_date(user.getCreated_date());
        return userResponseDto;
    }
    public UserResponseDto addUser(UserRequestDto userRequestDto){
        User user = new User();
        user.setId(userRequestDto.getId());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setUserName(userRequestDto.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));
        user.setRole(userRequestDto.getRole());

        try{
            User savedUser = userRepository.save(user);
            return convertToUserResponseDto(savedUser);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateResourceException("Username already exist!");
        }

    }

    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFountException("User not fount with this "+ id));

        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setUserName(userRequestDto.getUserName());
        if(userRequestDto.getPassword() != null && !userRequestDto.getPassword().isBlank()) {
            user.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));
        }
        user.setRole(userRequestDto.getRole());
        try{
            User savedUser = userRepository.save(user);
            return convertToUserResponseDto(savedUser);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateResourceException("Username already exist!");
        }
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFountException("User not fount with this "+ id));
        userRepository.delete(user);
    }

    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll().stream().map(this::convertToUserResponseDto).collect(Collectors.toList());
    }

    public UserResponseDto getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFountException("User not found with this id: "+ id));
        return  convertToUserResponseDto(user);
    }

}

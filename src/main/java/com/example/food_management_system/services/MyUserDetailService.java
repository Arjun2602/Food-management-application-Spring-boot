package com.example.food_management_system.services;

import com.example.food_management_system.entity.mysql.User;
import com.example.food_management_system.entity.mysql.UserPrinciple;
import com.example.food_management_system.repository.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByuserName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrinciple(user);
    }
}

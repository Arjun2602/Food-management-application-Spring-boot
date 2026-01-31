package com.example.food_management_system.repository.mysql;

import com.example.food_management_system.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

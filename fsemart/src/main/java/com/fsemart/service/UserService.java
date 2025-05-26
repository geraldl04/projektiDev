package com.fsemart.service;

import com.fsemart.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

     List<User> getAllUsers();

    Optional<User> getUserById(Long id);

     User saveUser(User user);

     void deleteUser(Long id);
}

package com.fsemart.controller;

import com.fsemart.entity.UpdateProfileDto;
import com.fsemart.entity.User;
import com.fsemart.repository.UserRepository;
import com.fsemart.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    //per momentin po e lej kshu cdo kush mund ti aksesosje

    @Autowired
    private final UserService userService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public User createUser(@Valid  @RequestBody User user) {
        return userService.saveUser(user);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/profile")
    public User getMyProfile(){
        return userService.getMyProfile();
    }

    //funksion per ti bere update usritcte autentikuar
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/profile")
    public User updateOwnProfile(@RequestBody UpdateProfileDto user) {
        return userService.updateUser(user);
    }

    //admini mund t ju beje update gjith userave
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/profile/{id}")
    public User updateUserByAdmin(@PathVariable Long id, @RequestBody UpdateProfileDto user) {
        return userService.updateUserByAdmin(id, user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public User updateUserByAdmin(@PathVariable Long id) {
        return userService.changeUserRoleToAdmin(id);
    }

}

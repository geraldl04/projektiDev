package com.fsemart.services.impl;

import com.fsemart.entity.CustomUserDetails;
import com.fsemart.entity.User;
import com.fsemart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepo) {
        this.userRepository = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByEmail(emailOrUsername);
        User user = userOpt.orElseThrow(() ->
                new UsernameNotFoundException(" useri nuk u gjet " + emailOrUsername));
        return new CustomUserDetails(user);
    }
}

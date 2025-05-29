package com.fsemart.services.impl;

import com.fsemart.entity.CustomUserDetails;
import com.fsemart.entity.UpdateProfileDto;
import com.fsemart.entity.User ;

import com.fsemart.repository.UserRepository;
import com.fsemart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public User getMyProfile() {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("Jo i autentikuar");
        }

        //perdor principal qe e perdora dhe ne order

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        if (userDetails != null) {
            return userDetails.getUserEntity();
        } else {
            throw new RuntimeException("useri nuk u gjet ");
        }
    }

    //nje user mund ti beje udpate vetem profilit te vet ndersa admini te gjithve
    @Override
    public User updateUser(UpdateProfileDto userUpdated) {

        //me kthen ate qe ruhet ne customUserDetails getUsername ne rastin tim emailin meqe eshte unik
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var auth = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();

        if (auth) {
            User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Useri nuk u gjet "));
            user.setUsername(userUpdated.getUsername());
            user.setEmail(userUpdated.getEmail());
       // nqs fushat e passwordit nuk jane null dhe bosh
            if (userUpdated.getNewPassword() != null && !userUpdated.getNewPassword().isBlank()) {
                if (!passwordEncoder.matches(userUpdated.getCurrentPassword(), user.getPassword())) {
                    throw new RuntimeException("Fjalkalimi i pasakte");
                }
                user.setPassword(passwordEncoder.encode(userUpdated.getNewPassword()));
            }
            return userRepository.save(user);
        }
        return null ;

    }

//do kemi duplikim kodi qe mund te bejme nje metode tjt qe ta perfshij kte duplikim po per momentin po e lej keshtu
    @Override
    public User updateUserByAdmin(Long userId, UpdateProfileDto userUpdated) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Useri nuk u gjet"));

        //admini do i beje update te gjithve duke sup qe mund tkem nje admin kryesor se eshte abstrakte qe nje admin
        //ti ndryshoje passwordin nje admini tjeter
        user.setUsername(userUpdated.getUsername());
        user.setEmail(userUpdated.getEmail());


        if (userUpdated.getNewPassword() != null && !userUpdated.getNewPassword().isBlank()) {

            if (!passwordEncoder.matches(userUpdated.getCurrentPassword(), user.getPassword())) {
                throw new RuntimeException("Fjalkalimi i pasakte");
            }
            user.setPassword(passwordEncoder.encode(userUpdated.getNewPassword()));
        }

        return userRepository.save(user);
    }


    @Override
    public User changeUserRoleToAdmin(Long id ){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Useri nuk u gjet"));
        user.setRole(User.Role.ADMIN);

        return userRepository.save(user);
    }
}

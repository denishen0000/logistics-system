package com.logistics.system.service;

import com.logistics.system.entity.User;
import com.logistics.system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public Optional<User> getUserByDriverId(Long driverId) {
        return userRepository.findByDriver_DriverId(driverId);
    }
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

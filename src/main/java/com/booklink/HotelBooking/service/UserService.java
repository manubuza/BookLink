package com.booklink.HotelBooking.service;

import com.booklink.HotelBooking.mapper.UserMapper;
import com.booklink.HotelBooking.model.dto.UserRequest;
import com.booklink.HotelBooking.model.dto.UserResponse;
import com.booklink.HotelBooking.model.entity.User;
import com.booklink.HotelBooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;



    public UserResponse addUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponse).collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResponse(user);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateEntity(userRequest, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

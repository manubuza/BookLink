package com.booklink.BookLink.service;

import com.booklink.BookLink.mapper.UserMapper;
import com.booklink.BookLink.model.dto.UserRequest;
import com.booklink.BookLink.model.dto.UserResponse;
import com.booklink.BookLink.model.entity.User;
import com.booklink.BookLink.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser_success() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        User user = new User();
        User savedUser = new User();
        UserResponse expectedResponse = new UserResponse();

        when(userMapper.toEntity(userRequest)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.toResponse(savedUser)).thenReturn(expectedResponse);

        // Act
        UserResponse actualResponse = userService.addUser(userRequest);

        // Assert
        assertNotNull(actualResponse);
        verify(userMapper, times(1)).toEntity(userRequest);
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toResponse(savedUser);
    }

    @Test
    void getAllUsers_success() {
        // Arrange
        User user = new User();
        UserResponse userResponse = new UserResponse();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        when(userMapper.toResponse(user)).thenReturn(userResponse);

        // Act
        List<UserResponse> actualResponses = userService.getAllUsers();

        // Assert
        assertEquals(1, actualResponses.size());
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).toResponse(user);
    }

    @Test
    void getUserById_success() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        UserResponse expectedResponse = new UserResponse();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toResponse(user)).thenReturn(expectedResponse);

        // Act
        UserResponse actualResponse = userService.getUserById(userId);

        // Assert
        assertNotNull(actualResponse);
        verify(userRepository, times(1)).findById(userId);
        verify(userMapper, times(1)).toResponse(user);
    }

    @Test
    void getUserById_notFound() {
        // Arrange
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.getUserById(userId));
        verify(userRepository, times(1)).findById(userId);
        verifyNoInteractions(userMapper);
    }

    @Test
    void updateUser_success() {
        // Arrange
        Long userId = 1L;
        UserRequest userRequest = new UserRequest();
        User user = new User();
        User updatedUser = new User();
        UserResponse expectedResponse = new UserResponse();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        doNothing().when(userMapper).updateEntity(userRequest, user);
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toResponse(updatedUser)).thenReturn(expectedResponse);

        // Act
        UserResponse actualResponse = userService.updateUser(userId, userRequest);

        // Assert
        assertNotNull(actualResponse);
        verify(userRepository, times(1)).findById(userId);
        verify(userMapper, times(1)).updateEntity(userRequest, user);
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toResponse(updatedUser);
    }

    @Test
    void updateUser_notFound() {
        // Arrange
        Long userId = 1L;
        UserRequest userRequest = new UserRequest();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.updateUser(userId, userRequest));
        verify(userRepository, times(1)).findById(userId);
        verifyNoInteractions(userMapper);
    }

    @Test
    void deleteUser_success() {
        // Arrange
        Long userId = 1L;

        doNothing().when(userRepository).deleteById(userId);

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }
}

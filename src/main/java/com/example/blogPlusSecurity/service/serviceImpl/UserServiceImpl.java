package com.example.blogPlusSecurity.service.serviceImpl;

import com.example.blogPlusSecurity.entity.User;
import com.example.blogPlusSecurity.exceptions.ResourceNotFoundException;
import com.example.blogPlusSecurity.repository.UserRepository;
import com.example.blogPlusSecurity.request.RegisterRequest;
import com.example.blogPlusSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public RegisterRequest createUser(RegisterRequest userDTO) {
        User user = mapToEntity(userDTO);
        User newUser = userRepository.save(user);
        return mapToDTO(newUser);
    }

    @Override
    public RegisterRequest updateUser(Long userId, RegisterRequest userDTO) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        User updatedUser = userRepository.save(user);
        return mapToDTO(user);
    }

    @Override
    public RegisterRequest findUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
        return mapToDTO(user);
    }

    private RegisterRequest mapToDTO(User user){
        return mapper.map(user, RegisterRequest.class);
    }

    private User mapToEntity(RegisterRequest userDTO) {
        return mapper.map(userDTO, User.class);
    }
}

package com.example.blogPlusSecurity.service;

import com.example.blogPlusSecurity.request.RegisterRequest;

public interface UserService{

    RegisterRequest createUser (RegisterRequest user);
    RegisterRequest updateUser(Long userId, RegisterRequest userRequest);
    RegisterRequest findUserById (Long userId);

}

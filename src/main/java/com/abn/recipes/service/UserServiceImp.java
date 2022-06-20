package com.abn.recipes.service;

import com.abn.recipes.domain.User;
import com.abn.recipes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }
}

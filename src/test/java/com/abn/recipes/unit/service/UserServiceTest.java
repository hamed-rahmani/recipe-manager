package com.abn.recipes.unit.service;

import com.abn.recipes.domain.User;
import com.abn.recipes.repository.UserRepository;
import com.abn.recipes.service.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userServiceImp;

    @Test
    public void checkGetUser() throws Exception {

        List<User> userList = new ArrayList<>();

        userList.add(new User(1L,"test",null));
        userList.add(new User(2L,"test2",null));

        when(userRepository.findAll()).thenReturn(userList);

        List<User> users = userServiceImp.findAll();
        assertThat(users.size()).isGreaterThan(0);
        verify(userRepository).findAll();
    }
}

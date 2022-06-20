package com.abn.recipes.unit.repository;


import com.abn.recipes.domain.User;
import com.abn.recipes.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAll_success() {
        List<User> allUsers = userRepository.findAll();
        assertThat(allUsers.size()).isGreaterThanOrEqualTo(1);
    }
}
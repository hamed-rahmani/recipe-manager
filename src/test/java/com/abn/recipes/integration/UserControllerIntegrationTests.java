package com.abn.recipes.integration;


import com.abn.recipes.RecipesApplication;
import com.abn.recipes.dto.ResponseEntityDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = RecipesApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTests {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_findAll() {

        ResponseEntityDto response = this.restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/user", ResponseEntityDto.class);

        assertEquals(3, ((ArrayList) response.getData()).size());

    }
}
package com.abn.recipes.integration;


import com.abn.recipes.RecipesApplication;
import com.abn.recipes.dto.ResponseEntityDto;
import com.abn.recipes.dto.recipe.add.AddRecipeDetailRequest;
import com.abn.recipes.dto.recipe.add.AddRecipeRequest;
import com.abn.recipes.dto.recipe.update.UpdateRecipeDetailRequest;
import com.abn.recipes.dto.recipe.update.UpdateRecipeRequest;
import com.abn.recipes.dto.search.SearchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = RecipesApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class RecipeControllerIntegrationTests {

    private static final String LOCALHOST_URL = "http://localhost:";
    private static final String BASE_URL = "/api/v1/recipe/";
    private static final String USER_ID = "1";
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_findAll() {

        ResponseEntityDto response = restTemplate
                .getForObject(LOCALHOST_URL + port + BASE_URL, ResponseEntityDto.class);

        assertEquals(false, ((ArrayList) response.getData()).isEmpty());

    }

    @Test
    public void test_findAllByUserId() {

        ResponseEntityDto response = restTemplate
                .getForObject(LOCALHOST_URL + port + BASE_URL + USER_ID, ResponseEntityDto.class);

        assertEquals(false, ((ArrayList) response.getData()).isEmpty());

    }

    @Test
    public void test_deleteById() {
        ResponseEntity<Void> response = restTemplate.exchange(LOCALHOST_URL + port + BASE_URL + USER_ID + "/1", HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void test_search() {

        ResponseEntity<ResponseEntityDto> response = restTemplate
                .postForEntity(LOCALHOST_URL + port + BASE_URL + "search", new SearchDto(), ResponseEntityDto.class);

        assertEquals(false, ((ArrayList) response.getBody().getData()).isEmpty());

    }

    @Test
    public void test_add() {
        Set<AddRecipeDetailRequest> recipeDetailRequestSet = new HashSet<>();
        recipeDetailRequestSet.add(new AddRecipeDetailRequest(100L, 1L));
        AddRecipeRequest request = new AddRecipeRequest("Name", "Comment", 10L, 1L, recipeDetailRequestSet);

        ResponseEntity<ResponseEntityDto> response = restTemplate
                .postForEntity(LOCALHOST_URL + port + BASE_URL + "/1", request, ResponseEntityDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());


    }

    @Test
    public void test_update() {
        Set<UpdateRecipeDetailRequest> recipeDetailRequestSet = new HashSet<>();
        recipeDetailRequestSet.add(new UpdateRecipeDetailRequest(100L, 1L, 1L));
        UpdateRecipeRequest request = new UpdateRecipeRequest("Name", "Comment", 10L, 1L, recipeDetailRequestSet);

        ResponseEntity<Void> response = restTemplate.exchange(LOCALHOST_URL + port + BASE_URL + USER_ID + "/1", HttpMethod.PUT, new HttpEntity<>(request), Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}
package com.abn.recipes.unit.controller;


import com.abn.recipes.config.ModelMapperConvertor;
import com.abn.recipes.controller.RecipeController;
import com.abn.recipes.dto.recipe.add.AddRecipeRequest;
import com.abn.recipes.dto.recipe.update.UpdateRecipeRequest;
import com.abn.recipes.dto.search.SearchDto;
import com.abn.recipes.service.RecipeServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private RecipeController controller;

    @Mock
    private RecipeServiceImp serviceImp;
    @Mock
    private ModelMapperConvertor modelMapperConvertor;

    private static final ObjectMapper JSON_SERIALIZER = new ObjectMapper();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }


    @BeforeAll
    static void init() {
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());

        JSON_SERIALIZER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        JSON_SERIALIZER.registerModule(new JavaTimeModule());
    }

    @Test
    public void check_findAllRecipe_endpoint_return_ok() throws Exception {

        mockMvc.perform(
                        get("/api/v1/recipe")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(status().isOk());

    }

    @Test
    public void check_findAllRecipeByUserId_endpoint_return_ok() throws Exception {

        mockMvc.perform(
                        get("/api/v1/recipe/2")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(status().isOk());

    }

    @Test
    public void check_deleteRecipeByIdAndUserId_endpoint_return_ok() throws Exception {

        mockMvc.perform(
                        delete("/api/v1/recipe/2/3")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(status().isOk());

    }

    @Test
    public void check_addRecipe_endpoint_return_ok() throws Exception {
        AddRecipeRequest request = new AddRecipeRequest();

        mockMvc.perform(
                        post("/api/v1/recipe/2")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON_SERIALIZER.writeValueAsString(request))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void check_updateRecipe_endpoint_return_ok() throws Exception {
        UpdateRecipeRequest request = new UpdateRecipeRequest();

        mockMvc.perform(
                        put("/api/v1/recipe/2/3")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON_SERIALIZER.writeValueAsString(request))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void check_search_endpoint_return_ok() throws Exception {
        SearchDto request = new SearchDto();

        mockMvc.perform(
                        post("/api/v1/recipe/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON_SERIALIZER.writeValueAsString(request))
                )
                .andExpect(status().isOk());
    }
}

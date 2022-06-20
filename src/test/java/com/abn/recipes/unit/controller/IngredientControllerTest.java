package com.abn.recipes.unit.controller;


import com.abn.recipes.config.ModelMapperConvertor;
import com.abn.recipes.controller.IngredientController;
import com.abn.recipes.service.IngredientService;
import com.abn.recipes.service.IngredientServiceImp;
import com.abn.recipes.service.UserServiceImp;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class IngredientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private IngredientController controller;

    @Mock
    private IngredientServiceImp ingredientServiceImp;
    @Mock
    private ModelMapperConvertor modelMapperConvertor;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    public void check_ingredient_endpoint_return_ok() throws Exception {

        mockMvc.perform(
                        get("/api/v1/ingredient")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(status().isOk());

    }
}

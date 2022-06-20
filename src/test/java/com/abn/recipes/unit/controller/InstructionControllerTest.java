package com.abn.recipes.unit.controller;


import com.abn.recipes.config.ModelMapperConvertor;
import com.abn.recipes.controller.InstructionController;
import com.abn.recipes.service.InstructionServiceImp;
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
public class InstructionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private InstructionController controller;

    @Mock
    private InstructionServiceImp instructionServiceImp;
    @Mock
    private ModelMapperConvertor modelMapperConvertor;


    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    public void check_instruction_endpoint_return_ok() throws Exception {

        mockMvc.perform(
                        get("/api/v1/instruction")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(status().isOk());
        
    }
}

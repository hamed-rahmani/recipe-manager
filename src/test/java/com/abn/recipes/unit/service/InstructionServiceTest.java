package com.abn.recipes.unit.service;

import com.abn.recipes.domain.Instruction;
import com.abn.recipes.repository.InstructionRepository;
import com.abn.recipes.service.InstructionServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InstructionServiceTest {

    @Mock
    private InstructionRepository repository;

    @InjectMocks
    private InstructionServiceImp serviceImp;

    @Test
    public void check_GetIngredient() {

        List<Instruction> ingredientList = new ArrayList<>();

        ingredientList.add(new Instruction(1L, "test", null));
        ingredientList.add(new Instruction(1L, "test2", null));

        when(repository.findAll()).thenReturn(ingredientList);

        List<Instruction> users = serviceImp.findAll();
        assertThat(users.size()).isGreaterThan(0);

        verify(repository).findAll();
    }
}

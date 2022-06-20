package com.abn.recipes.unit.repository;


import com.abn.recipes.domain.Instruction;
import com.abn.recipes.repository.InstructionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class InstructionRepositoryTest {

    @Autowired
    private InstructionRepository repository;

    @Test
    void findAll_success() {
        List<Instruction> instructions = repository.findAll();
        assertThat(instructions.size()).isGreaterThanOrEqualTo(1);
    }
}
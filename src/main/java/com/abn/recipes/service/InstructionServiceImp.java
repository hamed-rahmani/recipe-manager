package com.abn.recipes.service;

import com.abn.recipes.domain.Instruction;
import com.abn.recipes.repository.InstructionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InstructionServiceImp implements InstructionService {

    private final InstructionRepository instructionRepository;

    public List<Instruction> findAll() {
        return instructionRepository.findAll();
    }
}

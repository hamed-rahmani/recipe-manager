package com.abn.recipes.service;

import com.abn.recipes.domain.Instruction;

import java.util.List;

public interface InstructionService {
    List<Instruction> findAll();
}

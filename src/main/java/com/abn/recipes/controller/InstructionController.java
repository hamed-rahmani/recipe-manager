package com.abn.recipes.controller;

import com.abn.recipes.config.ModelMapperConvertor;
import com.abn.recipes.domain.Instruction;
import com.abn.recipes.dto.InstructionDto;
import com.abn.recipes.dto.ResponseEntityDto;
import com.abn.recipes.service.InstructionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instruction")
@AllArgsConstructor
public class InstructionController {

    private final InstructionService instructionService;
    private final ModelMapperConvertor modelMapperConvertor;

    @GetMapping
    public ResponseEntity<ResponseEntityDto> findAll() {

        List<Instruction> instructions = instructionService.findAll();
        ResponseEntityDto responseEntityDto = ResponseEntityDto.success(modelMapperConvertor.convert(instructions, InstructionDto.class));

        return ResponseEntity.ok(responseEntityDto);
    }

}

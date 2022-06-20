package com.abn.recipes.controller;

import com.abn.recipes.domain.User;
import com.abn.recipes.dto.IngredientDto;
import com.abn.recipes.dto.ResponseEntityDto;
import com.abn.recipes.dto.UserDto;
import com.abn.recipes.config.ModelMapperConvertor;
import com.abn.recipes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapperConvertor modelMapperConvertor;


    @GetMapping
    public ResponseEntity<ResponseEntityDto> findAll() {

        List<User> users = userService.findAll();
        ResponseEntityDto responseEntityDto = ResponseEntityDto.success(modelMapperConvertor.convert(users, UserDto.class));

        return ResponseEntity.ok(responseEntityDto);
    }

}

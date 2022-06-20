package com.abn.recipes.config;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ModelMapperConvertor<T, E> {
    private final ModelMapper modelMapper;

    public List<T> convert(List<E> inputs, Class<T> type) {
        List<T> convertedList = inputs.stream()
                .map(input -> modelMapper.map(input, type))
                .collect(Collectors.toList());
        return convertedList;
    }
}

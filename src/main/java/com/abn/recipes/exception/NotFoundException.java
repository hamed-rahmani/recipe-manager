package com.abn.recipes.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends Exception {

    public NotFoundException(String description) {
        super(description);
    }
}

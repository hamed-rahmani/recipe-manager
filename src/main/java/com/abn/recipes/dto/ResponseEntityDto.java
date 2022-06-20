package com.abn.recipes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntityDto {
    private Object data;
    private String message = "SUCCESS";

    public static ResponseEntityDto success(Object data) {
        return new ResponseEntityDto(data, "SUCCESS");
    }
    public static ResponseEntityDto successWithoutBody() {
        return new ResponseEntityDto();
    }

    public static ResponseEntityDto failed(String message) {
        return new ResponseEntityDto(null, message);
    }
}

package com.abn.recipes.exception;

import com.abn.recipes.dto.ResponseEntityDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.function.Function;
import java.util.stream.Collectors;

@ControllerAdvice
public class GeneralExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseEntityDto> handleUnknownException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseEntityDto.failed(exception.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseEntityDto> handleNotFoundException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND).body(ResponseEntityDto.failed(exception.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseEntityDto> echoResponseMessage(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getAllErrors().stream()
                .map(FIELD_ERROR_PROCESSOR)
                .collect(Collectors.joining(";"));
        return ResponseEntity
                .badRequest()
                .body(ResponseEntityDto.failed(message));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseEntityDto> echoResponseMessage(ConstraintViolationException exception) {
        return ResponseEntity
                .badRequest()
                .body(ResponseEntityDto.failed(exception.getMessage()));

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseEntityDto> echoResponseMessage(MissingServletRequestParameterException exception) {
        String message = "value for request-param '".concat(exception.getParameterName()).concat("' is missing");
        return ResponseEntity
                .badRequest()
                .body(ResponseEntityDto.failed(message));
    }


    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseEntityDto> handleBindException(BindException exception) {
        exception.getFieldErrors().forEach(m ->
                echoResponseMessage(new MissingServletRequestParameterException(m.getField(), m.getField()))
        );
        if (!exception.getBindingResult().getAllErrors().isEmpty()) { // handle MethodArgumentNotValidException
            String message = exception.getBindingResult().getAllErrors().stream()
                    .map(BINDING_RESULT_ERROR_PROCESSOR)
                    .collect(Collectors.joining(";"));
            return ResponseEntity
                    .badRequest()
                    .body(ResponseEntityDto.failed(message));
        }

        String message = "value for request-param '".concat(exception.getMessage()).concat("' is missing");
        return ResponseEntity
                .badRequest()
                .body(ResponseEntityDto.failed(message));
    }

    private static final Function<ObjectError, String> BINDING_RESULT_ERROR_PROCESSOR = error -> {
        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;
            return fieldError.getField().concat(" has invalid value: ").concat(fieldError.getDefaultMessage());
        }
        return error.getDefaultMessage();
    };
    private static final Function<ObjectError, String> FIELD_ERROR_PROCESSOR = error -> {
        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;
            return fieldError.getField().concat(" has invalid value: ").concat(fieldError.getDefaultMessage());
        }
        return error.getDefaultMessage();
    };
}

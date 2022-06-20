package com.abn.recipes.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


@Aspect
@Configuration
@Slf4j
public class LoggingHandler {

    private static final String LOGGING_PREFIX = "recipe-logging: ";

    @AfterReturning(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping) )", returning = "result")
    public static void afterExecute(JoinPoint joinPoint, Object result) {

        String request = extractRequest(joinPoint);

        String response = "Void";
        if (result != null) {
            response = result.toString().length() < 200 ? result.toString() : result.toString().substring(0, 200).concat("...");
        }

        log.info(LOGGING_PREFIX + request + ". Response is : '{}'", response);

    }

    @AfterThrowing(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping) )", throwing = "exception")
    public static void afterThrowing(JoinPoint joinPoint, Exception exception) {

        String request = "";
        String errorMessage = "";


        try {

            request = extractRequest(joinPoint);

            Optional<Throwable> exceptionRootCause = Stream.iterate(exception, Throwable::getCause)
                    .filter(element -> element.getCause() == null)
                    .findFirst();

            if (exceptionRootCause.isPresent()) {

                errorMessage = exceptionRootCause.get().getMessage();
            }

            log.warn(LOGGING_PREFIX +
                    request +
                    " .Caught Exception during serving Request. " +
                    "Error Message is : '{}' .Error Code is : '{}' ", errorMessage);
        } catch (Exception e) {
            log.warn(LOGGING_PREFIX +
                    request +
                    " .Caught Exception during serving Request. " +
                    "Error Message is : '{}' .Error Code is : '{}' ", e);
        }
    }

    private static String extractRequest(JoinPoint joinPoint) {
        StringBuilder request = new StringBuilder();

        request.append("`" + joinPoint.getSignature().toShortString() + "` is served. Request:{ ");

        AtomicInteger index = new AtomicInteger();

        Stream.of(joinPoint.getArgs())
                .filter(Objects::nonNull)
                .forEach(arg -> request.append("parameter_" + (index.incrementAndGet()) + ": " + arg + ", "));

        return request + "}";
    }
}

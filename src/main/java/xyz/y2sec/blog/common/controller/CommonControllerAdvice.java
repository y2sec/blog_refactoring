package xyz.y2sec.blog.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.y2sec.blog.common.dto.ApiResult;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice(annotations = RestController.class)
public class CommonControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ApiResult> entityNotFound(EntityNotFoundException exception) {
        // TODO log 남기기

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(null)
                .message("Entity Not Found")
                .build(), HttpStatus.NOT_FOUND);
    }

}

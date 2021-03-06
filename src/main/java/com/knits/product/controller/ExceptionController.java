package com.knits.product.controller;

import com.knits.product.dto.ExceptionDto;
import com.knits.product.exceptions.AppException;
import com.knits.product.exceptions.ExceptionCodes;
import com.knits.product.exceptions.SystemException;
import com.knits.product.exceptions.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(UserException.class)
    @ResponseBody
    public ResponseEntity<ExceptionDto> handleUserException(UserException ex) {
        return wrapIntoResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public ResponseEntity<ExceptionDto> handleSystemException(SystemException ex) {
        return wrapIntoResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ExceptionDto> handleAllExceptions(Exception ex, Errors errors) {
        log.error(ex.getMessage(), ex);
        String errorMessage = errors.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ExceptionDto exDto = new ExceptionDto(ExceptionCodes.UNMAPPED_EXCEPTION_CODE, errorMessage);
            return wrapIntoResponseEntity(exDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        private ResponseEntity<ExceptionDto> wrapIntoResponseEntity (AppException ex, HttpStatus status){
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(status)
                    .body(new ExceptionDto(ex));
        }

        private ResponseEntity<ExceptionDto> wrapIntoResponseEntity (ExceptionDto exDto, HttpStatus status){
            return ResponseEntity
                    .status(status)
                    .body(exDto);
        }
    }

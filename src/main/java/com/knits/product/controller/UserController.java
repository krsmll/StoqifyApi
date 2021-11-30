package com.knits.product.controller;

import com.knits.product.entity.User;
import com.knits.product.exceptions.UserException;
import com.knits.product.service.UserService;
import com.knits.product.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/users/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.debug("REST request to get all Users");
        return ResponseEntity.ok().body(userService.fetchAllUsers());
    }
}

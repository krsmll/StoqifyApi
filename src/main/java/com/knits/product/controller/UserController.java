package com.knits.product.controller;

import com.knits.product.exceptions.UserException;
import com.knits.product.service.UserService;
import com.knits.product.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long id) {

        log.debug("REST request to get User : {}", id);
        UserDTO userFound = userService.getUserById(id);
        return ResponseEntity.ok().body(userFound);
    }


    @GetMapping(value = "/users/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.debug("REST request to get all Users");
        return ResponseEntity.ok().body(userService.fetchAllUsers());
    }


    @PostMapping(value = "/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        log.debug("REST request to createUser User ");
        if (userDTO == null) {
            throw new UserException("User data are missing");
        }
        return ResponseEntity.ok().body(userService.createNewUser(userDTO));
    }

    @PutMapping(value = "/users")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        log.debug("REST request to updateUser User ");
        if (userDTO == null) {
            throw new UserException("User data are missing");
        }
        return ResponseEntity.ok().body(userService.update(userDTO));
    }

    @PatchMapping(value = "/users/{id}")
    public ResponseEntity<UserDTO> partialUpdateUser(@PathVariable(value = "id", required = false) Long id,
                                                     @RequestBody UserDTO userDTO) {
        log.debug("REST request to updateUser User ");

        if (userDTO == null) {
            throw new UserException("User data are missing");
        }
        userDTO.setId(id);
        return ResponseEntity.ok().body(userService.partialUpdateUserData(userDTO));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete User : {}", id);
        userService.deleteUserDataByUserId(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable) {
        throw new UnsupportedOperationException("getAllUsers(Pageable pageable) not implemented");
    }

    @PutMapping(value = "/users/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable(value = "id") Long id) {
        log.debug("REST request to deactivate User ");
        userService.deactivateUserById(id);
        return ResponseEntity.ok().build();
    }

}
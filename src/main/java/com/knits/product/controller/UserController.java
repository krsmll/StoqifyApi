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

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "id", required = true) Long id) {

        log.debug("REST request to get User : {}", id);
        UserDto userFound = userService.getUserById(id);
        return ResponseEntity.ok().body(userFound);
    }


    @GetMapping(value = "/users/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.debug("REST request to get all Users");
        return ResponseEntity.ok().body(userService.fetchAllUsers());
    }


    @PostMapping(value = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDTO) {
        log.debug("REST request to createUser User ");
        if (userDTO == null) {
            throw new UserException("User data are missing");
        }
        return ResponseEntity.ok().body(userService.createNewUser(userDTO));
    }

    @PutMapping(value = "/users")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDTO) {
        log.debug("REST request to updateUser User ");
        if (userDTO == null) {
            throw new UserException("User data are missing");
        }
        return ResponseEntity.ok().body(userService.update(userDTO));
    }

    @PatchMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> partialUpdateUser(@PathVariable(value = "id", required = false)Long id,
                                                     @RequestBody UserDto userDTO){
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
    public ResponseEntity<List<UserDto>> getAllUsers(Pageable pageable) {
        throw new UnsupportedOperationException("getAllUsers(Pageable pageable) not implemented");
    }

    @GetMapping("/searchuser/{searchkeyword}")
    public ResponseEntity<List<User>> searchUser(@PathVariable String searchkeyword) {
        log.debug("Searched Keyword : {}", searchkeyword);
        return new ResponseEntity<List<User>>(userService.searchUsersByKeyword(searchkeyword), HttpStatus.OK);
    }

    @PutMapping(value = "addusergroup/{userid}/{groupid}")
    public ResponseEntity<String> assignUserGroup(@PathVariable(value = "userid")Integer userId,
                                                  @PathVariable(value = "groupid")Integer groupId) {
        log.debug("REST request to add user in a group: {}", userId);
        return new ResponseEntity<>(userService.addUserGroup(userId, groupId), HttpStatus.OK);
    }

    @PutMapping("/adduserrole/{userid}/{roleid}")
    public ResponseEntity<String> addUserRole(@PathVariable(value = "userid")Integer userId,
                                              @PathVariable(value = "roleid")Integer roleId) {
        log.debug("Requested to add user role");
        return new ResponseEntity(userService.addUserRole(userId, roleId), HttpStatus.OK);
    }

    @PutMapping("/removeusergroup/{userid}")
    public ResponseEntity<String> removeUserGroup(@PathVariable(value = "userid")Integer userId) {
        return new ResponseEntity<>(userService.removeUserGroup(userId), HttpStatus.OK);
    }
}

package com.knits.product.controller;

import com.knits.product.dto.UserDto;
import com.knits.product.dto.groups.DeleteGroup;
import com.knits.product.dto.groups.UpdateGroup;
import com.knits.product.exceptions.UserException;
import com.knits.product.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * @param userId requested user id to search for
     * @return single user data
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {

        log.debug("REST request to get User : {}", userId);
        UserDto userFound = userService.getUserById(userId);
        return ResponseEntity.ok().body(userFound);
    }

    /**
     * @return all users
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.debug("REST request to get all Users");
        return ResponseEntity.ok().body(userService.fetchAllUsers());
    }

    /**
     * @param userDTO requested user to save
     * @return return all user including newly saved
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDTO) {
        log.debug("REST request to createUser User ");
        if (userDTO == null) {
            throw new UserException("User data are missing");
        }
        return new ResponseEntity<>(userService.createNewUser(userDTO), HttpStatus.CREATED);
    }

    /**
     * @param userDTO user data to update
     * @return newly updated user
     */
    @PutMapping(value = "/users")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDTO) {
        log.debug("REST request to updateUser User ");
        if (userDTO == null) {
            throw new UserException("User data are missing");
        }
        return ResponseEntity.ok().body(userService.update(userDTO));
    }

    /**
     * @param userDto update specific user fields
     * @return updated user info
     */
    @PatchMapping(value = "/users")
    public ResponseEntity<UserDto> partialUpdateUser(@Valid @RequestBody UserDto userDto) {
        log.debug("REST request to updateUser User ");
        return ResponseEntity.ok().body(userService.partialUpdateUserData(userDto));
    }

    /**
     * @param userDto delete the requested user
     * @return Void
     */
    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(@RequestBody UserDto userDto) {
        log.debug("REST request to delete User : {}", userDto.getId());
        userService.deleteUserDataByUserId(userDto);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param searchkeyword search for the user
     * @return all the users those are exits belong to the searched keyword
     */
    @GetMapping("/searchuser/{searchkeyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable("searchkeyword") String searchkeyword) {
        log.debug("Searched Keyword : {}", searchkeyword);
        return ResponseEntity.ok().body(userService.searchUsersByKeyword(searchkeyword));
    }

    /**
     * @param userDto add the user to the group
     * @return error message
     */
    @PutMapping("/addusergroup")
    public ResponseEntity<String> assignUserGroup(@Validated(UpdateGroup.class) @RequestBody UserDto userDto) {
        log.debug("REST request to add user in a group: {}", userDto.getId());
        return ResponseEntity.ok().body(userService.addUserGroup(userDto));
    }

    /**
     * @param userDto requested user data to add it in role
     * @return void
     */
    @PutMapping("/adduserrole")
    public ResponseEntity<Void> addUserRole(@RequestBody UserDto userDto) {
        log.debug("Requested to add user role");
        userService.addUserRole(userDto);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param userDto user data to remove
     * @return error message
     */
    @PutMapping("/removeusergroup")
    public ResponseEntity<String> removeUserGroup(@Validated(DeleteGroup.class) @RequestBody UserDto userDto) {
        userService.removeUserGroup(userDto);
        return ResponseEntity.noContent().build();
    }
}
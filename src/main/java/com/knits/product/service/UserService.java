package com.knits.product.service;

import com.knits.product.exceptions.ExceptionCodes;
import com.knits.product.exceptions.UserException;
import com.knits.product.entity.User;
import com.knits.product.mapper.UserMapper;
import com.knits.product.repository.UserRepository;
import com.knits.product.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Service for managing {@link com.knits.product.entity.User}.
 */
@Slf4j
@Service
@AllArgsConstructor
@Qualifier("sessionFactory")
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    /**
     * Save a employee.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    @Transactional
    public UserDto createNewUser(UserDto userDTO) {
        log.debug("Request to save User : {}", userDTO);
       // userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    /**
     * Partially updates a user.
     *
     * @param userDTO the entity to update partially.
     * @return the persisted entity.
     */
    @Transactional
    public UserDto partialUpdateUserData(UserDto userDTO) {
        log.debug("Request to partially update User : {}", userDTO);
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new UserException("User#" + userDTO.getId() + " not found"));
        userMapper.partialUpdate(user, userDTO);

        //TODO: manage User relationship to check updates
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    /**
     * overrides fields, including nulls.
     *
     * @param userDTO the entity to update.
     * @return the persisted entity.
     */
    @Transactional
    public UserDto update(UserDto userDTO) {
        log.debug("Request to update User : {}", userDTO);
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new UserException("User#" + userDTO.getId() + " not found"));
        userMapper.update(user, userDTO);

        //TODO: manage User relationship to check updates
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public List<UserDto> fetchAllUsers() {

        return userMapper.toUserDtoList(userRepository.findAll());
    }

    /**
     * Get by the "id" user.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public UserDto getUserById(Long id) {

        log.debug("Request User by id : {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("User#" + id + " not found", ExceptionCodes.USER_NOT_FOUND));
        return userMapper.toDto(user);
    }

    /**
     * Delete the "id" user.
     *
     * @param id the id of the entity.
     */
    @Transactional
    public void deleteUserDataByUserId(Long id) {
        log.debug("Delete User by id : {}", id);
        userRepository.deleteById(id);
    }

    /**
     * Search user using search keyword.
     *
     * @param searchKeyword to search users
     */
    public List<User> searchUsersByKeyword(String searchKeyword) {
        log.debug("Search User by search keyword : {}", searchKeyword);
        return userRepository.findByLastNameStartsWithIgnoreCase(searchKeyword);
    }

    /**
     * Get all the users.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<UserDto> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("not yet implementes");
    }

    /**
     *
     * @param userId specific user on which the group will be apply
     * @param groupId the group has been requested for
     * @return
     */
    @Transactional
    public String addUserGroup(Long userId, Long groupId) {
        if(userRepository.addUserGroup(userId, groupId) == 1) {
            return "User has been added to the requested group";
        } else {
            return "User could not add in group";
        }
    }

    /**
     *
     * @param userId the user will be use to set role
     * @param roleId assign role
     */
    @Transactional
    public String addUserRole(Long userId, Long roleId) {
        if(userRepository.addUserRole(userId, roleId) == 1) {
            return "Role has been assigned";
        } else {
            return "Could not assign user role";
        }
    }

    /**
     *
     * @param userId user id to remove group
     * @return message string
     */
    @Transactional
    public String removeUserGroup(Long userId) {
        if(userRepository.removeUserFromGroup(userId) == 1) {
            return "User removed from group";
        } else {
            return "User could not remove from group";
        }
    }
}

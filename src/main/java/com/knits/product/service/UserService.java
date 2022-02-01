package com.knits.product.service;

import com.knits.product.entity.UsersGroup;
import com.knits.product.entity.UsersRole;
import com.knits.product.exceptions.ExceptionCodes;
import com.knits.product.exceptions.UserException;
import com.knits.product.entity.User;
import com.knits.product.mapper.UserMapper;
import com.knits.product.repository.UserGroupRepository;
import com.knits.product.repository.UserRepository;
import com.knits.product.dto.UserDto;
import com.knits.product.repository.UsersRoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing {@link com.knits.product.entity.User}.
 */
@Slf4j
@Service("user")
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UsersRoleRepository usersRoleRepository;
    private final UserGroupRepository userGroupRepository;

    /**
     * Save a employee.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    public UserDto createNewUser(UserDto userDTO) {
        log.debug("Request to save User : {}", userDTO);
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
    public UserDto partialUpdateUserData(UserDto userDTO) {
        log.debug("Request to partially update User : {}", userDTO);
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new UserException("User#" + userDTO.getId() + " not found"));
        userMapper.partialUpdate(user, userDTO);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    /**
     * overrides fields, including nulls.
     *
     * @param userDTO the entity to update.
     * @return the persisted entity.
     */
    public UserDto update(UserDto userDTO) {
        log.debug("Request to update User : {}", userDTO);
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new UserException("User#" + userDTO.getId() + " not found"));
        userMapper.update(user, userDTO);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    /**
     *
     * @return List of users
     */
    public List<UserDto> fetchAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Get user "id" by UserDto.
     *
     * @param userId the id of the entity.
     * @return the entity.
     */
    public UserDto getUserById(Long userId) {

        log.debug("Request User by id : {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User#" + userId + " not found", ExceptionCodes.USER_NOT_FOUND));
        return userMapper.toDto(user);
    }

    /**
     * Delete the "id" user.
     *
     * @param userDto the id of the entity.
     */
    public void deleteUserDataByUserId(UserDto userDto) {
        log.debug("Delete User by id : {}", userDto.getId());
        userRepository.deleteById(userDto.getId());
    }

    /**
     * Search user using search keyword.
     *
     * @param searchKeyword to search users
     */
    public List<UserDto> searchUsersByKeyword(String searchKeyword) {
        log.debug("Search User by search keyword : {}", searchKeyword);
        return userRepository.findByLastNameStartsWithIgnoreCase(searchKeyword)
                .stream().map(userMapper::toDto).collect(Collectors.toList());
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
     * @param userDto specific user on which the group will be applied
     */
    public String addUserGroup(UserDto userDto) {
        usersRoleRepository.findById(userDto.getRoleId())
                .orElseThrow(() -> new UserException("User Role #" + userDto.getRoleId() + " does not exits"));

        userGroupRepository.findById(userDto.getGroupId())
                .orElseThrow(() -> new UserException("User Group #" + userDto.getGroupId() + " does not exits"));

        userGroupRepository.save(new UsersGroup(0L, userDto.getGroupId(), userDto.getId()));
        return "User could not add in group";
    }

    /**

     * @param userDto the user will be used to set role
     */
    public void addUserRole(UserDto userDto) {
        usersRoleRepository.save(new UsersRole(0L, userDto.getRoleId(), userDto.getId()));
    }

    /**
     *
     * @param userDto user to remove group from
     */
    public void removeUserGroup(UserDto userDto) {
        UsersGroup usersGroupData = userGroupRepository.findOneByUserId(userDto.getId())
                .orElseThrow(() -> new UserException("User# " + userDto.getId() + " not found"));
        userGroupRepository.delete(usersGroupData);
    }
}
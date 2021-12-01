package com.knits.product.service;

import com.knits.product.entity.UsersGroup;
import com.knits.product.entity.UsersRole;
import com.knits.product.exceptions.ExceptionCodes;
import com.knits.product.exceptions.UserException;
import com.knits.product.entity.User;
import com.knits.product.mapper.UserMapper;
import com.knits.product.repository.UserRepository;
import com.knits.product.dto.UserDto;
import com.knits.product.repository.UsersGroupRepository;
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
@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UsersRoleRepository usersRoleRepository;
    private final UsersGroupRepository usersGroupRepository;

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
    public UserDto updateUser(UserDto userDTO) {
        log.debug("Request to update User : {}", userDTO);
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new UserException("User#" + userDTO.getId() + " not found"));
        userMapper.update(user, userDTO);

        //TODO: manage User relationship to check updates
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public List<UserDto> fetchAllUsers() {

        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
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
    public void deleteUserDataByUserId(UserDto userDto) {
        log.debug("Delete User by id : {}", userDto.getId());
        User getUserData = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserException("User#" + userDto.getId() + " not found"));
        UsersRole getUserRole = usersRoleRepository.findOneByRoleId(getUserData.getRoleId());
        UsersGroup getUserGroup = usersGroupRepository.findOneByGroupId(getUserData.getGroupId());
        usersGroupRepository.delete(getUserGroup);
        usersRoleRepository.delete(getUserRole);
        userRepository.delete(getUserData);
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
     * @param UserDto specific user id and group id in this dto
     * @return UserDto
     */
    @Transactional
    public UserDto addUserGroup(UserDto userDto) {

        User getUserData = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserException("User Not found"));
        //getUserData.setGroupId(userDto.getGroupId());
        userRepository.save(getUserData);
        return userMapper.toDto(getUserData);
    }

    /**
     *
     * @param UserDto with requested role id
     * @return  UserDto with updated data
     */
    @Transactional
    public UserDto addUserRole(UserDto userDto) {
        User getUserData = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserException("User not found"));
        //getUserData.setRoleId(userDto.getRoleId());
        userRepository.save(getUserData);
        return userMapper.toDto(getUserData);
    }

    /**
     *
     * @param UserDto it should have only id which is user id
     * @return UserDto
     */
    @Transactional
    public UserDto removeUserGroup(UserDto userDto) {

        User getUserData = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserException("User Not found"));
        //getUserData.setGroupId(0L);
        userRepository.save(getUserData);
        return userMapper.toDto(getUserData);
    }
}

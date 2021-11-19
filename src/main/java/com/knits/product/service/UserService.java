package com.knits.product.service;

import com.knits.product.exceptions.ExceptionCodes;
import com.knits.product.exceptions.UserException;
import com.knits.product.entity.User;
import com.knits.product.repository.UserRepository;
import com.knits.product.dto.UserDTO;
import com.knits.product.service.mapper.UserMapper;
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

    /**
     * Save a employee.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    @Transactional
    public UserDTO createNewUser(UserDTO userDTO) {
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
    public UserDTO partialUpdateUserData(UserDTO userDTO) {
        log.debug("Request to partially update User : {}", userDTO);
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new UserException("User#" + userDTO.getId() + " not found", ExceptionCodes.USER_NOT_FOUND));
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
    public UserDTO update(UserDTO userDTO) {
        log.debug("Request to update User : {}", userDTO);
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new UserException("User#" + userDTO.getId() + " not found", ExceptionCodes.USER_NOT_FOUND));
        userMapper.update(user, userDTO);

        //TODO: manage User relationship to check updates
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public List<UserDTO> fetchAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Get by the "id" user.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public UserDTO getUserById(Long id) {

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
     * Get all the users.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<UserDTO> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Delete the "id" user.
     *
     * @param id the id of the entity.
     */
    @Transactional
    public void deactivateUserById(Long id) {
        log.debug("deactivate User by id : {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("User#" + id + " not found", ExceptionCodes.USER_NOT_FOUND));

        //TODO: manage User relationship to check updates
        user.setActive(false);
        userRepository.save(user);
    }

}
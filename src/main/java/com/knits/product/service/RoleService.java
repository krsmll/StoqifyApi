package com.knits.product.service;

import com.knits.product.dto.RoleDto;
import com.knits.product.dto.UserDto;
import com.knits.product.entity.Role;
import com.knits.product.exceptions.ExceptionCodes;
import com.knits.product.exceptions.UserException;
import com.knits.product.mapper.RoleMapper;
import com.knits.product.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing {@link com.knits.product.entity.Role}.
 */
@Slf4j
@Service
@AllArgsConstructor
public class RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    /**
     * Save a role.
     *
     * @param roleDTO the entity to save.
     * @return the persisted entity.
     */
    @Transactional
    public RoleDto createNewRole(RoleDto roleDTO) {
        log.debug("Request to save Role : {}", roleDTO);

        Role role = roleMapper.toRoleEntity(roleDTO);
        role = roleRepository.save(role);
        return roleMapper.toRoleEntity(role);
    }

    /**
     * Partially updates a role.
     *
     * @param roleDTO the entity to update partially.
     * @return the persisted entity.
     */

    @Transactional
    public RoleDto partialUpdateRoleData(RoleDto roleDTO) {
        log.debug("Request to partially update Role : {}", roleDTO);
        Role role = roleRepository.findById(roleDTO.getId())
                .orElseThrow(() -> new UserException("Role#" + roleDTO.getId() + " not found"));
        roleMapper.partialUpdate(role, roleDTO);
        roleRepository.save(role);
        return roleMapper.toRoleEntity(role);
    }

    /**
     * overrides fields, including nulls.
     *
     * @param roleDTO the entity to update.
     * @return the persisted entity.
     */

    @Transactional
    public RoleDto updateRoleData(RoleDto roleDTO) {
        log.debug("Request to update Role : {}", roleDTO);
        Role role = roleRepository.findById(roleDTO.getId())
                .orElseThrow(() -> new UserException("Role#" + roleDTO.getId() + " not found"));
        roleMapper.update(role, roleDTO);
        roleRepository.save(role);
        return roleMapper.toRoleEntity(role);
    }

    @Transactional
    public List<RoleDto> fetchAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleEntity).collect(Collectors.toList());
    }

    /**
     * Get by the "id" role.
     *
     * @param RoleDto the id of the entity.
     * @return the entity.
     */
    @Transactional
    public RoleDto getRoleById(RoleDto roleDto) {
        log.debug("Request User by id : {}", roleDto.getId());
        Role role = roleRepository.findById(roleDto.getId())
                .orElseThrow(() -> new UserException("Role#" + roleDto.getId() + "not found", ExceptionCodes.USER_NOT_FOUND));
        return roleMapper.toRoleEntity(role);
    }

    /**
     * Delete the "id" role.
     *
     * @param RoleDto the id of the entity.
     */
    @Transactional
    public void deleteRoleDataByRoleId(RoleDto roleDto) {
        log.debug("Delete Role by id : {}", roleDto.getId());
        roleRepository.deleteById(roleDto.getId());
    }

    /**
     * Get all the roles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional
    public Page<RoleDto> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("not yet implemented");
    }


}

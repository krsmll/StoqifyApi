package com.knits.product.service;

import com.knits.product.dto.RoleDto;
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
@Service("role")
@AllArgsConstructor
@Transactional
public class RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    /**
     * Save a role.
     *
     * @param roleDTO the entity to save.
     * @return the persisted entity.
     */
    public RoleDto createNewRole(RoleDto roleDTO) {
        log.debug("Request to save Role : {}", roleDTO);
        Role role = roleMapper.toEntity(roleDTO);
        return roleMapper.toDto(roleRepository.save(role));
    }

    /**
     * Partially updates a role.
     *
     * @param roleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public RoleDto partialUpdateRoleData(RoleDto roleDTO) {
        log.debug("Request to partially update Role : {}", roleDTO);
        Role role = roleRepository.findById(roleDTO.getId())
                .orElseThrow(() -> new UserException("Role#" + roleDTO.getId() + " not found"));
        roleMapper.partialUpdate(role, roleDTO);
        roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    /**
     * overrides fields, including nulls.
     *
     * @param roleDTO the entity to update.
     * @return the persisted entity.
     */
    public RoleDto updateRoleData(RoleDto roleDTO) {
        log.debug("Request to update Role : {}", roleDTO);
        Role role = roleRepository.findById(roleDTO.getId())
                .orElseThrow(() -> new UserException("Role#" + roleDTO.getId() + " not found"));
        roleMapper.update(role, roleDTO);
        roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    public List<RoleDto> fetchAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Get by the "id" role.
     *
     * @param roleId the id of the entity.
     * @return the entity.
     */
    public RoleDto getRoleById(Long roleId) {
        log.debug("Request User by id : {}", roleId);
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new UserException("Role#" + roleId + "not found", ExceptionCodes.USER_NOT_FOUND));
        return roleMapper.toDto(role);
    }

    /**
     * Delete the "id" role.
     *
     * @param roleId the id of the entity.
     */
    public void deleteRoleByRoleId(Long roleId) {
        log.debug("Delete Role by id : {}", roleId);
        roleRepository.deleteById(roleId);
    }

    /**
     * Get all the roles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<RoleDto> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("not yet implemented");
    }


}

package com.knits.product.service;


import com.knits.product.dto.RoleDTO;
import com.knits.product.entity.Role;
import com.knits.product.exceptions.ExceptionCodes;
import com.knits.product.exceptions.UserException;
import com.knits.product.repository.RoleRepository;
import com.knits.product.service.mapper.RoleMapper;
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

    public RoleDTO createNewRole(RoleDTO roleDTO) {
        log.debug("Request to save Role : {}", roleDTO);

        Role role = roleMapper.toEntity(roleDTO);

        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    /**
     * Partially updates a role.
     *
     * @param roleDTO the entity to update partially.
     * @return the persisted entity.
     */

    @Transactional
    public RoleDTO partialUpdateRoleData(RoleDTO roleDTO) {
        log.debug("Request to partially update Role : {}", roleDTO);
        Role role = roleRepository.findById(roleDTO.getRoleId()).orElseThrow(() -> new UserException("Role#" + roleDTO.getRoleId() + " not found"));
        roleMapper.partialUpdate(role, roleDTO);

        //TODO: manage Role relationship to check updates
        roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    /**
     * overrides fields, including nulls.
     *
     * @param roleDTO the entity to update.
     * @return the persisted entity.
     */

    @Transactional
    public RoleDTO updateRoleData(RoleDTO roleDTO) {
        log.debug("Request to update Role : {}", roleDTO);
        Role role = roleRepository.findById(roleDTO.getRoleId()).orElseThrow(() -> new UserException("Role#" + roleDTO.getRoleId() + " not found"));
        roleMapper.update(role, roleDTO);

        //TODO: manage role relationship to check updates
        roleRepository.save(role);
        return roleMapper.toDto(role);
    }


    public List<RoleDTO> fetchAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Get by the "id" role.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public RoleDTO getRoleById(Long id) {
        log.debug("Request User by id : {}", id);
        Role role = roleRepository.findById(id).orElseThrow(() -> new UserException("Role#" + id + "not found", ExceptionCodes.USER_NOT_FOUND));
        return roleMapper.toDto(role);
    }

    /**
     * Delete the "id" role.
     *
     * @param id the id of the entity.
     */

    public void deleteRoleDateByRoleId(Long id) {
        log.debug("Delete Role by id : {}", id);
        roleRepository.deleteById(id);
    }

    /**
     * Get all the roles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<RoleDTO> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("not yet implemented");
    }




}

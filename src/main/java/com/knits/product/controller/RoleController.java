package com.knits.product.controller;

import com.knits.product.dto.RoleDTO;
import com.knits.product.exceptions.UserException;
import com.knits.product.service.RoleService;
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
public class RoleController {
    private final RoleService roleService;

    @GetMapping(value = "/roles/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable(value = "id") Long id) {

        log.debug("REST request to get Role : {}", id);
        RoleDTO roleFound = roleService.getRoleById(id);
        return ResponseEntity.ok().body(roleFound);
    }

    @GetMapping(value = "/roles/all")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        log.debug("REST request to get all Roles");
        return ResponseEntity.ok().body(roleService.fetchAllRoles());
    }


    @PostMapping(value = "/roles")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        log.debug("REST request to createRole Roles ");
        if (roleDTO == null) {
            throw new UserException("User data are missing");
        }
        return ResponseEntity.ok().body(roleService.createNewRole(roleDTO));
    }

    @PatchMapping(value = "/roles/{id}")
    public ResponseEntity<RoleDTO> partialUpdateRole(@PathVariable(value = "id", required = false) Long id,
                                                     @RequestBody RoleDTO roleDTO) {
        log.debug("REST request to updateRole Role ");

        if (roleDTO == null) {
            throw new UserException("Role data are missing");
        }
        roleDTO.setRoleId(id);
        return ResponseEntity.ok().body(roleService.partialUpdateRoleData(roleDTO));
    }


    @DeleteMapping(value = "/roles/{id}")
    public ResponseEntity<RoleDTO> partialDeleteRole(@PathVariable Long id) {
        log.debug("REST request to delete Role : {}", id);
        roleService.deleteRoleDateByRoleId(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> getAllRoles(Pageable pageable) {
        throw new UnsupportedOperationException("getAllRoles(Pageable pageable) not implemented");
    }




}

package com.knits.product.controller;

import java.util.List;
import com.knits.product.dto.RoleDto;
import com.knits.product.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") Long roleId) {

        log.debug("REST request to get Role : {}", roleId);
        RoleDto roleFound = roleService.getRoleById(roleId);
        return ResponseEntity.ok().body(roleFound);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        log.debug("REST request to get all Roles");
        return ResponseEntity.ok().body(roleService.fetchAllRoles());
    }


    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDTO) {
        log.debug("REST request to createRole Roles ");
        return ResponseEntity.ok().body(roleService.createNewRole(roleDTO));
    }

    @PatchMapping
    public ResponseEntity<RoleDto> partialUpdateRole(@RequestBody RoleDto roleDto) {
        log.debug("REST request to updateRole Role ");
        return ResponseEntity.ok().body(roleService.partialUpdateRoleData(roleDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RoleDto> deleteRoleById(@PathVariable("id") Long roleId) {
        log.debug("REST request to delete Role : {}", roleId);
        roleService.deleteRoleDataByRoleId(roleId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles(Pageable pageable) {
        throw new UnsupportedOperationException("getAllRoles(Pageable pageable) not implemented");
    }

}

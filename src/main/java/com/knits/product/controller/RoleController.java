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
@RequestMapping("/api")
public class RoleController {

    private final RoleService roleService;

    @GetMapping(value = "/getrole")
    public ResponseEntity<RoleDto> getRoleById(@RequestBody RoleDto roleDto) {

        log.debug("REST request to get Role : {}", roleDto.getId());
        RoleDto roleFound = roleService.getRoleById(roleDto);
        return ResponseEntity.ok().body(roleFound);
    }

    @GetMapping(value = "/roles/all")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        log.debug("REST request to get all Roles");
        return ResponseEntity.ok().body(roleService.fetchAllRoles());
    }


    @PostMapping(value = "/createrole")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDTO) {
        log.debug("REST request to createRole Roles ");
        return ResponseEntity.ok().body(roleService.createNewRole(roleDTO));
    }

    @PatchMapping(value = "/partialroleupdate")
    public ResponseEntity<RoleDto> partialUpdateRole(@RequestBody RoleDto roleDto) {
        log.debug("REST request to updateRole Role ");
        return ResponseEntity.ok().body(roleService.partialUpdateRoleData(roleDto));
    }


    @DeleteMapping(value = "/deleterole")
    public ResponseEntity<RoleDto> deleteRoleById(@RequestBody RoleDto roleDto) {
        log.debug("REST request to delete Role : {}", roleDto.getId());
        roleService.deleteRoleDataByRoleId(roleDto);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> getAllRoles(Pageable pageable) {
        throw new UnsupportedOperationException("getAllRoles(Pageable pageable) not implemented");
    }

}

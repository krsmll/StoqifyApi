package com.knits.product.controller;

import com.knits.product.dto.GroupDto;
import com.knits.product.exceptions.SystemException;
import com.knits.product.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/groups")
public class GroupController {

    private GroupService groupService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        log.debug("REST request to getAllGroups");

        return ResponseEntity.ok().body(groupService.fetchAllGroups());
    }

    @GetMapping(value = "/active")
    public ResponseEntity<List<GroupDto>> getActiveGroups() {
        log.debug("REST request to getActiveGroups");

        return ResponseEntity.ok().body(groupService.getActiveGroups());
    }

    @GetMapping(value = "/inactive")
    public ResponseEntity<List<GroupDto>> getInactiveGroups() {
        log.debug("REST request to getInactiveGroups");

        return ResponseEntity.ok().body(groupService.getInactiveGroups());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable long id) {
        log.debug("REST request to getGroupById : " + id);

        return ResponseEntity.ok().body(groupService.getGroupById(id));
    }

    @GetMapping(value = "/search/{text}")
    public ResponseEntity<List<GroupDto>> findGroupsByName(@PathVariable String text) {
        log.debug("REST request to findGroupsByName : " + text);

        return ResponseEntity.ok().body(groupService.findGroupsByName(text));
    }

    @PostMapping(value = "")
    public ResponseEntity<GroupDto> createGroup(@Validated(GroupDto.InsertGroup.class) @RequestBody GroupDto groupDto, HttpServletRequest request) {
        log.debug("REST request to createGroup");

        GroupDto createdGroup = groupService.createGroup(groupDto);
        URI uri;
        try {
            uri = new URI(request.getRequestURL().append("/").append(createdGroup.getId()).toString());
        } catch (URISyntaxException e) {
            throw new SystemException(e);
        }

        return ResponseEntity.created(uri).body(createdGroup);
    }

    @PutMapping(value = "")
    public ResponseEntity<GroupDto> updateGroup(@Validated(GroupDto.UpdateGroup.class) @RequestBody GroupDto groupDto, HttpServletRequest request, HttpServletResponse response) {
        log.debug("REST request to updateGroup");

        GroupDto updatedGroup = groupService.updateGroup(groupDto);
        response.setHeader("Location", request.getRequestURL().append("/").append(updatedGroup.getId()).toString());

        return ResponseEntity.ok().body(updatedGroup);
    }

    @PatchMapping(value = "")
    public ResponseEntity<GroupDto> partialUpdateGroup(@Validated(GroupDto.UpdateGroup.class) @RequestBody GroupDto groupPartialDto, HttpServletRequest request, HttpServletResponse response) {
        log.debug("REST request to partialUpdateGroup");

        GroupDto groupDto = groupService.partialUpdateGroup(groupPartialDto);
        response.setHeader("Location", request.getRequestURL().append("/").append(groupDto.getId()).toString());

        return ResponseEntity.ok().body(groupDto);
    }

    @PutMapping(value = "/{id}/activate")
    public ResponseEntity<Void> activateGroup(@PathVariable long id) {
        log.debug("REST request to activateGroup : " + id);

        groupService.setGroupStatus(id, true);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/deactivate")
    public ResponseEntity<Void> deactivateGroup(@PathVariable long id) {
        log.debug("REST request to deactivateGroup : " + id);

        groupService.setGroupStatus(id, false);

        return ResponseEntity.noContent().build();
    }
}

package com.knits.product.service;

import com.knits.product.dto.GroupDto;
import com.knits.product.entity.Group;
import com.knits.product.exceptions.UserException;
import com.knits.product.GroupRepository;
import com.knits.product.mapper.GroupMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class GroupService {
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;

    public List<GroupDto> fetchAllGroups() {
        log.debug("Request to get all Groups");

        return groupRepository.findAll().stream().map(groupMapper::toDto).collect(Collectors.toList());
    }

    public GroupDto getGroupById(long id) {
        log.debug("Request Group by id : {}", id);

        Optional<Group> group = groupRepository.findById(id);

        return groupMapper.toDto(group.orElseThrow(() ->
                new UserException(String.format("Could not find Group with an ID: %s.", id), 404))
        );
    }

    public List<GroupDto> findGroupsByName(String name) {
        log.debug("Request Group by name : {}", name);

        List<Group> groups = groupRepository.findByNameContainingIgnoreCase(name);
        if (groups.size() == 0) {
            throw new UserException(String.format("Could not find any groups containing: %s.", name), 404);
        }
        return groups.stream().map(groupMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public GroupDto updateGroup(GroupDto groupDto) {
        log.debug("Request to update Group : {}", groupDto);

        Group group = groupRepository.findById(groupDto.getId()).orElseThrow(() ->
                new UserException(String.format("Could not find Group with an ID: %s.", groupDto.getId()), 404)
        );

        groupMapper.update(group, groupDto);

        return groupMapper.toDto(groupRepository.save(group));
    }

    @Transactional
    public GroupDto partialUpdateGroup(GroupDto groupDto) {
        log.debug("Request to partially update Group : {}", groupDto);

        Group group = groupRepository.findById(groupDto.getId()).orElseThrow(() ->
                new UserException(String.format("Could not find Group with an ID: %s.", groupDto.getId()), 404)
        );

        groupMapper.partialUpdate(group, groupDto);

        return groupMapper.toDto(groupRepository.save(group));
    }

    @Transactional
    public GroupDto createGroup(GroupDto groupDto) {
        log.debug("Request to create Group : {}", groupDto);

        Group group = groupMapper.toEntity(groupDto);

        if (group.getCreatedAt() == null) {
            group.setCreatedAt(new Date());
        }

        if (group.getIsActive() == null) {
            group.setIsActive(true);
        }

        return groupMapper.toDto(groupRepository.save(group));
    }

    @Transactional
    public void setGroupStatus(long id, boolean status) {
        log.debug("Request to change Group with an ID of {} to status : {}", id, status);

        Group group = groupRepository.findById(id).orElseThrow(() ->
                new UserException(String.format("Could not find Group with an ID: %s.", id), 404)
        );
        group.setIsActive(status);

        groupRepository.save(group);
    }

    public List<GroupDto> getActiveGroups() {
        log.debug("Request for all active Groups");

        return groupRepository.findByIsActiveTrue().stream().map(groupMapper::toDto).collect(Collectors.toList());
    }

    public List<GroupDto> getInactiveGroups() {
        log.debug("Request for all inactive Groups");

        return groupRepository.findByIsActiveFalse().stream().map(groupMapper::toDto).collect(Collectors.toList());
    }
}

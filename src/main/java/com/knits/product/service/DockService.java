package com.knits.product.service;

import java.util.List;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;
import com.knits.product.entity.User;
import com.knits.product.dto.DockDto;
import com.knits.product.mapper.DockMapper;
import org.springframework.stereotype.Service;
import com.knits.product.exceptions.UserException;
import com.knits.product.repository.UserRepository;
import com.knits.product.repository.DockRepository;

/**
 * This service class is responsible for handling data regarding to dock
 * @author Soumen Banerjee
 */
@Service("dock")
@AllArgsConstructor
public class DockService {

    private final DockMapper dockMapper;
    private final UserRepository userRepository;
    private final DockRepository dockRepository;

    /**
     *
     * @return list of docks
     */
    public List<DockDto> getAllDocks() {
        return dockRepository.findAll().stream().map(dockMapper::convertToDockDto).collect(Collectors.toList());
    }

    /**
     *
     * @param dockData requested dock data to create a new dock
     * @return list of dock list
     */
    public List<DockDto> createNewDock(DockDto dockData) {
        User getSupervisorData = userRepository.findById(dockData.getSupervisorId())
                .orElseThrow(() -> new UserException("User # " + dockData.getSupervisorId() + " not found"));

        if(!getSupervisorData.getRole().getRoleName().equals("Supervisor")) {
            throw new UserException("User is not Supervisor");
        }

        return dockRepository.findAll().stream().map(dockMapper::convertToDockDto).collect(Collectors.toList());
    }
}

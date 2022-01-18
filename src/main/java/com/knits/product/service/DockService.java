package com.knits.product.service;

import java.util.List;
import java.util.stream.Collectors;

import com.knits.product.entity.Dock;
import lombok.AllArgsConstructor;
import com.knits.product.dto.DockDto;
import com.knits.product.mapper.DockMapper;
import org.springframework.stereotype.Service;
import com.knits.product.repository.DockRepository;

/**
 * This service class is responsible for handling data regarding to dock
 * @author Soumen Banerjee
 */
@Service("dock")
@AllArgsConstructor
public class DockService {

    private final DockMapper dockMapper;
    private final DockRepository dockRepository;

    /**
     *
     * @return list of docks
     */
    public List<DockDto> getAllDocks() {
        return dockRepository.findAll().stream().map(dockMapper::convertToDockDto).collect(Collectors.toList());
    }
}

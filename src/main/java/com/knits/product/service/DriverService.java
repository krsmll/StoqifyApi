package com.knits.product.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import com.knits.product.dto.DriverDto;
import org.springframework.stereotype.Service;
import com.knits.product.mapper.DriverCarrierMapper;
import com.knits.product.repository.DriverRepository;

@Slf4j
@Service
@AllArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final DriverCarrierMapper driverCarrierMapper;

    /**
     * @return DriverDto list
     */
    public List<DriverDto> getAllDrivers() {
        return driverCarrierMapper.toDriverDtoList(driverRepository.findAll());
    }

    /**
     * @param driverDto
     * @return void
     */
    public void saveDriverData(DriverDto driverDto) {
        driverRepository.save(driverCarrierMapper.toEntity(driverDto));
    }
}

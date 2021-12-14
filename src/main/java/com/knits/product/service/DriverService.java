package com.knits.product.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import com.knits.product.dto.DriverDto;
import com.knits.product.entity.DriverCarrier;
import org.springframework.stereotype.Service;
import com.knits.product.exceptions.UserException;
import com.knits.product.mapper.DriverCarrierMapper;
import com.knits.product.repository.DriverRepository;

/**
 * This is a driver handler service class
 * @author Soumen Banerjee
 */
@Slf4j
@Service("driver")
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
     * @param driverDto save driver data
     * @return void
     */
    public void saveDriverData(DriverDto driverDto) {
        driverRepository.save(driverCarrierMapper.toEntity(driverDto));
    }

    /**
     *
     * @param driverId driver data by requested id
     * @return driver data
     */
    public DriverDto getDriverById(Long driverId) {
        DriverCarrier getDriverCarrier = driverRepository.findById(driverId)
                .orElseThrow(() -> new UserException("Driver# " + driverId + " not found"));

        return driverCarrierMapper.toDriverDto(getDriverCarrier);
    }

    /**
     *
     * @param driverId requested driver id to delete driver data
     */
    public void deleteDriverById(Long driverId) {
        driverRepository.deleteById(driverId);
    }

    /**
     *
     * @param driverDto requested driver dto to update driver data
     * @return updated driver list
     */
    public List<DriverDto> getUpdatedDriverList(DriverDto driverDto) {
        DriverCarrier getDriverData = driverRepository
                .findById(driverDto.getId()).orElseThrow(() -> new UserException("Driver# " + driverDto.getId() + " not found"));

        driverCarrierMapper.updateDriverData(getDriverData, driverDto);
        driverRepository.save(getDriverData);
        return driverCarrierMapper.toDriverDtoList(driverRepository.findAll());
    }
}

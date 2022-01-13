package com.knits.product.service;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;
import com.knits.product.dto.CarrierDto;
import com.knits.product.dto.CompanyType;
import org.springframework.stereotype.Service;
import com.knits.product.mapper.CarrierMapper;
import com.knits.product.repository.CompanyRepository;

/**
 * This is company service class
 * @author Soumen Banerjee
 */
@AllArgsConstructor
@Service("carrier")
public class CarrierService {

    private final CarrierMapper carrierMapper;
    private final CompanyRepository companyRepository;

    /**
     *
     * @return all carrier
     */
    public List<CarrierDto> getAllCarrier() {
        return companyRepository.findAll().stream()
                .filter(getCompany -> Objects.equals(getCompany.getCompanyType(), CompanyType.CARRIER.toString()))
                .map(carrierMapper::toCarrierDto)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param carrierDto requested carrier data to create a new carrier
     * @return list of all carriers even newly added carrier
     */
    public List<CarrierDto> registerCarrier(CarrierDto carrierDto) {
        carrierDto.setCompanyType(CompanyType.CARRIER);
        carrierDto.setActive(true);
        companyRepository.save(carrierMapper.toCarrierEntity(carrierDto));
        return getAllCarrier();
    }
}

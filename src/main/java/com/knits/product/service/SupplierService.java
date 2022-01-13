package com.knits.product.service;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;
import com.knits.product.dto.CompanyType;
import com.knits.product.dto.CustomerDto;
import com.knits.product.dto.SupplierDto;
import org.springframework.stereotype.Service;
import com.knits.product.mapper.SupplierMapper;
import com.knits.product.repository.CompanyRepository;

/**
 * This is company service class
 * @author Soumen Banerjee
 */
@AllArgsConstructor
@Service("supplier")
public class SupplierService {

    private final SupplierMapper supplierMapper;
    private final CompanyRepository companyRepository;

    /**
     *
     * @return all suppliers
     */
    public List<SupplierDto> getAllSuppliers() {
        return companyRepository.findAll().stream()
                .filter(getCompany -> Objects.equals(getCompany.getCompanyType(), CompanyType.SUPPLIER.toString()))
                .map(supplierMapper::toSupplierDto)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param supplierDto requested supplier data to create a new supplier
     * @return list of all suppliers even newly added supplier
     */
    public List<SupplierDto> registerSupplier(SupplierDto supplierDto) {
        supplierDto.setCompanyType(CompanyType.SUPPLIER);
        supplierDto.setActive(true);
        companyRepository.save(supplierMapper.toCompanyEntity(supplierDto));
        return getAllSuppliers();
    }
}

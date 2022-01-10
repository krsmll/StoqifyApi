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
     * @return all customers
     */
    public List<CustomerDto> getAllSuppliers() {
        return companyRepository.findAll().stream()
                .filter(getCompany -> Objects.equals(getCompany.getCompanyType(), CompanyType.SUPPLIER.toString()))
                .map(supplierMapper::toCustomerDto)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param supplierDto requested supplier data to create a new customer
     * @return list of all customers even newly added customer
     */
    public List<CustomerDto> registerSupplier(SupplierDto supplierDto) {
        supplierDto.setCompanyType(CompanyType.SUPPLIER);
        supplierDto.setActive(true);
        companyRepository.save(supplierMapper.toCompanyEntity(supplierDto));
        return getAllSuppliers();
    }
}

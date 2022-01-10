package com.knits.product.mapper;

import org.mapstruct.Mapper;
import com.knits.product.entity.Company;
import com.knits.product.dto.CustomerDto;
import com.knits.product.dto.SupplierDto;

/**
 * This is Customer mapper interface to map dto to entity and vise versa
 * @author Soumen Banerjee
 */
@Mapper(componentModel = "spring")
public interface SupplierMapper {

    CustomerDto toCustomerDto(Company company);
    Company toCompanyEntity(SupplierDto customerDto);
}

package com.knits.product.mapper;

import org.mapstruct.Mapper;
import com.knits.product.entity.Company;
import com.knits.product.dto.CustomerDto;

/**
 * This is Customer mapper interface to map dto to entity and vise versa
 * @author Soumen Banerjee
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toCustomerDto(Company company);
    Company toCompanyEntity(CustomerDto customerDto);
}

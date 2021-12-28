package com.knits.product.mapper;

import org.mapstruct.Mapper;
import com.knits.product.dto.CustomerDto;
import com.knits.product.entity.Company;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toCustomerDto(Company company);
    Company toCompanyEntity(CustomerDto customerDto);
}

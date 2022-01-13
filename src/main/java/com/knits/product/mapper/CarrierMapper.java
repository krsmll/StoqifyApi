package com.knits.product.mapper;

import com.knits.product.dto.CarrierDto;
import com.knits.product.dto.SupplierDto;
import com.knits.product.entity.Company;
import org.mapstruct.Mapper;

/**
 * This is Customer mapper interface to map dto to entity and vise versa
 * @author Soumen Banerjee
 */
@Mapper(componentModel = "spring")
public interface CarrierMapper {

    CarrierDto toCarrierDto(Company company);
    Company toCarrierEntity(CarrierDto customerDto);
}

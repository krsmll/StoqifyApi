package com.knits.product.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.knits.product.dto.CompanyDto;
import com.knits.product.entity.Company;
import org.mapstruct.MappingTarget;

/**
 * This is a mapper interface to map Company dto to entity and vise versa
 * @author Soumen Banerjee
 */
@Mapper(componentModel = "spring")
public interface CompanyMapper {

    List<CompanyDto> toCompanyDtoList(List<Company> companyList);
    Company toCompanyEntity(CompanyDto companyDto);
    void updateCompanyData(@MappingTarget Company company, CompanyDto companyDto);
    CompanyDto toCompanyDto(Company company);
}

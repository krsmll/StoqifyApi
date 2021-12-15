package com.knits.product.service;

import java.util.List;
import com.knits.product.entity.Company;
import com.knits.product.exceptions.UserException;
import lombok.AllArgsConstructor;
import com.knits.product.dto.CompanyDto;
import com.knits.product.mapper.CompanyMapper;
import com.knits.product.repository.CompanyRepository;
import org.springframework.stereotype.Service;

/**
 * This is a Company service class to process all company realted request
 * @author Soumen Banerjee
 */
@Service("company")
@AllArgsConstructor
public class CompanyService {

    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    /**
     *
     * @return list of companies
     */
    public List<CompanyDto> getAllCompanies() {
        return companyMapper.toCompanyDtoList(companyRepository.findAll());
    }

    /**
     *
     * @param companyDto requested company data to create a company
     * @return list of companies
     */
    public List<CompanyDto> getSavedCompanyWithOldCompanies(CompanyDto companyDto) {
        companyRepository.save(companyMapper.toCompanyEntity(companyDto));

        return companyMapper.toCompanyDtoList(companyRepository.findAll());
    }

    /**
     *
     * @param companyDto requested company for deactivating
     */
    public void deactivateCompany(CompanyDto companyDto) {
        Company getCompany = companyRepository.findById(companyDto.getId())
                .orElseThrow(() -> new UserException("Comapmy#" + companyDto.getId() + " not found"));

        companyMapper.updateCompanyData(getCompany, companyDto);
        companyRepository.save(getCompany);
    }

    /**
     *
     * @return list of companies
     */
    public List<CompanyDto> getCompanyListAfterEdit(CompanyDto companyDto) {
        Company getCompanyData = companyRepository.findById(companyDto.getId())
                .orElseThrow(() -> new UserException("Company# " + companyDto.getId() + " not found"));

        companyMapper.updateCompanyData(getCompanyData, companyDto);
        companyRepository.save(getCompanyData);

        return companyMapper.toCompanyDtoList(companyRepository.findAll());
    }

    /**
     *
     * @param registryCode requested registry code to search the company
     * @return single company data
     */
    public CompanyDto getCompanyBySearchedName(String registryCode) {
        Company getSearchedCompany = companyRepository.findByRegistryCode(registryCode)
                .orElseThrow(() -> new UserException("Company# " + registryCode + " not found"));

        return companyMapper.toCompanyDto(getSearchedCompany);
    }
}

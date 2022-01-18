package com.knits.product.service;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;
import com.knits.product.dto.CustomerDto;
import com.knits.product.dto.CompanyType;
import org.springframework.stereotype.Service;
import com.knits.product.mapper.CustomerMapper;
import com.knits.product.repository.CompanyRepository;

/**
 * This is company service class
 * @author Soumen Banerjee
 */
@AllArgsConstructor
@Service("customer")
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CompanyRepository companyRepository;

    /**
     *
     * @return all customers
     */
    public List<CustomerDto> getAllCustomers() {
        return companyRepository.findAll().stream()
                .filter(getCompany -> Objects.equals(getCompany.getCompanyType(), CompanyType.CUSTOMER.toString()))
                .map(customerMapper::toCustomerDto)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param customerDto requested customer data to create a new customer
     * @return list of all customers even newly added customer
     */
    public List<CustomerDto> registerCustomer(CustomerDto customerDto) {
        customerDto.setCompanyType(CompanyType.CUSTOMER);
        customerDto.setActive(true);
        companyRepository.save(customerMapper.toCompanyEntity(customerDto));
        return getAllCustomers();
    }
}

package com.knits.product.service;

import java.util.List;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;
import com.knits.product.dto.CustomerDto;
import org.springframework.stereotype.Service;
import com.knits.product.mapper.CustomerMapper;
import com.knits.product.repository.CompanyRepository;


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
                .filter(getCompany -> getCompany.getCompanyType().equals("Customer"))
                .map(customerMapper::toCustomerDto)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param customerDto requested customer data to create a new customer
     * @return list of all customers even newly added customer
     */
    public List<CustomerDto> registerCustomer(CustomerDto customerDto) {
        customerDto.setCompanyType("0");
        companyRepository.save(customerMapper.toCompanyEntity(customerDto));
        return getAllCustomers();
    }
}

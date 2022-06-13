package com.project.restbrewery.services;

import com.project.restbrewery.web.model.CustomerDto;


public interface CustomerService {
    CustomerDto getCustomerById(Integer customerId);

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    void updateCustomer(Integer customerId, CustomerDto customerDto);

    void deleteById(Integer customerId);
}

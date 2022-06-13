package com.project.restbrewery.services;

import com.project.restbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {


    @Override
    public CustomerDto getCustomerById(Integer customerId) {
        return null;
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        return null;
    }


    @Override
    public void updateCustomer(Integer customerId, CustomerDto customerDto) {
        log.debug("Updating....");
    }

    @Override
    public void deleteById(Integer customerId) {
        log.debug("Deleting.... ");
    }
}

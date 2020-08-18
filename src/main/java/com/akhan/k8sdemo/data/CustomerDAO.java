package com.akhan.k8sdemo.data;

import com.akhan.k8sdemo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDAO {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findByFirstName(String firstName){
        return customerRepository.findCustomersByFirstName(firstName);
    }

    public void create(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findByPhone(String phone){
        return customerRepository.findCustomersByPhone(phone);
    }

    public Optional<Customer> findById(String id){
        return customerRepository.findById(id);
    }
}

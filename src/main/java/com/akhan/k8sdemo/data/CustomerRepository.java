package com.akhan.k8sdemo.data;

import com.akhan.k8sdemo.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    List<Customer> findCustomersByFirstName(String firstName);

    Customer findCustomersByPhone(String phone);

    Customer findCustomerById(String id);

}

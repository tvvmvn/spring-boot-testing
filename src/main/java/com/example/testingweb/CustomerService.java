package com.example.testingweb;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository repository;

  public CustomerService(CustomerRepository repository) {
    this.repository = repository;
  }

  public List<Customer> findAllCustomers() {
    return repository.findAll();
  }

	public Customer findCustomerById(long id) {
		return repository.findById(id);
	}

  public Customer saveCustomer(String firstName, String lastName) {
    return repository.save(new Customer(firstName, lastName));
  }
}
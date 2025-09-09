package com.example.testingweb;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {

	private final CustomerService service;

	public CustomerController(CustomerService service) {
		this.service = service;
	}

  @GetMapping("/customers")
  public List<Customer> getCustomers() {
    return service.findAllCustomers();
  }

  @GetMapping("/customers/{id}")
  public Customer getCustomerById(@PathVariable long id) {
    return service.findCustomerById(id);
  }

  @PostMapping("/customers")
  public Customer addCustomer(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
    return service.saveCustomer(firstName, lastName);
  }
}
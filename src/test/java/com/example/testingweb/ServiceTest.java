package com.example.testingweb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

  @Mock
  private CustomerRepository repository;
  
  // 만들어진 Mock 객체를 주입 할 대상 객체를 선언합니다. @InjectMocks 어노테이션을 이용하여 
  @InjectMocks
	private CustomerService service;

  @Test
	void contextLoads() throws Exception {
		assertThat(service).isNotNull();
	}

  @Test
  void findAllCustomers() throws Exception {

    List<Customer> customers = new ArrayList<Customer>();
    customers.add(new Customer("John", "Doe"));
    customers.add(new Customer("Jane", "Doe"));

    when(repository.findAll())
      .thenReturn(customers);

    List<Customer> results = service.findAllCustomers();

    assertEquals(results.size(), 2);
  }

  @Test
  void findCustomerById() throws Exception {
    
    // record in db
    Customer customer = new Customer("John", "Doe");
    ReflectionTestUtils.setField(customer, "id", 1L);

    when(repository.findById(1L))
      .thenReturn(customer);

    Customer result = service.findCustomerById(1L);

    assertEquals(result.getFirstName(), customer.getFirstName());
  }

  @Test
  void saveCustomer() throws Exception {
    
    Customer customer = new Customer("John", "Doe");

    when(repository.save(any()))
      .thenReturn(customer);

    Customer saved = service.saveCustomer("John", "Doe");

    assertEquals(saved.getFirstName(), customer.getFirstName());
  }
} 
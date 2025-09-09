package com.example.testingweb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/*
 * We can narrow the tests to only the web layer by using @WebMvcTest.
 * 
 * The test assertion is the same as in the previous case. 
 * However, in this test, Spring Boot instantiates only the web layer 
 * rather than the whole context. 
 * 
 * In an application with multiple controllers, 
 * you can even ask for only one to be instantiated by using, 
 * for example, @WebMvcTest(HomeController.class).
 */

@WebMvcTest(CustomerController.class) // on web layer (controller)
class ControllerTest {
  
  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private CustomerService service;

  @Test
  void getCustomers() throws Exception {

    List<Customer> customers = new ArrayList<>();
    customers.add(new Customer("John", "Doe"));
    customers.add(new Customer("Jane", "Doe"));

    // when
    when(service.findAllCustomers())
      .thenReturn(customers);
    
    this.mockMvc
      .perform(get("/customers"))
      .andDo(print())
      .andExpect(status().isOk());
  }

  @Test
  void getCustomerById() throws Exception {
    
    Customer customer = new Customer("John", "Doe");

    when(service.findCustomerById(1))
      .thenReturn(customer);

    this.mockMvc
      .perform(get("/customers/1"))
      .andDo(print())
      .andExpect(status().isOk());
  }

  @Test
  void addCustomer() throws Exception {

    // params
    String firstName = "John";
    String lastName = "Doe";

    when(service.saveCustomer(firstName, lastName))
      .thenReturn(new Customer(firstName, lastName));
    
    this.mockMvc
      .perform(post("/customers")
        .param("firstName", firstName)
        .param("lastName", lastName))
      .andDo(print())
      .andExpect(status().isOk());
  }
} 
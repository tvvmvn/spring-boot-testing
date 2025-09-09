package com.example.testingweb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// the full Spring application context is started 
// but without the server.
@SpringBootTest
@AutoConfigureMockMvc // server not needed
class ApplicationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CustomerController controller;
  	
  @Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

  @Test
  void getCustomers() throws Exception {
    
    this.mockMvc
      .perform(get("/customers"))
      .andDo(print())
      .andExpect(status().isOk());
  }
  
  @Test
  void getCustomerById() throws Exception {
    
    this.mockMvc
      .perform(get("/customers/1"))
      .andDo(print())
      .andExpect(status().isOk());
  }

  @Test
  void addCustomer() throws Exception {

    String firstName = "John";
    String lastName= "Doe";
    
    this.mockMvc
      .perform(post("/customers")
        .param("firstName", firstName)
        .param("lastName", lastName))
      .andDo(print())
      .andExpect(status().isOk());
  }
}

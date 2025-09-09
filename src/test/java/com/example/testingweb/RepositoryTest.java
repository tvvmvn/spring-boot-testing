package com.example.testingweb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RepositoryTest {
  
  @Autowired
  private CustomerRepository repository;

  @Test
  void contextLoads() throws Exception {
    assertThat(repository).isNotNull();
  }

  @Test
  void findAll() {
      
      repository.save(new Customer("John", "Doe"));
      repository.save(new Customer("Jane", "Doe"));

      assertThat(repository.findAll().size())
        .isEqualTo(2);
  }

  @Test
  void findById() {

    Customer customer = repository.save(new Customer("John", "Doe"));
    Customer record = repository.findById(customer.getId());

    // compare first name
    assertEquals(customer.getFirstName(), record.getFirstName());
    // compare last name
    assertEquals(customer.getLastName(), record.getLastName());
  }

  @Test
  @DisplayName("SAVE TEST")
  void save() {

    Customer c = new Customer("John", "Doe");
    Customer customer = repository.save(c);

    assertThat(customer.getId()).isNotNull();
    assertEquals(customer.getFirstName(), "John");
    assertEquals(customer.getLastName(), "Doe");
    assertEquals(repository.count(), 1);
  }
}

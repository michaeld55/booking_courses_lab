package com.example.codeclan.lab.repositories;

import com.example.codeclan.lab.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //    Get all customers for a given course
    List<Customer> findByBookingsCourseNameIgnoreCase(String name);

    //    Get all customers in a given town for a given course
    List<Customer> findByTownIgnoreCaseAndBookingsCourseNameIgnoreCase(String town, String courseName );
    //    Get all customers over a certain age in a given town. for a given course
    List<Customer> findByAgeGreaterThanAndTownIgnoreCaseAndBookingsCourseNameIgnoreCase(int age, String town, String courseName);
}

package com.example.codeclan.lab.controllers;

import com.example.codeclan.lab.models.Course;
import com.example.codeclan.lab.models.Customer;
import com.example.codeclan.lab.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    //    Get all customers for a given course

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(name = "courseName", required = false) String courseName,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "town", required = false) String town
    ){
        if (age != null && town != null && courseName != null){
            return new ResponseEntity<>(customerRepository.findByAgeGreaterThanAndTownIgnoreCaseAndBookingsCourseNameIgnoreCase(age, town, courseName), HttpStatus.OK);
        }

        if (town != null && courseName != null ){
            return new ResponseEntity<>(customerRepository.findByTownIgnoreCaseAndBookingsCourseNameIgnoreCase(town, courseName), HttpStatus.OK);
        }

        if (courseName != null){
            return new ResponseEntity<>(customerRepository.findByBookingsCourseNameIgnoreCase(courseName), HttpStatus.OK);
        }

        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    //    Get all customers in a given town for a given course
    //    Get all customers over a certain age in a given town. for a given course

    @GetMapping("/{id}")
    public ResponseEntity getCustomers(@PathVariable Long id){
        return new ResponseEntity<>(customerRepository, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> postCustomers(@RequestBody Customer customer){
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> putCustomers(@RequestBody Customer customer, @PathVariable Long id) {
        if (customer.getId().longValue() != id){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<Customer>> deleteCustomers(@PathVariable Long id){
        customerRepository.deleteById(id);
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }
}

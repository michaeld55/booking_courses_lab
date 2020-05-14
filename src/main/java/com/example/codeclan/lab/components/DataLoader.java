package com.example.codeclan.lab.components;

import com.example.codeclan.lab.models.Booking;
import com.example.codeclan.lab.models.Course;
import com.example.codeclan.lab.models.Customer;
import com.example.codeclan.lab.repositories.BookingRepository;
import com.example.codeclan.lab.repositories.CourseRepository;
import com.example.codeclan.lab.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CourseRepository courseRepository;

    public DataLoader() {
    }

    public void run(ApplicationArguments args){
        Customer customer1 = new Customer("Sam", "Edinburgh", 30);
        customerRepository.save(customer1);
        Course course1 = new Course("Advanced React", "Edinburgh", 4);
        courseRepository.save(course1);
        Booking booking1 = new Booking("14/05/2020", course1, customer1);
        bookingRepository.save(booking1);

        course1.addBooking(booking1);
        courseRepository.save(course1);
        customer1.addBooking(booking1);
        customerRepository.save(customer1);
    }
}

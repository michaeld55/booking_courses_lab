package com.example.codeclan.lab.repositories;

import com.example.codeclan.lab.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    //    Get all courses with a given rating
    List<Course> findByRating( int rating);
    //    Get all courses for a given customer
    List<Course> findByBookingsCustomerNameIgnoreCase(String name);
}

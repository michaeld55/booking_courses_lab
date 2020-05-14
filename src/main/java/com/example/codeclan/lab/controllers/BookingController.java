package com.example.codeclan.lab.controllers;

import com.example.codeclan.lab.models.Booking;
import com.example.codeclan.lab.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

//    Get all bookings for a given date
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings(
            @RequestParam(name = "date", required = false) String date
    ){
        if (date != null){
            return new ResponseEntity<>(bookingRepository.findByDate(date), HttpStatus.OK);
        }
        return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getCourses(@PathVariable Long id){
        return new ResponseEntity<>(bookingRepository, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Booking> postCourses(@RequestBody Booking booking){
        bookingRepository.save(booking);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Booking> putCourses(@RequestBody Booking booking, @PathVariable Long id) {
        if (booking.getId().longValue() != id){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookingRepository.save(booking);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<Booking>> deleteCourses(@PathVariable Long id){
        bookingRepository.deleteById(id);
        return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
    }
}

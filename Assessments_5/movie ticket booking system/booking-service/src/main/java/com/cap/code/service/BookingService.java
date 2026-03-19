package com.cap.code.service;


import org.springframework.stereotype.Service;

import com.cap.code.client.MovieClient;
import com.cap.code.model.Booking;
import com.cap.code.model.BookingRequest;
import com.cap.code.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class BookingService {

 
    private final MovieClient movieClient;

    private final List<Booking> bookings = new ArrayList<>();

    private final AtomicLong bookingIdCounter = new AtomicLong(100);

    public BookingService(MovieClient movieClient) {
        this.movieClient = movieClient;
    }
    
    public Booking createBooking(BookingRequest request) {

        MovieResponse movie = movieClient.getMovieById(request.getMovieId());

        if (movie.getPrice() == 0.0) {
            return null;
        }

        double totalAmount = request.getTickets() * movie.getPrice();

        Booking booking = new Booking(
            bookingIdCounter.getAndIncrement(),
            request.getMovieId(),
            request.getTickets(),
            totalAmount
        );
        bookings.add(booking);

        return booking;
    }
    public List<Booking> getAllBookings() {
        return bookings;
    }
}

package com.cap.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    private Long bookingId;      
    private Long movieId; 
    private int tickets; 
    private double totalAmount;
}

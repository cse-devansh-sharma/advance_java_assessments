package com.cap.code.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Long id;
    private String name;     
    private String language; 
    private double price; 
}

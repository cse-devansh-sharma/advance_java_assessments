package com.cap.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {

    private Long id;
    private String name;
    private String language;
    private double price;  
}

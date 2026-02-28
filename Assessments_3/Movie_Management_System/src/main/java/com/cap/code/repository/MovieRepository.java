package com.cap.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cap.code.model.*;

import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer>{

}

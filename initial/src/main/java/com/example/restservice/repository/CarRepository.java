package com.example.restservice.repository;

import com.example.restservice.model.Car;
import com.example.restservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository  extends JpaRepository<Car, UUID> {
}

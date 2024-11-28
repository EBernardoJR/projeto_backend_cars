package com.example.restservice.service;

import com.example.restservice.model.Car;
import com.example.restservice.model.User;
import com.example.restservice.repository.CarRepository;
import com.example.restservice.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService {
    @Autowired
    private UserRepository userRepository;

    private CarRepository carRepository;

    public List<Car> getCars() throws BadRequestException {
        return carRepository.findAll();
    }
}

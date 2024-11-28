package com.example.restservice.controller;

import com.example.restservice.dtos.CreateCarDTO;
import com.example.restservice.model.Car;
import com.example.restservice.model.User;
import com.example.restservice.repository.CarRepository;
import com.example.restservice.repository.UserRepository;
import com.example.restservice.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("cars")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/save")
    public ResponseEntity saveCar(@RequestBody @Valid CreateCarDTO createCarDTO) {
        Car car = new Car();
        car.setName(createCarDTO.name());
        car.setPlaca(createCarDTO.placa());
        car.setOwner(createCarDTO.user());

        carRepository.save(car);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/list")
    public ResponseEntity getCars() {
        try {
            List<Car> cars = carRepository.findAll();;
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }


    }
}

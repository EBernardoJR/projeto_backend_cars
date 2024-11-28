package com.example.restservice.dtos;

import com.example.restservice.model.UserRole;

public record CreateUserDTO(String login, String password, UserRole role){
}

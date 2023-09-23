package com.example.springwebfluxdemo.model.dto.request;

public record UpdateUserRequest(String name, String surname, String username, String birthPlace) {
}

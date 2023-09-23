package com.example.springwebfluxdemo.model.dto.request;

public record SaveUserRequest(String name, String surname, String username, String birthPlace) {
}

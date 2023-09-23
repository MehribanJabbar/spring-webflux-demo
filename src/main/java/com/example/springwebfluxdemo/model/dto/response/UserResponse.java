package com.example.springwebfluxdemo.model.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String name, String surname, String username, String birthPlace) {
}

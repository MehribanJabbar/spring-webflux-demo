package com.example.springwebfluxdemo.mapper;

import com.example.springwebfluxdemo.dao.entity.UserEntity;
import com.example.springwebfluxdemo.model.dto.request.SaveUserRequest;
import com.example.springwebfluxdemo.model.dto.request.UpdateUserRequest;
import com.example.springwebfluxdemo.model.dto.response.UserResponse;
import com.example.springwebfluxdemo.model.enums.Status;

import java.time.LocalDateTime;

public class UserMapper {
    public static UserResponse buildToResponse(UserEntity user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .birthPlace(user.getBirthPlace())
                .build();
    }

    public static UserEntity buildToEntity(SaveUserRequest request){
        return UserEntity.builder()
                .name(request.name())
                .surname(request.surname())
                .username(request.username())
                .birthPlace(request.birthPlace())
                .createdAt(LocalDateTime.now())
                .status(Status.ACTIVE)
                .build();
    }

    public static void updateToUser(UserEntity user, UpdateUserRequest request){
        user.setName(request.name());
        user.setSurname(request.surname());
        user.setUsername(request.username());
        user.setBirthPlace(request.birthPlace());
    }
}

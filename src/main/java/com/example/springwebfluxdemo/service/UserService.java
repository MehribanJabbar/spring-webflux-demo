package com.example.springwebfluxdemo.service;

import com.example.springwebfluxdemo.dao.entity.UserEntity;
import com.example.springwebfluxdemo.dao.repository.UserRepository;
import com.example.springwebfluxdemo.exception.NotFoundException;
import com.example.springwebfluxdemo.mapper.UserMapper;
import com.example.springwebfluxdemo.model.dto.request.SaveUserRequest;
import com.example.springwebfluxdemo.model.dto.request.UpdateUserRequest;
import com.example.springwebfluxdemo.model.dto.response.UserResponse;
import com.example.springwebfluxdemo.model.enums.Status;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;

    public Mono<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::buildToResponse)
                .switchIfEmpty(Mono.error(new NotFoundException("User not found for with id : " + id)));
    }

    public Flux<UserResponse> getAllUsers() {
        return userRepository.findAll().map(UserMapper::buildToResponse);
    }

    public Mono<Void> saveUser(SaveUserRequest request) {
        return userRepository.save(UserMapper.buildToEntity(request)).then();
    }

    public Mono<Void> updateUser(Long id, UpdateUserRequest request){
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("User not found")))
                .flatMap(user -> {
                     UserMapper.updateToUser(user, request);
                     return userRepository.save(user).then();
                });
    }

    public Mono<Void> deleteUser(Long id){
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("User not found")))
                .flatMap(user -> {
                    user.setStatus(Status.DELETED);
                    return userRepository.save(user).then();
                });
    }
}

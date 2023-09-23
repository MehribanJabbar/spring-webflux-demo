package com.example.springwebfluxdemo.controller;

import com.example.springwebfluxdemo.model.dto.request.SaveUserRequest;
import com.example.springwebfluxdemo.model.dto.request.UpdateUserRequest;
import com.example.springwebfluxdemo.model.dto.response.UserResponse;
import com.example.springwebfluxdemo.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping("/{id}")
    public Mono<UserResponse> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public Flux<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public Mono<Void> saveUser(@RequestBody SaveUserRequest request){
        return userService.saveUser(request);
    }

    @PutMapping("/{id}")
    public Mono<Void> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}

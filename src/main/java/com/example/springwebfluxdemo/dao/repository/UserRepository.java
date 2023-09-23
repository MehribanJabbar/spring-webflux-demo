package com.example.springwebfluxdemo.dao.repository;

import com.example.springwebfluxdemo.dao.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {
}

package com.jwd0526.practice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jwd0526.practice.model.User;

public interface UserRepository extends MongoRepository<User, String> {
}

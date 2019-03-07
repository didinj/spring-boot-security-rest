package com.djamware.SecurityRest.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.djamware.SecurityRest.models.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);
}

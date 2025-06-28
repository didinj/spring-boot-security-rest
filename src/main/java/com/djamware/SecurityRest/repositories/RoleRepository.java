package com.djamware.SecurityRest.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.djamware.SecurityRest.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRole(String role);
}

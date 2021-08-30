package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users,Long> {
    Users findByUsernameIgnoreCase(String username);
    Users findByEmail(String email);

}

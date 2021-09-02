package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<Users,Long> {
    Users findByUsernameIgnoreCase(String username);
    Users findByEmail(String email);

    @Override
    List<Users> findAll();
}

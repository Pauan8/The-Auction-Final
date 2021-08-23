package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

public interface UserRepository extends CrudRepository<User,Long> {
}

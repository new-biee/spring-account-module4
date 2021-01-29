package com.codegym.service;

import com.codegym.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAllByFirstNameContaining(String firstName, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    Iterable<User> findAll();

    User findById(Long id);

    void save(User user);

    void remove(Long id);
}

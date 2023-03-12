package com.ey.ejercicio.daos;

import com.ey.ejercicio.entities.User;

import java.util.List;

public interface UserDao{

    User save(User user);
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    User findByToken(String token);
    User findByEmail(String email);
    User update(User user);
    void delete(User user);

}

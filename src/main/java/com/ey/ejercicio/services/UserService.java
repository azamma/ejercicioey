package com.ey.ejercicio.services;

import com.ey.ejercicio.daos.UserDao;
import com.ey.ejercicio.dtos.UserDTO;
import com.ey.ejercicio.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User saveUser(UserDTO userDTO) {

        if (userDao.findByEmail(userDTO.getEmail()) != null) {
            throw new PersistenceException("El correo ya está registrado");
        }

        User user = new User();
        user = user.mapToUser(userDTO);


            userDao.save(user);
            return user;

    }

    public User updateUser(User user) {
        user.setModified(new Date());
        userDao.update(user);
        return user;
    }

    public User getUserById(Long id) {
        return userDao.findById(id);
    }

    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public void deleteUser(Long id) {
        User user = userDao.findById(id);
        user.setIsActive(false);
        userDao.update(user);
    }

}

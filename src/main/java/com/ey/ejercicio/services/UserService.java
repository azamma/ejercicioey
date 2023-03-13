package com.ey.ejercicio.services;

import com.ey.ejercicio.daos.UserDao;
import com.ey.ejercicio.dtos.UserDTO;
import com.ey.ejercicio.entities.User;
import com.ey.ejercicio.utils.EncryptionUtils;
import com.ey.ejercicio.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    public User saveUser(UserDTO userDTO) {

        if (userDao.findByEmail(userDTO.getEmail()) != null) {
            throw new PersistenceException("El correo ya está registrado");
        }

        User user = new User();
        user = user.mapToUser(userDTO);

        String encryptedPassword = user.getPassword();
        String token = jwtUtil.create(user.getName(), user.getEmail());

        user.setPassword(EncryptionUtils.encrypt(encryptedPassword));
        user.setToken(token);

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

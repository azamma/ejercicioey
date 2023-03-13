package com.ey.ejercicio.services;

import com.ey.ejercicio.daos.UserDao;
import com.ey.ejercicio.dtos.UserDTO;
import com.ey.ejercicio.entities.User;
import com.ey.ejercicio.utils.EncryptionUtils;
import com.ey.ejercicio.utils.JWTUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Date;

/**
 * Servicio para manejar los usuarios
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    private static final Logger logger = LogManager.getLogger(UserService.class);

    /**
     * Guarda un usuario en la base de datos
     * @param userDTO el DTO del usuario a guardar
     * @return el usuario guardado
     * @throws PersistenceException si el correo del usuario ya está registrado
     */
    public User saveUser(UserDTO userDTO) throws PersistenceException {

        // Verifica si el correo electrónico del usuario ya existe en la base de datos
        if (userDao.findByEmail(userDTO.getEmail()) != null) {
            logger.info("Usuario ya registrado");
            throw new PersistenceException("El correo ya está registrado");
        }

        // Crea un nuevo objeto User a partir del objeto UserDTO proporcionado
        User user = new User();
        user = user.mapToUser(userDTO);

        // Encripta la contraseña del usuario y crea un token JWT
        String encryptedPassword = user.getPassword();
        String token = jwtUtil.create(user.getName(), user.getEmail());
        user.setPassword(EncryptionUtils.encrypt(encryptedPassword));
        user.setToken(token);

        // Guarda el objeto User en la base de datos
        userDao.save(user);
        return user;


    }

    /**
     * Actualiza un usuario en la base de datos
     * @param user el usuario a actualizar
     * @return el usuario actualizado
     */
    public User updateUser(User user) {
        user.setModified(new Date());
        userDao.update(user);
        return user;
    }

    /**
     * Obtiene un usuario por su id
     * @param id el id del usuario a obtener
     * @return el usuario con el id especificado
     */
    public User getUserById(Long id) {
        return userDao.findById(id);
    }

    /**
     * Obtiene un usuario por su correo electrónico
     * @param email el correo electrónico del usuario a obtener
     * @return el usuario con el correo electrónico especificado
     */
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * Elimina un usuario de la base de datos
     * @param id el id del usuario a eliminar
     */
    public void deleteUser(Long id) {
        User user = userDao.findById(id);
        user.setIsActive(false);
        userDao.update(user);
    }

}

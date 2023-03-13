package com.ey.ejercicio.daos;

import com.ey.ejercicio.entities.User;

import java.util.List;

public interface UserDao {

    /**
     * Guarda un usuario en la base de datos.
     * @param user El usuario a guardar.
     * @return El usuario guardado.
     */
    User save(User user);

    /**
     * Obtiene todos los usuarios de la base de datos.
     * @return Una lista con todos los usuarios.
     */
    List<User> findAll();

    /**
     * Busca un usuario por su ID.
     * @param id El ID del usuario a buscar.
     * @return El usuario encontrado, o null si no se encuentra.
     */
    User findById(Long id);

    /**
     * Busca un usuario por su nombre de usuario.
     * @param username El nombre de usuario a buscar.
     * @return El usuario encontrado, o null si no se encuentra.
     */
    User findByUsername(String username);

    /**
     * Busca un usuario por su token de autenticación.
     * @param token El token de autenticación a buscar.
     * @return El usuario encontrado, o null si no se encuentra.
     */
    User findByToken(String token);

    /**
     * Busca un usuario por su dirección de correo electrónico.
     * @param email La dirección de correo electrónico a buscar.
     * @return El usuario encontrado, o null si no se encuentra.
     */
    User findByEmail(String email);

    /**
     * Actualiza los datos de un usuario en la base de datos.
     * @param user El usuario a actualizar.
     * @return El usuario actualizado.
     */
    User update(User user);

    /**
     * Elimina un usuario de la base de datos.
     * @param user El usuario a eliminar.
     */
    void delete(User user);

}

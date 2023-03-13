/**
 Implementación del Data Access Object (DAO) para la entidad User
 */
package com.ey.ejercicio.daosImpl;

import com.ey.ejercicio.daos.UserDao;
import com.ey.ejercicio.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Guarda un nuevo usuario en la base de datos
     * @param user el usuario a guardar
     * @return el usuario guardado
     */
    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    /**
     * Devuelve una lista de todos los usuarios en la base de datos
     * @return una lista de usuarios
     */
    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    /**
     * Busca un usuario por su ID
     * @param id el ID del usuario a buscar
     * @return el usuario encontrado o null si no existe
     */
    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    /**
     * Busca un usuario por su nombre de usuario
     * @param username el nombre de usuario a buscar
     * @return el usuario encontrado o null si no existe
     */
    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    /**
     * Busca un usuario por su token de acceso
     * @param token el token de acceso a buscar
     * @return el usuario encontrado o null si no existe
     */
    @Override
    public User findByToken(String token) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.token = :token", User.class);
        query.setParameter("token", token);
        return query.getSingleResult();
    }

    /**
     * Busca un usuario por su dirección de correo electrónico
     * @param email la dirección de correo electrónico a buscar
     * @return el usuario encontrado o null si no existe
     */
    @Override
    public User findByEmail(String email) {
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    /**
     * Actualiza un usuario existente en la base de datos
     * @param user el usuario a actualizar
     * @return el usuario actualizado
     */
    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    /**
     * Elimina un usuario de la base de datos
     * @param user el usuario a eliminar
     */
    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }


}
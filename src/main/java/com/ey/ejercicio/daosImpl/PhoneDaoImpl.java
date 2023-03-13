/**
 Implementación del Data Access Object (DAO) para la entidad Phone
 */

package com.ey.ejercicio.daosImpl;

import com.ey.ejercicio.daos.PhoneDao;
import com.ey.ejercicio.entities.Phone;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class PhoneDaoImpl implements PhoneDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Busca una lista de teléfonos a partir del id de un usuario
     * @param userId el id del usuario
     * @return una lista de teléfonos del usuario con el id proporcionado
     */
    @Override
    public List<Phone> findByUserId(Long userId) {
        TypedQuery<Phone> query = entityManager.createQuery("FROM Phone WHERE user_id = :userId", Phone.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    /**
     * Guarda un nuevo teléfono en la base de datos
     * @param phone el teléfono a guardar
     */
    @Override
    public void save(Phone phone) {
        entityManager.persist(phone);
    }

    /**
     * Actualiza un teléfono existente en la base de datos
     * @param phone el teléfono a actualizar
     */
    @Override
    public void update(Phone phone) {
        entityManager.merge(phone);
    }

    /**
     * Elimina un teléfono existente de la base de datos
     * @param phone el teléfono a eliminar
     */
    @Override
    public void delete(Phone phone) {
        entityManager.remove(entityManager.contains(phone) ? phone : entityManager.merge(phone));
    }

    /**
     * Busca un teléfono por su id en la base de datos
     * @param id el id del teléfono a buscar
     * @return el teléfono con el id proporcionado, o null si no se encuentra
     */
    @Override
    public Phone findById(Long id) {
        return entityManager.find(Phone.class, id);
    }

}

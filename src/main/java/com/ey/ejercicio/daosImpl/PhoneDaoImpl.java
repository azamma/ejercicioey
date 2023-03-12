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

    @Override
    public List<Phone> findByUserId(Long userId) {
        TypedQuery<Phone> query = entityManager.createQuery("FROM Phone WHERE user_id = :userId", Phone.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public void save(Phone phone) {
        entityManager.persist(phone);
    }


    @Override
    public void update(Phone phone) {
        entityManager.merge(phone);
    }

    @Override
    public void delete(Phone phone) {
        entityManager.remove(entityManager.contains(phone) ? phone : entityManager.merge(phone));
    }

    @Override
    public Phone findById(Long id) {
        return entityManager.find(Phone.class, id);
    }

}

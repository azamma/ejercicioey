package com.ey.ejercicio.daos;

import com.ey.ejercicio.entities.Phone;

import java.util.List;

public interface PhoneDao {
    List<Phone> findByUserId(Long userId);
    void save(Phone phone);
    void update(Phone phone);
    void delete(Phone phone);
    Phone findById(Long id);
}

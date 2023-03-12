package com.ey.ejercicio.services;

import com.ey.ejercicio.daos.PhoneDao;
import com.ey.ejercicio.entities.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneDao phoneDao;

    public Phone savePhone(Phone phone) {
        phoneDao.save(phone);
        return phone;
    }

    public Phone updatePhone(Phone phone) {
        phoneDao.update(phone);
        return phone;
    }

    public Phone getPhoneById(Long id) {
        return phoneDao.findById(id);
    }

    public List<Phone> getPhonesByUserId(Long userId) {
        return phoneDao.findByUserId(userId);
    }

    public void deletePhone(Phone phone) {
        phoneDao.delete(phone);
    }

}

package com.ey.ejercicio.services;

import com.ey.ejercicio.daos.PhoneDao;
import com.ey.ejercicio.entities.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para manejar los teléfonos
 */
@Service
public class PhoneService {

    @Autowired
    private PhoneDao phoneDao;

    /**
     * Guarda un teléfono en la base de datos
     * @param phone el teléfono a guardar
     * @return el teléfono guardado
     */
    public Phone savePhone(Phone phone) {
        phoneDao.save(phone);
        return phone;
    }

    /**
     * Actualiza un teléfono en la base de datos
     * @param phone el teléfono a actualizar
     * @return el teléfono actualizado
     */
    public Phone updatePhone(Phone phone) {
        phoneDao.update(phone);
        return phone;
    }

    /**
     * Obtiene un teléfono por su id
     * @param id el id del teléfono a obtener
     * @return el teléfono con el id especificado
     */
    public Phone getPhoneById(Long id) {
        return phoneDao.findById(id);
    }

    /**
     * Obtiene una lista de teléfonos por el id de usuario
     * @param userId el id del usuario cuyos teléfonos se quieren obtener
     * @return una lista de teléfonos pertenecientes al usuario especificado
     */
    public List<Phone> getPhonesByUserId(Long userId) {
        return phoneDao.findByUserId(userId);
    }

    /**
     * Elimina un teléfono de la base de datos
     * @param phone el teléfono a eliminar
     */
    public void deletePhone(Phone phone) {
        phoneDao.delete(phone);
    }

}

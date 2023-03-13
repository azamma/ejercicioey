package com.ey.ejercicio.daos;

import com.ey.ejercicio.entities.Phone;

import java.util.List;

public interface PhoneDao {
    /**
     * Busca y devuelve la lista de teléfonos asociados a un usuario específico.
     *
     * @param userId Identificador del usuario cuyos teléfonos se desean buscar.
     * @return Lista de teléfonos asociados al usuario especificado.
     */
    List<Phone> findByUserId(Long userId);

    /**
     * Guarda un objeto Phone en la base de datos.
     *
     * @param phone Objeto Phone a guardar.
     */
    void save(Phone phone);

    /**
     * Actualiza un objeto Phone existente en la base de datos.
     *
     * @param phone Objeto Phone a actualizar.
     */
    void update(Phone phone);

    /**
     * Elimina un objeto Phone existente en la base de datos.
     *
     * @param phone Objeto Phone a eliminar.
     */
    void delete(Phone phone);

    /**
     * Busca y devuelve un objeto Phone en base a su identificador único.
     *
     * @param id Identificador único del objeto Phone a buscar.
     * @return Objeto Phone con el identificador especificado.
     */
    Phone findById(Long id);
}

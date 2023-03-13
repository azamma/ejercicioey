/**
 * Data Transfer Object (DTO) que representa la información de un User.
 */

package com.ey.ejercicio.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter @Setter
public class UserDTO {

    /**
     * Nombre del usuario
     */
    @NotEmpty(message = "Falta el campo name") // Validación: El campo no debe estar vacío
    @NotNull(message = "El campo name no puede ser nulo") // Validación: El campo no puede ser nulo
    private String name;

    /**
     * Correo electrónico del usuario
     */
    @NotEmpty(message = "Falta el campo email") // Validación: El campo no debe estar vacío
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}", message = "El formato del correo electrónico es inválido") // Validación: El campo debe tener un formato de correo electrónico válido
    private String email;

    /**
     * Contraseña del usuario
     */
    @NotEmpty(message = "Falta el campo password") // Validación: El campo no debe estar vacío
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d{2}).{6,}$", message = "La contraseña debe contener al menos una mayúscula, una minúscula y dos números.") // Validación: La contraseña debe cumplir con ciertos requisitos de complejidad
    private String password;

    /**
     * Lista de teléfonos asociados al usuario
     */
    @NotEmpty(message = "No pueden faltar teléfonos") // Validación: La lista de teléfonos no puede estar vacía
    private List<PhoneDTO> phones;

}

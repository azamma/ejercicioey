package com.ey.ejercicio.dtos;

import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter @Setter
public class UserDTO {

    @NotEmpty(message = "Falta el campo name")
    @NotNull(message = "El campo name no puede ser nulo")
    private String name;

    @NotEmpty(message = "Falta el campo email")
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}", message = "El formato del correo electrónico es inválido")
    private String email;

    @NotEmpty(message = "Falta el campo password")
    private String password;

    @NotEmpty(message = "No pueden faltar teléfonos")
    private List<PhoneDTO> phones;

}

/**
 * DTO de respuesta para la entidad User
 */

package com.ey.ejercicio.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    /**
     * Identificador único del usuario
     */
    private Long id;

    /**
     * Fecha y hora de creación del usuario
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date created;

    /**
     * Fecha y hora de última modificación del usuario
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date modified;

    /**
     * Fecha y hora del último inicio de sesión del usuario
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date lastLogin;

    /**
     * Token JWT del usuario
     */
    private String token;

    /**
     * Indica si el usuario está activo o no
     */
    private Boolean isActive;
}

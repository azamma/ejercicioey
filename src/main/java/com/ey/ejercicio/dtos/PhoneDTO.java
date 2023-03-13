/**
 * Data Transfer Object (DTO) que representa la información de un teléfono.
 */

package com.ey.ejercicio.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PhoneDTO {

    /**
     * Número de teléfono.
     */
    private String number;

    /**
     * Código de ciudad del número de teléfono.
     */
    private String cityCode;

    /**
     * Código de país del número de teléfono.
     */
    private String countryCode;
}

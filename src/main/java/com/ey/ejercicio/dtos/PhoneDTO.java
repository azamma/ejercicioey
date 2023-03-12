package com.ey.ejercicio.dtos;

import lombok.Getter;
import lombok.Setter;


public class PhoneDTO {

    @Getter @Setter
    private String number;

    @Getter @Setter
    private String cityCode;

    @Getter @Setter
    private String countryCode;
}

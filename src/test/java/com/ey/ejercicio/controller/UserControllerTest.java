package com.ey.ejercicio.controller;

import com.ey.ejercicio.controllers.UserController;
import com.ey.ejercicio.dtos.PhoneDTO;
import com.ey.ejercicio.dtos.UserDTO;
import com.ey.ejercicio.dtos.UserResponseDTO;
import com.ey.ejercicio.entities.User;
import com.ey.ejercicio.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.PersistenceException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

    // Aquí creamos el mock del servicio que usará el controlador para crear usuarios.

    @MockBean
    private UserService userService;

// Inyectamos el MockMvc para poder hacer las pruebas HTTP.

    @Autowired
    private MockMvc mockMvc;

// Este es el método que testea la creación de un usuario.

    @Test
    public void createUser() throws Exception {
        // Primero, creamos los objetos necesarios para hacer la petición.

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setCountryCode("57");
        phoneDTO.setCityCode("1");
        phoneDTO.setNumber("1234567");

        UserDTO userDTO = new UserDTO();
        userDTO.setName("Jhon");
        userDTO.setEmail("Jhon@gmail.com");
        userDTO.setPassword("Password2023");
        userDTO.setPhones(Collections.singletonList(phoneDTO));

        User createdUser = new User();
        createdUser.mapToUser(userDTO);
        createdUser.setToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ3ZHdkd2QiLCJpYXQiOjE2Nzg3Mjc2NTEsInN1YiI6Ikpocm90ZG5EQGdtYWlsLmNvbSIsImlzcyI6IlphbW1hIiwiZXhwIjoxNjc4NzMzNjUxfQ.KlrD2tOvRiacUcG5qYZumurPEMjMHkwPioLNgHo7g7M");

        // Aquí hacemos el "mocking": le decimos qué debería devolver el UserService cuando le pedimos que guarde un usuario.

        Mockito.when(userService.saveUser(any(UserDTO.class))).thenReturn(createdUser);

        // Creamos una petición HTTP con la información del usuario que queremos crear.

        String requestJson = "{\"name\": \"Jhon\", \"email\": \"Jhon@gmail.com\", \"password\": \"Password2023\", \"phones\": [{\"number\": \"1234567\",\"cityCode\": \"1\",\"countryCode\": \"57\"}]}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        // Enviamos la petición y esperamos el resultado.

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // La parte final: verificamos que la respuesta sea la que esperamos.

        UserResponseDTO userResponseDTO = createdUser.mapToUserResponseDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(userResponseDTO);
        String responseJson = result.getResponse().getContentAsString();
        assertThat(responseJson).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void createUserWithExistingEmail() throws Exception {
        // Primero, creamos los objetos necesarios para hacer la petición.

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setCountryCode("57");
        phoneDTO.setCityCode("1");
        phoneDTO.setNumber("1234567");

        UserDTO userDTO = new UserDTO();
        userDTO.setName("Jhon");
        userDTO.setEmail("Jhon@gmail.com");
        userDTO.setPassword("Password2023");
        userDTO.setPhones(Collections.singletonList(phoneDTO));

        // Aquí hacemos el "mocking": le decimos qué debería devolver el UserService cuando le pedimos que guarde un usuario con un correo electrónico existente.

        Mockito.when(userService.saveUser(any(UserDTO.class))).thenThrow(new PersistenceException("El correo ya está registrado"));

        // Creamos una petición HTTP con la información del usuario que queremos crear.

        String requestJson = "{\"name\": \"Jhon\", \"email\": \"Jhon@gmail.com\", \"password\": \"Password2023\", \"phones\": [{\"number\": \"1234567\",\"cityCode\": \"1\",\"countryCode\": \"57\"}]}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        // Enviamos la petición y esperamos el resultado.

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String response = "{\"message\":\"Se produjo un error al crear el usuario -> "+ result.getResolvedException().getMessage() +"\"}";

        // La parte final: verificamos que la respuesta sea la que esperamos.

        int expectedStatusCode = HttpStatus.CONFLICT.value();
        String expectedErrorMessage = "{\"message\":\"Se produjo un error al crear el usuario -> El correo ya está registrado\"}";

        assertThat(result.getResponse().getStatus()).isEqualTo(expectedStatusCode);
        assertThat(response).isEqualTo(expectedErrorMessage);
    }

}
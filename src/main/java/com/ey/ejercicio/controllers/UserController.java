/**
 * Controlador REST para usuarios.
 */

package com.ey.ejercicio.controllers;

import com.ey.ejercicio.dtos.UserDTO;
import com.ey.ejercicio.dtos.UserResponseDTO;
import com.ey.ejercicio.entities.User;
import com.ey.ejercicio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Maneja la solicitud para crear un nuevo usuario.
     *
     * @param userDTO DTO que contiene los campos del request
     * @return ResponseEntity con un objeto UserResponseDTO que representa el usuario creado.
     */
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        User usuarioCreado = userService.saveUser(userDTO);
        return ResponseEntity.ok(usuarioCreado.mapToUserResponseDTO());
    }

    /**
     * Maneja la solicitud para obtener un usuario por su correo electrónico.
     *
     * @param email El correo electrónico del usuario que se desea buscar.
     * @return ResponseEntity con un objeto UserResponseDTO que representa el usuario encontrado, o notFound si no se encontró.
     */
    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.mapToUserResponseDTO());
    }
}

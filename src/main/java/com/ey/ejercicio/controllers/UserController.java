package com.ey.ejercicio.controllers;

import com.ey.ejercicio.controllers.exceptions.ErrorResponse;
import com.ey.ejercicio.dtos.UserDTO;
import com.ey.ejercicio.dtos.UserResponseDTO;
import com.ey.ejercicio.entities.User;
import com.ey.ejercicio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        User usuarioCreado = userService.saveUser(userDTO);
        return ResponseEntity.ok(usuarioCreado.mapToUserResponseDTO());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse("Hay uno o más campos con errores -> ".concat(errors.toString()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ErrorResponse> handlePersistenceExceptions(PersistenceException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Se produjo un error al crear el usuario -> ".concat(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Se produjo un error inesperado -> ".concat(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

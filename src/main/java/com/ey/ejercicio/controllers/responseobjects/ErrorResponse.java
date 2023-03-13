/**
Clase con el formato de mensaje de repuestas con errores.
 */

package com.ey.ejercicio.controllers.responseobjects;

public class ErrorResponse {
    /**
     * Mensaje que describe el error.
     */
    private String message;

    /**
     * Constructor que recibe el mensaje de error.
     *
     * @param message Mensaje de error a incluir en la respuesta.
     */
    public ErrorResponse(String message) {
        this.message = message;
    }

    /**
     * Devuelve el mensaje de error.
     *
     * @return El mensaje de error.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece el mensaje de error.
     *
     * @param message El mensaje de error a establecer.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

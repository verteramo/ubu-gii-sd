package sd.verteramo.exception;

import sd.verteramo.dto.ApiErrorDto;

/**
 * Excepción personalizada para manejar errores de la API de Flask.
 * Permite encapsular el detalle del error recibido en un DTO.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
public class ApiException extends RuntimeException {
    private final ApiErrorDto error;

    public ApiException(ApiErrorDto error) {
        super(error.getMessage());
        this.error = error;
    }

    public ApiErrorDto getError() {
        return error;
    }
}
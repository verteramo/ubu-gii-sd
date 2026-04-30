package sd.verteramo.exception;

import sd.verteramo.dto.ApiErrorDto;

/**
 * Excepción personalizada para manejar errores de la API de Flask.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
public class ApiException extends RuntimeException {

    /** El error de la API. */
    private final ApiErrorDto error;

    /**
     * Constructor de la excepción.
     * 
     * @param error El error de la API que causó la excepción.
     */
    public ApiException(ApiErrorDto error) {
        super(error.getMessage());
        this.error = error;
    }

    /**
     * Obtiene el error de la API asociado a esta excepción.
     * 
     * @return El error de la API.
     */
    public ApiErrorDto getError() {
        return error;
    }
}
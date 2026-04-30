package sd.verteramo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO para representar errores de la API de Flask.
 * Se utiliza para mapear respuestas de error como {"error": "Not Found"}.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDto {

    /** Tipo de error, por ejemplo "Not Found". */
    private String error;

    /** Mensaje adicional, si la API lo proporciona. */
    private String message;
}

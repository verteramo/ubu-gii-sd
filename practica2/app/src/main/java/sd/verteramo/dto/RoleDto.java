package sd.verteramo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto de transferencia de datos utilizado para desacoplar la capa de
 * persistencia de la entidad 'role' de la vista.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    /** ID del rol. */
    private Integer id;

    /** Nombre del rol. */
    private String name;

    /** Campo showOnCreate. */
    private Integer showOnCreate;

}

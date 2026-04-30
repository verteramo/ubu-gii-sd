package sd.verteramo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para representar un rol en la aplicación.
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

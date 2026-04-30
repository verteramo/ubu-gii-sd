package sd.verteramo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para representar un usuario en la aplicación.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    /** Nombre de usuario. */
    private String username;

    /** Correo electrónico. */
    private String email;

    /** Nombre público. */
    private String name;

    /** Rol del usuario. */
    private RoleDto role;

}

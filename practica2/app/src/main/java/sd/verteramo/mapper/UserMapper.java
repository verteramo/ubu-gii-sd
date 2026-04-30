package sd.verteramo.mapper;

import org.springframework.stereotype.Component;

import sd.verteramo.dto.RoleDto;
import sd.verteramo.dto.UserDto;
import sd.verteramo.model.User;

/**
 * Mapeador para convertir entre el modelo de usuario y su DTO correspondiente.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Component
public class UserMapper {

    /**
     * Conversión de modelo a DTO.
     * 
     * @param user Usuario.
     * @return DTO de usuario.
     */
    public UserDto toDto(User user) {
        RoleDto roleDto = new RoleDto(
                user.getUserRole().getId(),
                user.getUserRole().getRoleName(),
                user.getUserRole().getShowOnCreate());

        UserDto userDto = new UserDto(
                user.getUsername(),
                user.getEmail(),
                user.getNombreUsuario(),
                roleDto);

        return userDto;
    }

}

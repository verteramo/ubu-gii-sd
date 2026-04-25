package sd.verteramo.service;

import lombok.RequiredArgsConstructor;
import sd.verteramo.dto.UserDto;
import sd.verteramo.mapper.UserMapper;
import sd.verteramo.model.User;
import sd.verteramo.repository.UserRepository;

import org.springframework.stereotype.Service;

/**
 * Capa de servicio encargada de gestionar la lógica de negocio y coordinar el
 * acceso a datos para la entidad 'user'.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Service
@RequiredArgsConstructor
public class UserService {

    /** Inyección del repositorio de usuarios. */
    private final UserRepository userRepository;

    /** Inyección del mapper de usuarios. */
    private final UserMapper userMapper;

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return DTO de usuario o null en caso contrario.
     */
    public UserDto getUser(String username) {
        User user = userRepository.findUserByUsername(username);
        return (user != null) ? userMapper.toDto(user) : null;
    }

    /**
     * Verifica la autenticación de un usuario.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @return Verdadero si la autenticación es exitosa, falso en caso contrario.
     */
    public boolean isAuthentic(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

}

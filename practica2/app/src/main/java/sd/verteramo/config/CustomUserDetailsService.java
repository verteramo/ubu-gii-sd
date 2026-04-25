package sd.verteramo.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import sd.verteramo.model.User;
import sd.verteramo.repository.UserRepository;

import java.util.List;

/**
 * Servicio por defecto para la gestión de usuarios.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /** Repositorio de usuarios. */
    private final UserRepository userRepository;

    /**
     * Constructor.
     * 
     * @param userRepository Repositorio de usuarios.
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Carga un usuario por su nombre de usuario.
     * 
     * @param username Nombre de usuario.
     * @return Usuario.
     * @throws UsernameNotFoundException Si no se encuentra el usuario.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Obtención del usuario desde el repositorio
        User user = userRepository.findUserByUsername(username);

        // Si no se encuentra el usuario, se lanza una excepción
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User not found: %s.", username));
        }

        // Retorno del usuario con su rol
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getUserRole().getRoleName())));
    }

}

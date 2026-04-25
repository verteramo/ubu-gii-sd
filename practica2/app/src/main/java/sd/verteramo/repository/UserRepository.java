package sd.verteramo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sd.verteramo.model.User;

/**
 * Interfaz de acceso a datos para realizar operaciones de persistencia sobre la
 * entidad 'user'.
 *
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Usuario encontrado o null en caso contrario.
     */
    User findUserByUsername(String username);

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email Correo electrónico.
     * @return Usuario encontrado o null en caso contrario.
     */
    User findUserByEmail(String email);

}

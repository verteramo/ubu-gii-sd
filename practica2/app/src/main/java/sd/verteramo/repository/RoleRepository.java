package sd.verteramo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sd.verteramo.model.Role;

/**
 * Interfaz de acceso a datos para la entidad rol.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

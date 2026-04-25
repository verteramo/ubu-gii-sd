package sd.verteramo.service;

import lombok.RequiredArgsConstructor;
import sd.verteramo.model.Role;
import sd.verteramo.repository.RoleRepository;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Capa de servicio encargada de gestionar la lógica de negocio y coordinar el
 * acceso a datos para la entidad 'role'.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Service
@RequiredArgsConstructor
public class RoleService {

    /** Inyección del repositorio de roles. */
    private final RoleRepository roleRepository;

    /**
     * Obtiene una lista de todos los roles.
     *
     * @return Lista de roles.
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Obtiene un rol por su ID.
     *
     * @param id ID del rol.
     * @return Rol encontrado o null en caso contrario.
     */
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

}

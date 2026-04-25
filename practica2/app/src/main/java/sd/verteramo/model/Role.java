package sd.verteramo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * Entidad de persistencia que representa la estructura de la tabla 'role'
 * en la base de datos.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role implements Serializable {

    /** ID del rol. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Nombre del rol. */
    @Column(nullable = false)
    private String roleName;

    /** Campo showOnCreate. */
    @Column(nullable = false)
    private Integer showOnCreate;

}

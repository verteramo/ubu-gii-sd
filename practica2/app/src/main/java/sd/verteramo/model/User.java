package sd.verteramo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entidad de persistencia que representa la estructura de la tabla 'user'
 * en la base de datos.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements Serializable {

    /** ID de usuario. */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** Nombre de usuario. */
    @Column(name = "username", length = 50)
    private String username;

    /** Correo electrónico. */
    @Column(name = "email", length = 50)
    private String email;

    /** Nombre público */
    @Column(name = "nombre_usuario", length = 30)
    private String nombreUsuario;

    /** Contraseña. */
    @Column(name = "password", length = 250)
    private String password;

    /** Clave pública. */
    @Lob
    private byte[] publickey;

    /** Fecha del último acceso. */
    @Column(name = "fechaUltimoAcceso")
    private LocalDateTime fechaUltimoAcceso;

    /** Rol del usuario. */
    @ManyToOne(fetch = FetchType.EAGER)
    private Role userRole;

}

package sd.verteramo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Configuration
public class SecurityConfig {

    /** Servicio por defecto para la gestión de usuarios. */
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Constructor.
     * 
     * @param customUserDetailsService Servicio por defecto para la gestión de
     *                                 usuarios.
     */
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * Codificador de contraseñas.
     * 
     * @return Codificador de contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Administrador de autenticación.
     * 
     * @param config Configuración de autenticación.
     * @return Administrador de autenticación.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }

    /**
     * Configuración de seguridad.
     * 
     * @param http Configuración de seguridad.
     * @return Configuración de seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Se deshabilita CSRF por simplicidad
                .csrf(AbstractHttpConfigurer::disable)
                // Configuración de la autenticación
                .userDetailsService(customUserDetailsService)
                // Configuración de autorización
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas permitidas
                        .requestMatchers("/", "/css/**", "/js/**").permitAll()
                        // Todas las demás rutas requieren autenticación
                        .anyRequest().authenticated())
                // Configuración del login
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll())
                // Configuración del logout
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

}

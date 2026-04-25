package sd.verteramo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador de la página de inicio de sesión.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * Muestra la página de inicio de sesión.
     * Spring Security redirige aquí con ?error=true si falla la autenticación.
     *
     * @return Vista de inicio de sesión.
     */
    @GetMapping
    public String showLogin() {
        return "login";
    }

}

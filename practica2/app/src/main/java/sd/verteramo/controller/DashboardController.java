package sd.verteramo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import sd.verteramo.dto.UserDto;
import sd.verteramo.service.UserService;

/**
 * Controlador del panel de control donde pueden acceder usuarios autenticados.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    /** Inyección del servicio de usuario. */
    private final UserService userService;

    /**
     * Control de solicitudes GET.
     * Muestra el panel de control.
     * 
     * @param principal Usuario autenticado.
     * @param model     Modelo de la vista.
     * @return Vista del panel de control.
     */
    @GetMapping
    public String showDashboard(java.security.Principal principal, Model model) {
        UserDto userDto = userService.getUser(principal.getName());

        model.addAttribute("user", userDto);
        return "dashboard";
    }

}

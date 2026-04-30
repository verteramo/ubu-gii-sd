package sd.verteramo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador de la página principal.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Controller
@RequestMapping("/")
public class MainController {

    /**
     * Control de solicitudes GET.
     * En esta aplicación no hay, de momento, una página principal; por lo que las
     * solicitudes son redirigidas al panel de control.
     *
     * @param model Modelo de la vista.
     * @return Redirección al panel de control.
     */
    @GetMapping
    public String vistaHome(ModelMap model) {
        return "redirect:/dashboard";
    }

}

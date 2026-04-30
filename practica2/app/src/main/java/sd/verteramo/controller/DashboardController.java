package sd.verteramo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import sd.verteramo.dto.PokemonDto;
import sd.verteramo.exception.ApiException;
import sd.verteramo.service.ApiService;
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

    /** Inyección del servicio de la API externa. */
    private final ApiService apiService;

    /**
     * Inyección del usuario autenticado en el modelo para todas las vistas de este
     * controlador.
     * 
     * @param model     Modelo de la vista.
     * @param principal Usuario autenticado.
     */
    @ModelAttribute
    public void addUser(Model model, java.security.Principal principal) {
        if (principal != null) {
            model.addAttribute("user", userService.getUser(principal.getName()));
        }
    }

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
        return "dashboard";
    }

    /**
     * Procesa la búsqueda de un Pokémon desde el formulario.
     * 
     * @param name  Nombre capturado por el input 'name="name"' del HTML.
     * @param model Modelo para pasar datos a la vista Thymeleaf.
     * @return La vista dashboard con los datos del Pokémon o mensaje de error.
     */
    @PostMapping("/pokemon")
    public String getPokemon(@RequestParam String name, Model model) {
        try {
            PokemonDto pokemon = apiService.getPokemon(name);

            if (pokemon != null) {
                // El pokémon existe
                model.addAttribute("pokemon", pokemon);
            } else {
                // El pokémon no existe
                model.addAttribute("notfound", true);
            }
        } catch (ApiException e) {
            // Error al comunicarse con la API externa
            model.addAttribute("error", e.getError());
        }

        return "dashboard";
    }

    /**
     * Método para probar el manejo de errores al leer un archivo.
     * 
     * @param model Modelo para pasar datos a la vista Thymeleaf.
     * @return La vista dashboard con el mensaje de error si ocurre una excepción.
     */
    @PostMapping("/test-file")
    public String testFile(Model model) {
        try {
            apiService.testFileError();
        } catch (ApiException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "dashboard";
    }

    /**
     * Método para probar el manejo de errores al leer una base de datos.
     * 
     * @param model Modelo para pasar datos a la vista Thymeleaf.
     * @return La vista dashboard con el mensaje de error si ocurre una excepción.
     */
    @PostMapping("/test-db")
    public String testDb(Model model) {
        try {
            apiService.testDbError();
        } catch (ApiException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "dashboard";
    }

}

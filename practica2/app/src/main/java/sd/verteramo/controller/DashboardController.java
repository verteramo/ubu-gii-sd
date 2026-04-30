package sd.verteramo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import sd.verteramo.dto.PokemonDto;
import sd.verteramo.dto.UserDto;
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

    /** Inyección del servicio de mensajes. */
    private final MessageSource messageSource;

    /** Inyección del servicio de usuario. */
    private final UserService userService;

    /** Inyección del servicio de Pokémon. */
    private final ApiService apiService;

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
        UserDto user = userService.getUser(principal.getName());

        model.addAttribute("user", user);
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
    public String getPokemon(@RequestParam String name, java.security.Principal principal, Model model) {
        model.addAttribute("user", userService.getUser(principal.getName()));

        try {
            PokemonDto pokemon = apiService.getPokemon(name);

            if (pokemon == null) {
                // El pokémon no existe
                String message = messageSource.getMessage("pokemon.not.found", null, null);
                model.addAttribute("warning", message);
            } else {
                // El pokémon existe
                model.addAttribute("pokemon", pokemon);
            }
        } catch (ApiException e) {
            // Error al comunicarse con la API externa
            model.addAttribute("error", e.getError());
        }

        return "dashboard";
    }

}

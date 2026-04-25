package sd.verteramo.service;

// import com.sistemasdistr.basico.client.PokemonClient;
// import com.sistemasdistr.basico.dto.PokemonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Capa de servicio encargada de gestionar la lógica de negocio y coordinar el acceso a datos.
 */
@Service
@RequiredArgsConstructor
public class PokemonService {

    // private final PokemonClient pokemonClient;

    /**
     * Obtiene la información de un Pokémon.
     * @param nombre Nombre del pokemon a buscar.
     * @return El DTO con los datos o null si no se encuentra.
     */
    // public PokemonDto buscarPokemon(String nombre) {
    //     if (nombre == null || nombre.isBlank()) {
    //         return null;
    //     }
    //     // Normalizamos el nombre antes de pasarlo al cliente técnico
    //     return pokemonClient.fetchPokemon(nombre.toLowerCase().trim());
    // }

}

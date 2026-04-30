package sd.verteramo.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sd.verteramo.client.ApiClient;
import sd.verteramo.dto.PokemonDto;

/**
 * Capa de servicio encargada de gestionar la lógica de negocio para las
 * operaciones relacionadas con la API externa.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Service
@RequiredArgsConstructor
public class ApiService {

    /** Inyección del cliente de la API externa. */
    private final ApiClient client;

    /**
     * Obtiene un Pokémon por su nombre utilizando la API externa.
     * 
     * @param nombre Nombre del Pokémon a buscar.
     * @return DTO del Pokémon encontrado o null si no se encuentra o el nombre es
     *         inválido.
     */
    public PokemonDto getPokemon(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return null;
        }

        return client.fetchPokemon(nombre.toLowerCase().trim()).orElse(null);
    }

    /**
     * Método de prueba para simular un error de archivo en la API externa.
     */
    public void testFileError() {
        client.testFileError();
    }

    /**
     * Método de prueba para simular un error de base de datos en la API externa.
     */
    public void testDbError() {
        client.testDbError();
    }

}

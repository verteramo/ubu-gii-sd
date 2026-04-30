package sd.verteramo.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sd.verteramo.client.ApiClient;
import sd.verteramo.dto.PokemonDto;

/**
 * Servicio que actúa como intermediario entre el controlador y el cliente
 * técnico.
 * Encapsula la lógica de negocio relacionada con la obtención de datos de
 * Pokémon.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Service
@RequiredArgsConstructor
public class ApiService {
    private final ApiClient client;

    public PokemonDto getPokemon(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return null;
        }

        return client.fetchPokemon(nombre.toLowerCase().trim()).orElse(null);
    }

}

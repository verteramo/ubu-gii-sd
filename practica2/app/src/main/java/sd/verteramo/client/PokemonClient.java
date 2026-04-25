package sd.verteramo.client;

import sd.verteramo.config.ApiConfig;
import sd.verteramo.dto.PokemonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientResponseException;

/**
 * Cliente técnico encargado de la comunicación por red y la ejecución de
 * peticiones HTTP hacia el servicio "externo".
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Component
@RequiredArgsConstructor
public class PokemonClient {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate = new RestTemplate();

    public PokemonDto fetchPokemon(String name) {
        try {
            String url = apiConfig.getUrl("/pokemon/%s", name);
            return restTemplate.getForObject(url, PokemonDto.class);
        } catch (RestClientResponseException e) {
            // Si Flask devuelve 404 o 500, devolvemos null o podrías lanzar una excepción
            // personalizada
            return null;
        }
    }

}

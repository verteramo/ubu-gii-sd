package sd.verteramo.client;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import sd.verteramo.config.ApiConfig;
import sd.verteramo.dto.ApiErrorDto;
import sd.verteramo.dto.PokemonDto;
import sd.verteramo.exception.ApiException;

/**
 * Cliente para consumir la API de Flask.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Component
@RequiredArgsConstructor
public class ApiClient {

    /** Inyección de la configuración de la API. */
    private final ApiConfig config;

    /** Cliente HTTP para realizar solicitudes a la API. */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Método para obtener datos de un Pokémon por su nombre.
     * 
     * @param name Nombre del Pokémon a buscar.
     * @return Optional con el DTO del Pokémon si se encuentra, o vacío si no
     *         existe.
     * @throws ApiException Si ocurre un error en la comunicación con la API o si la
     *                      API responde con un error de servidor.
     */
    public Optional<PokemonDto> fetchPokemon(String name) {
        try {
            // Construcción de la URL de la solcitud
            String url = config.getUrl("/pokemon/%s", name);

            // Solicitud GET a la API de Flask
            PokemonDto response = restTemplate.getForObject(url, PokemonDto.class);

            // Si la respuesta es exitosa, se devuelve el DTO envuelto en Optional
            return Optional.ofNullable(response);

        } catch (HttpClientErrorException.NotFound e) {
            // Si la API responde con un 404, significa que el Pokémon no existe
            return Optional.empty();

        } catch (RestClientResponseException e) {
            // Captura de errores de servidor: 500, 503, etc.
            ApiErrorDto error = e.getResponseBodyAs(ApiErrorDto.class);
            throw new ApiException(error);
        }
    }

    /**
     * Método para probar la gestión de errores de tipo archivo.
     */
    public void testFileError() {
        try {
            String url = config.getUrl("/test/file");
            restTemplate.getForObject(url, Void.class);
        } catch (RestClientResponseException e) {
            ApiErrorDto error = e.getResponseBodyAs(ApiErrorDto.class);
            throw new ApiException(error);
        }
    }

    /**
     * Método para probar la gestión de errores de tipo base de datos.
     */
    public void testDbError() {
        try {
            String url = config.getUrl("/test/db");
            restTemplate.getForObject(url, Void.class);
        } catch (RestClientResponseException e) {
            ApiErrorDto error = e.getResponseBodyAs(ApiErrorDto.class);
            throw new ApiException(error);
        }
    }

}

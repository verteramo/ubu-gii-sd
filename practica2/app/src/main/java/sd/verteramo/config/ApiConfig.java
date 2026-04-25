package sd.verteramo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuración de la API externa.
 * Obtiene la propiedades de app.api.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@ConfigurationProperties(prefix = "app.api")
public class ApiConfig {

    /** URL de la API externa. */
    private final String url;

    /**
     * Constructor.
     * 
     * @param url URL de la API externa.
     */
    public ApiConfig(String url) {
        this.url = url;
    }

    /**
     * Obtiene la URL de la API externa.
     * 
     * @return URL de la API externa.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sobrecarga que obtiene un endpoint a partir de la URL de la API externa.
     * 
     * @param endpoint Endpoint a obtener.
     * @param args     Argumentos del endpoint.
     * @return URL del endpoint.
     */
    public String getUrl(String endpoint, Object... args) {
        return this.url + String.format(endpoint, args);
    }

}

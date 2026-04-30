package sd.verteramo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para representar un pokémon obtenido de la API de Flask.
 * 
 * @author Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDto {

    /** Identificador del pokémon. */
    private Integer id;

    /** Nombre del pokémon. */
    private String name;

    /** Altura del pokémon. */
    private Integer height;

    /** Peso del pokémon. */
    private Integer weight;

    /** Experiencia del pokémon. */
    private Integer experience;

    /** URL de la imagen del pokémon. */
    private String picture;

}

############################################
# DTOs
# Contratos que deben cumplir los objetos
# que entrega esta API.
#
# Alumno: Marcelo Verteramo Pérsico (mvp1011@qalu.ubu.es)
############################################

from dataclasses import dataclass


@dataclass
class PokemonDto:
    """
    Objeto de transferencia de datos de Pokémon.

    Attributes:
    - id: Identificador del pokémon.
    - name: Nombre del pokémon.
    - height: Altura del pokémon.
    - weight: Peso del pokémon.
    - experience: Experiencia del pokémon.
    - picture: URL de la imagen del pokémon.
    """
    id: int
    name: str
    height: int
    weight: int
    experience: int
    picture: str

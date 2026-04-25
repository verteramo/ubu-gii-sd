############################################
# Servicios
# Funciones que conocen el servicio externo
# y el formato de los datos de origen.
#
# Alumno: Marcelo Verteramo Pérsico (mvp1011@qalu.ubu.es)
############################################

import requests
from config import Config
from dto import PokemonDto


def fetch_pokemon(name: str) -> tuple[PokemonDto | None, int]:
    """
    Realiza una solicitud por nombre al servicio de obtención de pokémons.

    Args:
    - name: Nombre del pokémon.

    Returns:
    - Datos del pokémon encapsulados en el DTO o `None` si no existe.
    """

    # Solicitud al servicio externo
    response = requests.get(f"{Config.URL}/pokemon/{name}", timeout=5)

    # En caso de no encontrar el pokémon, se propaga el resultado 404
    if response.status_code == 404:
        return None, 404

    # Se fuerzan los eventuales errores que hayan ocurrido
    response.raise_for_status()

    # Obtención del objeto recibido en un diccionario
    data = response.json()

    # Transformación del objeto mapeado al DTO
    pokemon = PokemonDto(
        id=data["id"],
        name=data["name"],
        height=data["height"],
        weight=data["weight"],
        experience=data["base_experience"],
        picture=data["sprites"]["front_default"],
    )

    return pokemon, 200

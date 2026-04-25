############################################
# Configuración
#
# Alumno: Marcelo Verteramo Pérsico (mvp1011@qalu.ubu.es)
############################################

import os


class Config:
    """
    Variables de entorno, si no existieran se termina el programa.
    """

    try:
        URL = os.environ["API_POKEAPI_URL"].rstrip("/")
        PORT = int(os.environ["API_DOCKER_PORT"])
    except KeyError as e:
        print(f"Error: Variable de entorno no definida: {e}.")
        exit(1)
    except ValueError:
        print("Error: API_DOCKER_PORT debe ser un entero.")
        exit(1)

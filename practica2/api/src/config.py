############################################
# Configuración
# Variables de entorno necesarias para el funcionamiento de la API.
#
# Alumno: Marcelo Verteramo Pérsico (mvp1011@qalu.ubu.es)
############################################

import os


class Config:
    """
    Variables de entorno, si no existieran se termina el programa.
    """

    try:
        POKEAPI_URL = os.environ["POKEAPI_URL"].rstrip("/")
        PORT = int(os.environ["API_PORT"])
    except Exception as e:
        print(f"Error: Fallo en la configuración: {e}.")
        exit(1)
    else:
        DEBUG = os.environ.get("API_DEBUG", "false").lower() in ("true", "1")

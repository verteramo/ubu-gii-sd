############################################
# Fichero de rutas
# Definición de los endpoints de la API y manejo de errores
#
# Alumno: Marcelo Verteramo Pérsico (mvp1011@qalu.ubu.es)
############################################


from dataclasses import asdict
from werkzeug.exceptions import HTTPException

from flask import Blueprint, abort, jsonify
from services import fetch_pokemon

routes = Blueprint("api", __name__)


@routes.app_errorhandler(Exception)
def handle_exception(e: Exception):
    if isinstance(e, HTTPException):
        code = e.code
        error = {"error": e.name, "message": e.description}
    else:
        code = 500
        error = {"error": "Internal Server Error", "message": str(e)}

    return jsonify(error), code


@routes.route("/", methods=["GET"])
def index():
    return jsonify({"status": "Ok", "version": "1.0"}), 200


@routes.route("/pokemon/<name>", methods=["GET"])
def get_pokemon(name):
    """
    Endpoint para obtener los datos de un pokémon por su nombre.

    Args:
     - name: Nombre del pokémon a buscar.

    Returns:
     - JSON con los datos del pokémon o mensaje de error si no se encuentra.
    """

    # Solicitud del pokémon
    pokemon, status = fetch_pokemon(name)

    # Aborto si no se encuentra el pokémon
    if status == 404:
        abort(404, description=f"Pokémon '{name}' no encontrado")

    # Retorno del objeto
    return jsonify(asdict(pokemon)), 200


@routes.route("/test/file", methods=["GET"])
def test_file_error():
    with open("", "r") as f:
        return f.read()


@routes.route("/test/db", methods=["GET"])
def test_db_error():
    raise ConnectionError("No se pudo conectar con la base de datos de Flask")

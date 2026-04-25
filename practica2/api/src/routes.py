############################################
# Fichero de rutas
#
# Alumno: Marcelo Verteramo Pérsico (mvp1011@qalu.ubu.es)
############################################


from dataclasses import asdict

from flask import Blueprint, jsonify
import requests
from services import fetch_pokemon

api_routes = Blueprint("api", __name__)


@api_routes.route("/pokemon/<name>", methods=["GET"])
def get_pokemon(name):
    try:
        # Solicitud del pokémon
        pokemon, status = fetch_pokemon(name)

        # Retorno de error no encontrado
        if status == 404:
            return jsonify({"error": "Not Found"}), 404

        # Retorno del objeto
        return jsonify(asdict(pokemon)), 200

    except requests.exceptions.RequestException as e:

        # Retorno de error de conexión
        return jsonify({"error": e}), 503


@api_routes.route("/test/file", methods=["GET"])
def test_file_error(path: str):
    with open(path, "r") as f:
        return f.read()


@api_routes.route("/test/db", methods=["GET"])
def test_db_error():
    raise ConnectionError("No se pudo conectar con la base de datos de Flask")

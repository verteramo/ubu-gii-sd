############################################
# Sistemas distribuidos
# API en Flask que consume, a su vez, la API pokeapi.co
# Alumno: Marcelo Verteramo Pérsico (mvp1011@qalu.ubu.es)
############################################

from flask import Flask, jsonify
from config import Config
from routes import api_routes


api = Flask(__name__)
api.register_blueprint(api_routes)


@api.errorhandler(Exception)
def handle_exception(e: Exception):
    return jsonify({"error": e}), 500


if __name__ == "__main__":
    api.run(host="0.0.0.0", port=Config.PORT)

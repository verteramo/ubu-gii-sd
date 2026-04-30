############################################
# Sistemas distribuidos
# API en Flask que consume, a su vez, la API pokeapi.co
# Alumno: Marcelo Verteramo Pérsico (mvp1011@qalu.ubu.es)
############################################

from flask import Flask
from config import Config
from routes import routes

api = Flask(__name__)
api.register_blueprint(routes)

if __name__ == "__main__":
    api.run(host="0.0.0.0", port=Config.PORT, debug=Config.DEBUG)

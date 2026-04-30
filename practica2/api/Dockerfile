############################################
# Sistemas distribuidos
# Dockerfile de la imagen de la API.
#
# Alumno: Marcelo Verteramo Pérsico (mvp1011@qalu.ubu.es)
############################################

# Imagen base: https://hub.docker.com/layers/library/python/3.11-slim/
FROM python:3.11-slim

# Directorio de trabajo
WORKDIR /api

# Instalación de dependencias
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copia del código fuente
COPY src ./src

# Exposición del puerto
ARG API_PORT
EXPOSE $API_PORT

# Ejecución de la aplicación
CMD ["python", "src/main.py"]

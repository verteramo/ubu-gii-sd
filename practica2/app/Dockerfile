############################################
# Sistemas distribuidos
# Dockerfile de la imagen de la aplicación web
# Alumno: Marcelo Verteramo Pérsico (mvp1011@qalu.ubu.es)
############################################

# Imagen base: https://hub.docker.com/layers/library/maven/3.9-amazoncorretto/
FROM maven:3.9-amazoncorretto

# Directorio de trabajo
WORKDIR /app

# Instalación de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia, limpieza, compilación y empaquetado del código fuente
COPY src ./src
RUN mvn clean package

# Creación de usuario sin privilegios absolutos
RUN groupadd -r nonroot && \
    useradd -r -g nonroot -m nonroot && \
    mkdir -p /app && \
    chown -R nonroot:nonroot /app

# Cambio de usuario
USER nonroot

# Ejecución de la aplicación
CMD mvn spring-boot:run

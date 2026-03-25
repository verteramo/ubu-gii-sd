# Práctica 01 - Sistemas Distribuidos - GII - UBU
- Alumno: Marcelo Verteramo Pérsico (mvp1011@alu.ubu.es)
- Profesor: José Manuel Aroca Fernández
- Repositorio: [https://github.com/verteramo/ubu-gii-sd/](https://github.com/verteramo/ubu-gii-sd/)

## Compilación y ejecución
### Manual
```sh
mvn exec:java -Dexec.mainClass="es.ubu.lsi.server.ChatServerImpl"  -Dexec.args="2000"
mvn exec:java -Dexec.mainClass="es.ubu.lsi.client.ChatClientImpl"  -Dexec.args="Pio 2000 localhost"
mvn exec:java -Dexec.mainClass="es.ubu.lsi.client.ChatClientImpl"  -Dexec.args="Blas 2000 localhost"
mvn exec:java -Dexec.mainClass="es.ubu.lsi.client.ChatClientImpl"  -Dexec.args="Donatello 2000 localhost"
```

### Con `exec-maven-plugin`
```sh
mvn exec:java@server
mvn exec:java@cliente-pio
mvn exec:java@cliente-blas
mvn exec:java@cliente-donatello
```

## Particularidades del diseño
Enunciado:
> Por otro lado, un usuario (cliente) tendrá la posibilidad de bloquear los mensajes de otro usuario incluso si el usuario a bloquear no está todavía conectado. Para realizar este bloqueo se implementará el comando “ban”, pudiendo volver a aceptar sus mensajes con el comando “unban”. 
Cuando un usuario haya sido bloqueado, simplemente no se mostrarán sus mensajes.
>
> Ejemplo:
> - `ban usuariobaneado`
> - `unban usuariounbaneado`
>
> Cuando un usuario bloquea a otro, se deberá comunicar este hecho al servidor a través de un 
mensaje del tipo “usuarioactual ha baneado a usuariobaneado”.

Para tener "memoria" y banear a un usuario, aún si este no estuviera conectado, puesto que [`ServerSocket.accept`](https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html#accept--) no garantiza el mismo número de puerto en cada conexión, se implementa por username.
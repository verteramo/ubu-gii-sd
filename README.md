El enunciado solicita:

> Por otro lado, un usuario (cliente) tendrá la posibilidad de bloquear los mensajes de otro usuario incluso si el usuario a bloquear no está todavía conectado. Para realizar este bloqueo se implementará el comando “ban”, pudiendo volver a aceptar sus mensajes con el comando “unban”. 
Cuando un usuario haya sido bloqueado, simplemente no se mostrarán sus mensajes.

> Ejemplo:
> - ban usuariobaneado
> - unban usuariounbaneado 

> Cuando un usuario bloquea a otro, se deberá comunicar este hecho al servidor a través de un 
mensaje del tipo “usuarioactual ha baneado a usuariobaneado”.

Para tener "memoria" y banear a un usuario, aún si este no estuviera conectado, se implementa por username, puesto que [ServerSocket.accept](https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html#accept--) no garantiza el mismo número de puerto en cada conexión.
# candidate_management
Servicio que gestiona información de candidatos que postulan a proceso de selección y contratación.

# Tecnologia y Herramientas

* Ide Intellij
* Spring Boot para creacion y configuracion de proyecto
* Estructura de Proyecto con modelo Hexagonal
* Flyway para gestion de data de prueba en el archivo afterMigrate
* Jpa para manejo de entidades y relacion con la DB.
* Spring Security para manejo de Authenticacion y Authorizacion
* JWT para manejo de token en validacion de metodos y creacion de usuarios.
* OpenAPi o SWagger para documentacion y contratos de la api.
* JUnit para test de las clases utilizadas.
* Postman para hacer pruebas de la api. /resource/postman/Candidate.postman_collection.json

# Entregas del Proyecto

* Repositorio de GitHub con el código fuente
* Servicio desplegado en https://candidatemanagement-production.up.railway.app
* Documentación de los endpoints en Swagger y openapi ubicado en  /openapi/api-doc.yaml
* Una colección en Postman en Local Y Remoto:
* * Para las pruebas en Postman ejecutar en el order numerico:
* * 1. Registrar Usuario
* * 2. Obtener Token Usuario
* * 3. Obtener todos los candidatos
* * 4. Obtener Candidatos por Id
* * 5. Registrar un Candidato
* * 6. Eliminar un Candidate

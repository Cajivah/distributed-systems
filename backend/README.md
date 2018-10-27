# CINEMA BACKEND

## Swagger
http://localhost:8080/swagger-ui.html

## Login
POST localhost:8080/login
x-www-form-urlencoded {username, password}

## Frontend
- reloaded every time you run `./gradlew bootrun`
- located in `main/resources/static`
- then available at `localhost:8080/app`

## Environment
### Docker
In order to set up dependencies (redis, postgre), run `./gradlew composeup`. It will set up not only depencies, but main application as well. If you are working on it and want to start it from IDE/console, just shut it down with docker cli. Ports of redis and postgre are exposed and should be available from local network.
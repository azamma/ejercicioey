# API Ejercicio EY

API RESTful para crear usuarios utilizando Java 8+, Spring Framework, Hibernate y una base de datos en memoria como HSQLDB o H2

## Tecnologías utilizadas

- Spring Boot
- Hibernate
- HSQLDB

## Requisitos previos

- Java 8
- Gradle

## Configuración

1. Clonar el repositorio: `git clone https://github.com/azamma/ejercicioey`
2. Configurar las credenciales de la base de datos en el archivo `application.properties`.
3. Compilar utilizando Gradle.


## Request de ejemplo

Los campos no pueden estar vacíos y además:

1. El campo "email" debe ser un correo electrónico válido.
2. El campo "password" debe contener una Mayuscula, letras minúsculas, y dos números.

```
POST /api/users
Content-Type: application/json
```

```json
{
    "name": "Jhon",
    "email": "Jhon@gmail.com",
    "password": "Password2023",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        }
    ]
}


```

# Response de ejemplo esperado 

```json
{
  "id": 20,
  "created": "2023-03-13T06:04:51",
  "modified": "2023-03-13T06:04:51",
  "lastLogin": "2023-03-13T06:04:51",
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJKaG9uIiwiaWF0IjoxNjc4Njg3NDkxLCJzdWIiOiJKaG9uQGdtYWlsLmNvbSIsImlzcyI6IlphbW1hIiwiZXhwIjoxNjc4NjkzNDkxfQ.NyZ6J1OaOKqaaQQZJRBMYaZ0qGWpU9Qp6TD8LUkR_bU",
  "isActive": true
}

```
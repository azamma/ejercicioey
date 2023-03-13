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
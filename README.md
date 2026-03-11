# Inventario Backend
API REST para gestión de inventario desarrollada con Java, Spring Boot y PostgreSQL. Incluye autenticación con JWT, validaciones y paginación.

---

## Tecnologías utilizadas

| Capa | Tecnología |
|------|-----------|
| Lenguaje | Java 21 |
| Framework | Spring Boot 3.4.3 |
| Seguridad | Spring Security + JWT |
| Persistencia | Spring Data JPA / Hibernate |
| Base de datos | PostgreSQL |
| Build | Maven |

---

## Funcionalidades

- CRUD completo de productos
- Autenticación con JWT (registro e inicio de sesión)
- Endpoints protegidos por token
- Validaciones con mensajes descriptivos
- Paginación de resultados

---

## Estructura del proyecto
```
bl.inventarios
├── controlador      # Controllers REST
├── modelo           # Entidades JPA
├── Repositorio      # Interfaces Spring Data JPA
├── servicio         # Lógica de negocio
└── seguridad        # JWT: JwtUtil, JwtFilter, SecurityConfig
```

---

## Configuración

### Requisitos previos

- Java 21
- PostgreSQL 15+
- Maven

### application.properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/inventarios
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
server.port=8082
```

### Ejecutar el proyecto
```bash
mvn spring-boot:run
```

---

## Endpoints

### Autenticación (no requieren token)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/auth/register` | Registrar usuario |
| POST | `/auth/login` | Iniciar sesión, devuelve token JWT |

### Productos (requieren `Authorization: Bearer <token>`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/inventario-app/productos` | Listar todos |
| GET | `/inventario-app/productos/paginado?page=0&size=10` | Listar paginado |
| POST | `/inventario-app/productos` | Agregar producto |
| PUT | `/inventario-app/productos/{id}` | Actualizar producto |
| DELETE | `/inventario-app/productos/{id}` | Eliminar producto |

### Ejemplo de uso

Login:
```http
POST /auth/login
Content-Type: application/json

{
  "username": "jacob",
  "password": "123456"
}
```

Obtener productos con token:
```http
GET /inventario-app/productos
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

Validación — si falta un campo obligatorio:
```json
{
  "descripcion": "La descripción es obligatoria",
  "existencia": "La existencia es obligatoria"
}
```

---

## Autor

**Jacob Benito Lopez**  
[LinkedIn](https://www.linkedin.com/in/jacob-benito-lopez-b2a427354) · [GitHub](https://github.com/Jey00002)

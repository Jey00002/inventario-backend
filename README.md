# 📦 Inventario Backend

API REST para gestión de inventario desarrollada con **Java + Spring Boot + MySQL**, con autenticación segura mediante **JWT**.

---

## 🚀 Tecnologías

| Capa | Tecnología |
|------|-----------|
| Lenguaje | Java 21 |
| Framework | Spring Boot 3.4.3 |
| Seguridad | Spring Security + JWT |
| Persistencia | Spring Data JPA / Hibernate |
| Base de datos | MySQL |
| Build | Maven |

---

## ✨ Funcionalidades

- ✅ CRUD completo de productos
- ✅ Autenticación stateless con JWT
- ✅ Registro e inicio de sesión de usuarios
- ✅ Endpoints protegidos — requieren token válido
- ✅ Validaciones con mensajes de error en español
- ✅ Paginación de resultados

---

## 📁 Arquitectura

```
bl.inventarios
├── controlador      # Capa de presentación (REST Controllers)
├── modelo           # Entidades JPA
├── Repositorio      # Interfaces Spring Data JPA
├── servicio         # Lógica de negocio
└── seguridad        # JWT: JwtUtil, JwtFilter, SecurityConfig
```

---

## ⚙️ Configuración

### Requisitos
- Java 21
- MySQL 8+
- Maven

### `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventarios
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update
server.port=8082
```

### Ejecutar
```bash
mvn spring-boot:run
```

---

## 🔐 Endpoints de Autenticación

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| POST | `/auth/register` | Registrar nuevo usuario | ❌ |
| POST | `/auth/login` | Iniciar sesión y obtener token | ❌ |

### Ejemplo — Register
```http
POST /auth/register
Content-Type: application/json

{
  "username": "jacob",
  "password": "123456"
}
```
Respuesta: `Usuario registrado correctamente`

### Ejemplo — Login
```http
POST /auth/login
Content-Type: application/json

{
  "username": "jacob",
  "password": "123456"
}
```
Respuesta: `eyJhbGciOiJIUzI1NiJ9...` (token JWT)

---

## 📦 Endpoints de Productos

> Todos requieren header: `Authorization: Bearer <token>`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/inventario-app/productos` | Listar todos los productos |
| GET | `/inventario-app/productos/paginado?page=0&size=10` | Listar con paginación |
| POST | `/inventario-app/productos` | Agregar producto |
| PUT | `/inventario-app/productos/{id}` | Actualizar producto |
| DELETE | `/inventario-app/productos/{id}` | Eliminar producto |

### Ejemplo — Obtener productos
```http
GET /inventario-app/productos
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```
```json
[
  {
    "idproducto": "1fdf5b1f-4463-46e6-b02b-f235e11df0ed",
    "descripcion": "polo negro",
    "precio": 21.0,
    "existencia": 90
  }
]
```

### Ejemplo — Validación
```http
POST /inventario-app/productos
Authorization: Bearer <token>

{ "precio": 50.0 }
```
```json
{
  "descripcion": "La descripción es obligatoria",
  "existencia": "La existencia es obligatoria"
}
```

---

## 👤 Autor

**Jacob Benito López**  
Estudiante de Ingeniería de Sistemas — UTP  
[GitHub](https://github.com/Jey00002) · [LinkedIn](https://linkedin.com/in/tu-perfil)

# 🏥 Hospital VM - Ecosistema de Microservicios en Salud

**Institución:** Duoc UC - Sede Alameda
**Desarrolladores:** Carlos Ramírez y Sebastián Moreno
**Versión:** 1.0.0

---

## 📌 Descripción del Proyecto

Hospital VM es un sistema robusto desarrollado bajo una **Arquitectura de Microservicios** utilizando Spring Boot y Spring Cloud. Este proyecto centraliza y optimiza los procesos de una institución de salud, permitiendo la gestión eficiente de pacientes, transacciones y control de inventario clínico.

El ecosistema está diseñado para ser escalable, utilizando un servidor de descubrimiento (Eureka) y un enrutador central (API Gateway) para orquestar las peticiones a los distintos microservicios.

---

## ⚙️ Arquitectura y Tecnologías

| Categoría | Tecnología / Herramienta |
| :--- | :--- |
| **Lenguaje Base** | **Java 21** |
| **Framework Principal** | Spring Boot 3.x |
| **Orquestación** | Spring Cloud (Eureka, API Gateway) |
| **Comunicación API** | Spring WebFlux |
| **Persistencia** | Spring Data JPA / Hibernate |
| **Migraciones** | Flyway |
| **Base de Datos** | MySQL |
| **Contenedores** | Docker |
| **Testing** | JUnit 5, Mockito |

---

## 🌐 Ecosistema de Microservicios

1. **Eureka Server:** Servidor de descubrimiento (puerto `8761`).
2. **API Gateway:** Enrutador central (puerto `8080`).
3. **Hospital VM:** Microservicio de lógica de negocio (puerto `8081`).

---

## 🚀 Requisitos e Instalación

1. **JDK 21** y **Maven**.
2. **Laragon (MySQL)**: Corriendo en puerto `3307`.
3. **Docker**: Para despliegue.

### Configuración de DB
```sql
CREATE DATABASE db_hospital_vm;

```

---

## 🐳 Despliegue con Docker (Paso a Paso)

```bash
# 1. Crear red
docker network create app-net

# 2. Eureka Server
cd eureka-server && mvn clean package -DskipTests && docker build -t eureka-server .
docker run -d --name eureka-server --network app-net -p 8761:8761 eureka-server

# 3. API Gateway
cd ../api-gateway && mvn clean package -DskipTests && docker build -t api-gateway .
docker run -d --name api-gateway --network app-net -p 8080:8080 -e EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/ api-gateway

# 4. Microservicio (Hospital VM)
cd ../hospital-vm && mvn clean package -DskipTests && docker build -t hospital-vm .
docker run -d --name hospital-vm --network app-net -p 8081:8081 -e EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/ -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3307/db_hospital_vm hospital-vm

```

---

## 🧪 Guía de Pruebas (Postman)

### A. Vía API Gateway (Puerto 8080)

* **Crear:** `POST http://localhost:8080/hospital-vm/api/stock-farmacia`
* **Listar:** `GET http://localhost:8080/hospital-vm/api/stock-farmacia`
* **Consultar ID:** `GET http://localhost:8080/hospital-vm/api/stock-farmacia/1`
* **Actualizar:** `PUT http://localhost:8080/hospital-vm/api/stock-farmacia/1`
* **Eliminar:** `DELETE http://localhost:8080/hospital-vm/api/stock-farmacia/1`

### B. Directo al Microservicio (Puerto 8081)

* **Crear:** `POST http://localhost:8081/api/stock-farmacia`
* **Listar:** `GET http://localhost:8081/api/stock-farmacia`
* **Consultar ID:** `GET http://localhost:8081/api/stock-farmacia/1`
* **Actualizar:** `PUT http://localhost:8081/api/stock-farmacia/1`
* **Eliminar:** `DELETE http://localhost:8081/api/stock-farmacia/1`

### C. Prueba de Excepciones

* **Endpoint:** `GET http://localhost:8081/api/stock-farmacia/999`
* **Expectativa:** Retorna `404 Not Found` con mensaje JSON estructurado.

*(Nota: En los métodos POST y PUT, el body debe ser: `{"productoId": 105, "cantidad": 100}`)*


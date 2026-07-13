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
| **Lenguaje Base** | **Java 21** *(Requisito estricto)* |
| **Framework Principal** | Spring Boot 3.x |
| **Orquestación y Enrutamiento**| Spring Cloud (Netflix Eureka, API Gateway) |
| **Comunicación HTTP / API** | Spring WebFlux (WebClient) |
| **Persistencia de Datos** | Spring Data JPA / Hibernate |
| **Migraciones de BD** | Flyway |
| **Base de Datos** | MySQL (Vía Laragon) |
| **Contenedores** | Docker |
| **Testing** | JUnit 5, Mockito |
| **Documentación** | Swagger (OpenAPI 3.0) |

---

## 🌐 Ecosistema de Microservicios

Este repositorio forma parte de una arquitectura mayor. Para el funcionamiento completo del ecosistema, interactúa con los siguientes proyectos:

1. **Eureka Server:** Actúa como el servidor de descubrimiento (puerto `8761`). Todos los microservicios se registran aquí al inicializarse.
2. **API Gateway:** Es el punto de entrada único para el cliente. Enruta las peticiones entrantes hacia el microservicio correspondiente (puerto `8080`).
3. **Hospital VM (Este microservicio):** Contiene la lógica de negocio, operando de manera independiente en el puerto `8081`.

---

## 🚀 Requisitos Previos e Instalación

Para compilar y ejecutar este proyecto, asegúrate de cumplir con los siguientes requisitos:

1. **Java Development Kit (JDK) 21:** Obligatorio para evitar problemas de compatibilidad.
2. **Apache Maven:** Herramienta de gestión de dependencias.
3. **Laragon (MySQL):** Gestor de base de datos local corriendo en el puerto `3307`.
4. **Docker (Opcional):** Para revisión de imágenes empaquetadas.

### Configuración de la Base de Datos

Crea el esquema en tu gestor de base de datos MySQL (a través de Laragon) antes de iniciar:

```sql
CREATE DATABASE db_hospital_vm;
# Sistema de Gestión de Citas Médicas - Hospital

Este proyecto es una aplicación web para la gestión de citas médicas en un hospital. Permite a los doctores del área de
Medicina Interna consultar y gestionar sus citas para el día, así como agendar nuevas citas, editarlas y cancelarlas. El
sistema incluye reglas de negocio para la programación de citas y permite la eliminación lógica de las mismas.

## Funcionalidades

1. **Alta de citas**:
    - Se pueden agendar nuevas citas.
    - Validaciones de reglas:
        - No se pueden agendar citas a la misma hora en un consultorio o con el mismo doctor.
        - Un paciente no puede tener más de una cita en el mismo día con menos de 2 horas de diferencia.
        - Un doctor no puede tener más de 8 citas en un solo día.

2. **Consulta de citas**:
    - Consulta de citas por doctor y fecha.
    - Consulta de citas por consultorio.
    - Consulta de citas por fecha.
    - Cancelación de citas pendientes.

3. **Eliminación lógica**:
    - La eliminación de una cita es lógica mediante un campo `estado` (pendiente, realizada, cancelada), lo que evita
      eliminaciones físicas.

## Tecnologías Usadas

- **Java** con **Spring Boot**.
- **H2** como base de datos en memoria (usado para desarrollo y pruebas).
- **Lombok** para simplificar el código (getters, setters, constructores).
- **Spring Data JPA** para la persistencia de datos.
- **Jakarta Validation** para validaciones de entradas.

## Instrucciones para Ejecutar

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/tu_usuario/gestion-citas-hospital.git

2. Configurar el proyecto:

Desde IntelliJ IDEA, ejecuta la clase principal del proyecto, que contiene el método main, para arrancar el servidor.

La API estará disponible en http://localhost:8080.

Endpoints
Crear una cita
POST /api/appointment

Cuerpo de la solicitud:

```json
{
   "doctorId": 1,
   "consultorioId": 1,
   "fechaHora": "2025-05-10T10:00:00",
  "nombrePaciente": "Juan Perez"
}
```

Consultar citas por doctor y fecha
GET /api/v1/appointments/doctor/{idDoctor}?fecha={fecha}

Parámetros:

idDoctor: ID del doctor.

fecha: Fecha en formato yyyy-MM-dd.

Cancelar cita
DELETE api/v1/appointments/{idCita}

Editar cita
PUT /api/appointments/{idCita}

Cuerpo de la solicitud:

json
Copiar
Editar
{
"doctorId": 1,
"consultorioId": 1,
"fechaHora": "2025-05-10T10:00:00",
"nombrePaciente": "Juan Perez"
}
Estructura del Proyecto
plaintext
Copiar
Editar
.
├── src
│ ├── main
│ │ ├── java
│ │ │ ├── com
│ │ │ │ ├── hospital
│ │ │ │ │ ├── controller
│ │ │ │ │ ├── dto
│ │ │ │ │ ├── exception
│ │ │ │ │ ├── model
│ │ │ │ │ └── service
│ │ └── resources
│ │ └── application.properties
└── pom.xml
Base de Datos
Este proyecto utiliza H2 como base de datos en memoria. Se puede consultar y modificar directamente desde la consola web
de H2 en http://localhost:8080/h2-console.

Configuración de H2 en application.properties:

properties
Copiar
Editar
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
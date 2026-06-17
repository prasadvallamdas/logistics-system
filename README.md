# 🚚 Logitrack: Logistics System Portal

Logitrack is an advanced, full-stack application designed to automate supply chain orchestration, manage real-time cargo bookings, and coordinate delivery truck assignments using smart resource-locking to prevent scheduling conflicts. 

By abandoning traditional multi-page server redirects in favor of an event-driven **JSP + MVC model**, the platform leverages native browser **ES6+ Fetch streams** to transfer stateless JSON payloads. This delivers a fluid, desktop-like user experience with zero screen flickering or tab reloads.

---

## 💻 Tech Stack

* **Backend Core Architecture:** Java, Spring Boot, Spring Data JPA, Hibernate ORM
* **Security Infrastructure:** Spring Security, JWT 
* **Database Engine:** PostgreSQL
* **Frontend UI Layer:** Single-Page Application (SPA) Architecture, Jakarta JSTL Tags, HTML5 validation API, CSS3 Variable Theme Engine
* **Asynchronous Processing:** Client-side JavaScript (Fetch API Streams transmitting JSON Payload Objects)
* **Runtime Container:** Embedded Apache Tomcat Web Servlet Engine

---

## 🌟 Features

### 🔄 JSP-Backed Single-Page Application (SPA)
* Eliminates traditional browser tab reloads and screen blinking.
* Sub-modules change view instantly via hardware-accelerated CSS conversions triggered by client-side JavaScript view controllers.
### 🔑 Stateless JWT Authentication Guard
* Secures operations using cryptographically signed JSON Web Tokens.
* Successful logins return a token payload that the client cache saves and appends to the request headers of all background telemetry-tracking queries.
### 🎛️ Role-Based Security Tiers
* Features a sliding split-entrance gate driven by a segmented access switcher.
* Users map into explicit permission boundaries (`ROLE_USER` or `ROLE_ADMIN`), unlocking protected system metrics and administrative panels based on their assigned token roles.
### 🔒 Automated Truck Resource Locking
* Mitigates scheduling conflicts across shipping lanes.
* Turning a shipment status to `PLACED` automatically captures free drivers and trucks, updates their records to `BUSY`, and safely releases them to the `AVAILABLE` pool only when delivery concludes.

---

## 🔗 API End Points

### 🔓 Security Access Gateways (Public)

* `POST /api/auth/login` - Processes inbound raw identity credentials and returns a secure JWT bearer token string.
* `POST /saveuser` - Securely builds a new user profile inside the database table after verifying that the email account doesn't already exist.

### 📦 Freight & Manifest Pipelines (Secured - Requires Valid User/Admin Token)

* `POST /order` - Creates a new cargo manifest tracking profile matching the incoming payload object.
* `PUT /updateorder` - Asynchronously advances transit steps (`PENDING` ➔ `PLACED` ➔ `COMPLETED`).

### 🚛 Active Truck Rosters (Secured - Requires Valid User/Admin Token)

* `POST /savedriver` - Onboards a new vehicle operator profile with an initial status mapping of `AVAILABLE`.
* `POST /savetruck` - Adds a heavy transport utility truck into the asset deployment pool.

### 🛠️ Administrative Operations (Secured - Strictly Requires `ROLE_ADMIN`)

* `GET /api/admin/reports/all` - Streams master telemetry audit logs for complete operational history analysis.
* `POST /api/admin/order/override` - Modifies records dynamically, allowing an administrator to change driver and truck links for live shipments.

---

## 📂 Project Structure

```text
src/main/java/com/alpha/logisticsproject/
│
├── controller/            # REST API Ingestion & Core View Resolvers
│   ├── AdminController.java
│   ├── AuthController.java       # Handles Token Issuing Pipelines
│   ├── DashboardController.java
│   └── UserController.java
│
├── security/              # 🔐 Spring Security & JWT Infrastructure Layers
│   ├── CustomUserDetailsService.java # Authenticates Identity Keys from DB
│   ├── JwtAuthFilter.java            # Intercepts Requests for Bearer Proofs
│   ├── JwtUtil.java                  # Generates, Parses, and Verifies Tokens
│   └── SecurityConfig.java           # Stateless Filtering Chain Rule Map
│
├── service/               # Core Supply Chain Logic & Resource Calculators
│   ├── DashboardService.java
│   └── OrderService.java
│
├── repository/            # Database Access Layer Objects (Spring Data JPA)
├── entity/                # Hibernate Entity Definition Relationship Matrix
└── exception/             # Centralized System Exception Propagation Map
```

---

## ⚙️ Configuration

**File Path:** `src/main/resources/application.properties`

```properties
spring.application.name=logisticsproject

# Connection setup configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/logistics
spring.datasource.username=postgres
spring.datasource.password=root
spring.datasource.driver-class-name=org.postgresql.Driver

# Object Relational engine settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# View structure directories lookup map
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```

---

## 🗄️ Database Schema

Hibernate automatically maps, compiles, and builds the database tables inside PostgreSQL at startup using the `update` property parameter configured in your application properties.

### Generated Entities Matrix

### 👤 LOGISTIC_USER
* Tracks system login credentials, encrypted handles, and profile permissions (`USER`, `ADMIN`).
### 📍 ADDRESS
* Foundational location directory table managing city distribution and delivery hubs.
### 📦 CARGO
* Stores transit cargo specifications, industrial weights, and count loads.
### 👨‍✈️ DRIVER
* Tracks vehicle operators and real-time availability states (`AVAILABLE`, `BUSY`).
### 🚛 TRUCK
* Registers heavy hauling transport trucks, plate numbers, and carrying capabilities.
### 📑 LOGISTIC_ORDER
* Core transactional context tracking manifest mapping relational foreign keys (`cargo_id`, `driver_id`, `truck_id`, `loadingadd_id`, `unloadingadd_id`).

---

## 🏃 How to Run (Local)

1. Open your local pgAdmin terminal or command line interface and initialize the target database container shell:
   ```sql
   CREATE DATABASE logistics;
   ```
2. Import the project root directory inside Eclipse Enterprise Edition or STS as an **Existing Maven Project**.
3. Right-click your root project folder node and select **Maven ➔ Update Project...** with **"Force Update of Snapshots/Releases"** checked to synchronize the configured Spring Security and JWT dependencies on your classpath. 🔄
4. Right-click `LogisticsprojectApplication.java` and select **Run As ➔ Spring Boot App**. 🚀
5. Confirm a successful launch message in your IDE console, open your browser, and navigate to the application entrance checkpoint: `http://localhost:8080/` 🌐

---

## 📥 Example API Request

### 🔐 Security Sign In & Token Retrieval (`POST /api/auth/login`)

* **Headers:** `Content-Type: application/json`
* **Body:**
```json
{
  "username": "prasad@logitrack.com",
  "password": "SecurePassword123",
  "role": "USER"
}
```
* **Response Payload Object Stream:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiVVNFUiIs... ",
  "username": "Durga Prasad",
  "role": "USER"
}
```

### 📦 Book Cargo Manifest (`POST /order`)

* **Header Token Injector Required:** `Authorization: Bearer <Your_Generated_Token>`
* **Body:**
```json
{
  "orderdate": "2026-06-17",
  "cargoName": "Industrial Steel Components",
  "cargoDescription": "Sensitive automated factory machinery tooling, handle with care.",
  "cargowt": 6800,
  "cargocount": 14,
  "distanceKm": 520,
  "loadingid": 1,
  "unloadingid": 2
}
```

---

## 🎨 Web Frontend

The user interface operates out of a clean single-page view layout schema:

### 🖥️ Single-Canvas Viewport Mapping
* The entire operational application module space is hosted inside a single view layout (`index.jsp`).
* Standard navigation links trigger lightweight client-side JavaScript routers that cycle hidden/visible attributes (`.spa-tab`) instantly, completely bypassing old full-browser window tab reloads.
### 👤 Dynamic Identity Synchronization
* Upon landing, the lifecycle scripts query your browser's native `localStorage` memory cache structure to extract your verified profile tokens, personalize header text greetings, and configure contextual action buttons matching your security level.
### 📡 Asynchronous Background Data Fetching
* Background processing routines leverage the modern `fetch()` API engine to continuously poll backend API routing maps, refreshing tracking data rows and audit table grids smoothly via JSON payload streams.

---

## 🛡️ Validation and Exception Handling

### 🔴 Corporate Form Requirement Styling
* Every input layout critical to database safety displays a vibrant red asterisk (`*`). When criteria conditions break limits (such as an email address missing standard domain extensions or bypassed inputs), CSS pseudo-classes activate an error state, transforming input text underscores into solid high-contrast red warning alert paths.
### 🛡️ Centralized REST Exception Interception
* Features a globally linked fallback wrapper class (`@RestControllerAdvice`) that catches validation anomalies or database duplicate exceptions before execution blocks.
* It translates failures into uniform, safe structures, returning human-readable messages to front-facing alert overlays while keeping system execution secure.

---

## 🚀 Future Implementations

### 🏗️ Microservices & Docker Isolation
* Deconstruct business logic into decoupled domains (Booking, Truck, Auth) packaged inside standalone Docker containers for complete environment consistency and independent resource scaling.
### ☸️ Kubernetes Orchestration
* Deploy containerized services within a Kubernetes cluster to enable automated horizontal auto-scaling, smart load balancing, and resilient self-healing.
### 🔄 Automated CI/CD Pipelines
* Implement continuous integration and delivery pipelines to completely automate regression testing, container packaging, and zero-downtime rolling production updates.
### 🤖 AI-Driven Optimization
* Integrate predictive machine learning models to automatically calculate the most cost-efficient truck routes, forecast market demand patterns, and schedule preventative vehicle maintenance.
### ⚛️ Modernized Frontend Engine Decoupling
* Migrate the view layer from server-side parsed JSP files to a dedicated single-page framework client (like React or Angular). Integrating WebSockets will open up a persistent open data lane, enabling real-time dashboard tracking updates without relying on client-side polling loops.

---

Feel free to fork or ⭐ the repository if you find it helpful!

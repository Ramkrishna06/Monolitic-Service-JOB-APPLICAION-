# 💼 Job Application API

A monolithic REST API for managing job listings, companies, and reviews — built with **Java 17**, **Spring Boot 3.5**, and **PostgreSQL**.

---

## 🧱 Tech Stack

| Layer        | Technology                          |
|--------------|-------------------------------------|
| Language     | Java 17                             |
| Framework    | Spring Boot 3.5.14                  |
| Database     | PostgreSQL (Docker)                 |
| ORM          | Spring Data JPA / Hibernate         |
| Build Tool   | Maven                               |
| Monitoring   | Spring Boot Actuator                |
| DB Admin UI  | pgAdmin 4 (Docker)                  |

---

## 🗂️ Project Structure

```
src/
└── main/java/com/example/JobApplication/
    ├── Company/
    │   ├── Company.java
    │   ├── CompanyController.java
    │   ├── CompanyRepository.java
    │   ├── CompanyService.java
    │   └── CompanyServiceImpl.java
    ├── Job/
    │   ├── Controller/JobController.java
    │   ├── Model/Job.java
    │   ├── Repository/JobRepo.java
    │   └── Service/JobService.java / JobServiceImpl.java
    ├── Reviews/
    │   ├── Review.java
    │   ├── ReviewController.java
    │   ├── ReviewRepo.java
    │   ├── ReviewService.java
    │   └── ReviewServiceImpl.java
    └── JobApplication.java
```

---

## 🐳 Docker Setup

The project uses Docker Compose to run PostgreSQL and pgAdmin locally.

### Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed and running

### Start the containers

```bash
docker-compose up -d
```

This starts two services:

| Service    | Container             | Port  | Description              |
|------------|-----------------------|-------|--------------------------|
| PostgreSQL | `postgres_container`  | 5432  | Main database            |
| pgAdmin 4  | `pgadmin_container`   | 5050  | Database admin UI        |

### pgAdmin Login

Open [http://localhost:5050](http://localhost:5050) in your browser.

| Field    | Value                     |
|----------|---------------------------|
| Email    | `pgadmin4@pgadmin.org`    |
| Password | `admin`                   |

### Connect pgAdmin to PostgreSQL

After logging in, add a new server with:

| Field    | Value        |
|----------|--------------|
| Host     | `postgres`   |
| Port     | `5432`       |
| Database | `JobApp`     |
| Username | `admin`      |
| Password | `postgresql` |

### Stop the containers

```bash
docker-compose down
```

---

## ▶️ Running the Application

Make sure Docker containers are running first, then:

```bash
./mvnw spring-boot:run
```

The app starts on **http://localhost:8080**

---

## 📋 Database Configuration

Found in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/JobApp
spring.datasource.username=admin
spring.datasource.password=postgresql
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

> ⚠️ `create-drop` means data is wiped on every restart. Change to `update` or `validate` for persistent data.

---

## 🔗 API Reference

Base URL: `http://localhost:8080`

---

### 🏢 Company Endpoints — `/company`

#### GET all companies
```
GET /company
```
**Response `200 OK`:**
```json
[
  {
    "id": 1,
    "name": "Google",
    "description": "Search and cloud company",
    "reviews": []
  }
]
```

---

#### GET company by ID
```
GET /company/{id}
```
**Response `200 OK`:**
```json
{
  "id": 1,
  "name": "Google",
  "description": "Search and cloud company",
  "reviews": []
}
```
**Response `404 Not Found`** — if company doesn't exist.

---

#### POST create company
```
POST /company
Content-Type: application/json
```
**Request Body:**
```json
{
  "name": "Google",
  "description": "Search and cloud company"
}
```
**Response `201 Created`:**
```
new Company Posted successfully
```

---

#### PUT update company
```
PUT /company/{id}
Content-Type: application/json
```
**Request Body:**
```json
{
  "name": "Google LLC",
  "description": "Updated description"
}
```
**Response `202 Accepted`:**
```
Company updated
```
**Response `404 Not Found`:**
```
Company not found to update
```

---

#### DELETE company
```
DELETE /company/{id}
```
**Response `202 Accepted`:**
```
Company deleted
```
**Response `404 Not Found`:**
```
Company Not found
```

---

### 💼 Job Endpoints — `/jobs`

#### GET all jobs
```
GET /jobs
```
**Response `200 OK`:**
```json
[
  {
    "id": 1,
    "title": "Backend Developer",
    "description": "Java Spring Boot developer",
    "minSalary": "800000",
    "maxSalary": "1500000",
    "location": "Bengaluru",
    "company": {
      "id": 1,
      "name": "Google",
      "description": "Search and cloud company"
    }
  }
]
```

---

#### GET job by ID
```
GET /jobs/{id}
```
**Response `200 OK`:** Returns a single Job object (same structure as above).

**Response `404 Not Found`** — if job doesn't exist.

---

#### POST create job
```
POST /jobs
Content-Type: application/json
```
**Request Body:**
```json
{
  "title": "Backend Developer",
  "description": "Java Spring Boot developer",
  "minSalary": "800000",
  "maxSalary": "1500000",
  "location": "Bengaluru",
  "company": { "id": 1 }
}
```
**Response `201 Created`:**
```
job posted succesfully
```

---

#### PUT update job
```
PUT /jobs/{id}
Content-Type: application/json
```
**Request Body:**
```json
{
  "title": "Senior Backend Developer",
  "description": "Updated description",
  "minSalary": "1000000",
  "maxSalary": "2000000",
  "location": "Remote"
}
```
**Response `200 OK`:**
```
job updated
```
**Response `404 Not Found`** — if job ID doesn't exist.

---

#### DELETE job
```
DELETE /jobs/{id}
```
**Response `200 OK`:**
```
job deleted
```
**Response `404 Not Found`** — if job ID doesn't exist.

---

### ⭐ Review Endpoints — `/company/{companyId}/reviews`

All review endpoints are scoped under a company.

---

#### GET all reviews for a company
```
GET /company/{companyId}/reviews
```
**Response `200 OK`:**
```json
[
  {
    "id": 1,
    "title": "Great place to work",
    "description": "Amazing culture and work-life balance",
    "rating": 4.5
  }
]
```
**Response `404 Not Found`** — if company has no reviews.

---

#### GET review by ID
```
GET /company/{companyId}/reviews/{reviewId}
```
**Response `200 OK`:** Returns a single Review object.

**Response `404 Not Found`** — if review doesn't exist under that company.

---

#### POST create review
```
POST /company/{companyId}/reviews
Content-Type: application/json
```
**Request Body:**
```json
{
  "title": "Great place to work",
  "description": "Amazing culture and work-life balance",
  "rating": 4.5
}
```
**Response `201 Created`:**
```
Thanks for you heartfelt feedback
```
**Response `404 Not Found`:**
```
Company not found add company first
```

---

#### PUT update review
```
PUT /company/{companyId}/reviews/{reviewId}
Content-Type: application/json
```
**Request Body:**
```json
{
  "title": "Updated title",
  "description": "Updated description",
  "rating": 3.8
}
```
**Response `200 OK`:**
```
Your review for company id x is updated
```
**Response `404 Not Found`:**
```
Your review id not found or company not exist
```

---

#### DELETE review
```
DELETE /company/{companyId}/reviews/{reviewId}
```
**Response `200 OK`:**
```
Your review for company id x is deleted
```
**Response `404 Not Found`:**
```
Your review id not found or company not exist
```

---

## 🩺 Actuator Endpoints

Spring Boot Actuator is fully enabled. Access at:

```
http://localhost:8080/actuator
```

| Endpoint                           | Description                    |
|------------------------------------|--------------------------------|
| `/actuator/health`                 | App health + DB status         |
| `/actuator/info`                   | App name, version, description |
| `/actuator/metrics`                | JVM and HTTP metrics           |
| `/actuator/env`                    | Environment properties         |
| `/actuator/shutdown` *(POST)*      | Graceful shutdown              |

App info is configured in `application.properties`:

```properties
info.app.name=JobApplication
info.app.description=Job build by Ramkrishna
info.app.version=1.0.0
```

---

## 🗄️ Entity Relationships

```
Company (1) ──────< Job (Many)
    │
    └──────────< Review (Many)
```

- A **Company** has many **Jobs** and many **Reviews**
- A **Job** belongs to one **Company**
- A **Review** belongs to one **Company**

---

## 🚀 Quick Start (End-to-End)

```bash
# 1. Start DB
docker-compose up -d

# 2. Run app
./mvnw spring-boot:run

# 3. Create a company
curl -X POST http://localhost:8080/company \
  -H "Content-Type: application/json" \
  -d '{"name":"Google","description":"Search and cloud"}'

# 4. Post a job under that company (use id=1)
curl -X POST http://localhost:8080/jobs \
  -H "Content-Type: application/json" \
  -d '{"title":"Backend Dev","description":"Java dev","minSalary":"800000","maxSalary":"1500000","location":"Bengaluru","company":{"id":1}}'

# 5. Post a review for the company
curl -X POST http://localhost:8080/company/1/reviews \
  -H "Content-Type: application/json" \
  -d '{"title":"Great!","description":"Loved working here","rating":4.8}'

# 6. Fetch everything
curl http://localhost:8080/company
curl http://localhost:8080/jobs
curl http://localhost:8080/company/1/reviews
```

---

## 👤 Author

**Ramkrishna** — [GitHub](https://github.com/Ramkrishna06)

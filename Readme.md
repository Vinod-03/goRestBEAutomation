# GoRest Backend Automation Framework

## 📌 Overview

This project is a **Java-based Backend API Automation Framework** built to test **GoRest public APIs** using **RestAssured, TestNG, and Maven**.
It follows a **clean, modular, and scalable framework design**, suitable for real-world backend automation and interview demonstrations.

---

## 🛠 Technology Stack

* **Java (JDK 8+)**
* **RestAssured** – API automation
* **TestNG** – Test execution & soft assertions
* **Maven** – Build & dependency management
* **Jackson** – Serialization & deserialization
* **Lombok** – Boilerplate code reduction
* **Git & GitHub** – Version control

---

## 📂 Project Structure

```
GorestBackendAutomation
│
├── src/main/java/org/goRestApiAutomation
│   ├── configuration
│   │   └── BaseUri.java
│   ├── endpoints
│   │   └── GetuserEndpoints.java
│   ├── utils
│   │   ├── ApiRequests.java
│   │   ├── ApiAssertions.java
│   │   ├── JavaUtil.java
│   │   └── CommonSerlizationUtil.java
│   └── data
│       ├── requestModel
│       ├── responseModel
│       └── common
│
├── src/test/java
│   ├── gorestgetusers
│   ├── CreateuserScenerious
│   ├── UserUpdate
│   └── DeleteUserById
│
├── src/test/resources
│   └── testng.xml
│
├── pom.xml
└── README.md
```

---

## ✨ Framework Features

* Reusable **API request & assertion utilities**
* Centralized **SoftAssert-based validation layer**
* Strongly typed **request and response POJOs**
* Clean separation of:

    * API calls
    * Test logic
    * Validations
* Test execution via **TestNG Suite XML**
* Easily extensible for new APIs

---

## ⚙️ Prerequisites

Ensure the following are installed:

* Java JDK 21 or above
* Maven
* Git
* IntelliJ IDEA recommended

Verify using:

---

## ▶️ How to Run the Tests

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Vinod-03/goRestBEAutomation.git
cd GorestBackendAutomation
```

### 2️⃣ Execute All Tests

```bash
mvn clean test
```

### 3️⃣ Run Using TestNG Suite

Suite file location:

```
src/test/resources/testng.xml
```

Run a specific suite:

```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
```

---

## 🧪 Test Coverage

The framework currently covers:

* ✅ Get all users
* ✅ Create user
* ✅ Update user
* ✅ Delete user by ID
* ✅ Response schema validation
* ✅ Field-level validations

---

## 🧩 Assertions Design

All common assertions are centralized in:

```
ApiAssertions.java
```

This ensures:

* Reusability
* Consistent validation messages
* Clean test classes

---

## 📊 Reporting

* Uses **TestNG default reports**
* Can be extended to **Allure / Extent Reports**

---

## 🚀 Future Enhancements

* Environment-based execution (QA / Stage / Prod)
* Token management & auth abstraction
* Parallel execution support
* CI/CD integration (Jenkins / GitHub Actions)
* Enhanced reporting

---

## 👤 Author

**Vinod Kumar**



---

✅ **Happy Testing!**

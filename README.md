# Multi-Threaded E-Commerce Order Fulfillment Engine
## Overview
A high-performance CLI Java application designed to simulate concurrent inventory management and transactional processing.
## Features
- Full Database CRUD Operations for Inventory Management.
- Multi-threaded Transaction Engine with explicit thread synchronization.
- Transactional log generation using character-oriented Java Writer streams.
## Prerequisites
- Java Development Kit (JDK 11 or higher)
- H2 Database Engine (or any JDBC-compatible relational database)
## Environment Setup & Configuration
1. Ensure `java` and `javac` are available in your system path.
2. Place your JDBC driver jar (e.g., `h2-*.jar` or `mysql-connector-java.jar`) in a `lib/` folder or include it in your environment classpath.
## How to Compile and Run
Run the following commands from the root directory of the project:
### Compile all modules
`javac -d bin src/com/ecommerce/*.java src/com/ecommerce/config/*.java src/com/ecommerce/model/*.java src/com/ecommerce/repository/*.java src/com/ecommerce/service/*.java src/com/ecommerce/util/*.java`
### Run the application (Include your database driver jar in the classpath)
`java -cp bin:lib/h2-2.2.224.jar com.ecommerce.Main`

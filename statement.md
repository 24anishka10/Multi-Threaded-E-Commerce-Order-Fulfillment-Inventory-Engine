# Problem Statement & Scope
## 1. Problem Statement
In modern e-commerce systems, high traffic can cause critical race conditions where multiple customers attempt to purchase the last remaining item in stock simultaneously. Without synchronization, this leads to negative inventory, database corruption, and a poor user experience. This project builds a high-performance, command-line-driven E-Commerce Order Fulfillment & Inventory Engine that handles concurrent orders safely, persists catalog updates to a database, and generates transactional invoices.
## 2. Scope of the Project
The application is a standalone, terminal-based backend simulation that satisfies all technical constraints:
- **Object-Oriented Design:** Encapsulated domain models with robust state verification.
- **Concurrency Control:** Multi-threaded simulation using synchronized methods to eliminate race conditions.
- **Persistence Layer:** Database CRUD execution tracking live product status.
- **File I/O Automation:** Character-oriented stream processing to auto-generate transactional invoices.
## 3. Target Users
- Backend System Evaluators
- E-Commerce Inventory Administrators
- Transactional Logistics Systems
## 4. High-Level Features
- **Catalog Administration:** CLI tools to Create, Read, Update, and Delete products.
- **Concurrent Order Simulation:** Automated multi-threaded engine simulating multiple concurrent buyers.
- **Thread-Safe Deductions:** Critical section synchronization preventing overselling.
- **Automated Invoice Printing:** Permanent record tracking using Java I/O file writing.

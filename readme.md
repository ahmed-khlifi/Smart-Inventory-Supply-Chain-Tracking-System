# Smart Inventory & Supply Chain Tracking System
A Java Spring Boot application to track inventory levels, manage supply flows, and automate low-stock alerts for small-to-medium businesses.

⚠️ **Project Status:** Currently frozen / in maintenance mode. Core features are implemented, but some functionality is still pending.

This project is designed as a **multi-tenant backend system**, where each company has its own dedicated instance of the backend. Existing features include inventory tracking, supply chain management, low-stock alerts, and scheduled tasks. Planned enhancements include mapping users to their CRUD operations, improving test coverage, and expanding mobile integration.

## Features
- REST APIs for inventory and supply chain management
- Automatic low-stock alerts via email (SMTP)
- Scheduled tasks for periodic inventory checks
- Modular service architecture
- Integration with mobile clients (planned)

## Setup
1. Clone the repo: `git clone https://github.com/ahmed-khlifi/Smart-Inventory-Supply-Chain-Tracking-System.git`
2. Configure database in `application.properties` (Note: username & password for the SMTP server are placeholders, Please put yours.)
3. Run Spring Boot app: `mvn spring-boot:run`
4. Use Postman or curl to test endpoints


## Project Status
- Core REST APIs implemented ✅
- Low-stock alert emails ✅
- Scheduler for periodic checks ✅
- Full test coverage ❌ (in progress)
- Mobile client integration ❌ (planned)
- Multi-tenant ❌ (planned)


Developer ( Ahmed Khelifi )
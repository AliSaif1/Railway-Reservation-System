# 🚆 Railway Reservation System (Desktop Application)

This is a Java-based desktop application that allows users to reserve train tickets, manage bookings, and handle train and seat management. It also includes an admin portal for managing trains and stations.

---

## 📦 Project Structure

This desktop app uses **JavaFX** for the user interface and connects to a **MySQL** database for data storage and retrieval.

---

## 🛠 Features

### 👥 User Features
- Create a new account
- Login securely
- Search and book available train tickets
- View booked tickets
- Cancel reserved tickets
- View station and train details

### 🛡 Admin Features
- Add/delete trains and routes
- View and manage all bookings
- Monitor station connectivity and schedules

---

## 📁 File Overview

| File/Class | Description |
|------------|-------------|
| `HelloApplication.java` | Entry point for the JavaFX application |
| `HelloController.java` | Initial UI controller |
| `CreateAccountController.java` | Handles user registration |
| `HomeController.java` | Main dashboard after login |
| `BookTicketController.java` | Manages the ticket booking logic |
| `BookedTicketsController.java` | Displays user's booked tickets |
| `TrainStationController.java` | Admin controls for managing stations and trains |
| `DBConnectivity.java` | Manages the database connection |
| `REserve.java` | Handles seat reservation logic |
| `Seats.java` | Represents seat entities and availability |
| `Trains.java` | Represents train data models |
| `User.java` | Represents user data models |
| `BookedTickets.java` | Data model for storing ticket booking info |
| `RailwayReservation.sql` | SQL script to initialize the database schema |

---

## 🧰 Technologies Used

- **Java**
- **JavaFX** (UI Framework)
- **MySQL** (Database)
- **JDBC** (Java Database Connectivity)

---

## 🧑‍💻 Getting Started

### Prerequisites

- Java JDK 8 or higher
- MySQL Server
- JavaFX SDK (if not bundled with your IDE)
- IntelliJ IDEA / Eclipse / NetBeans

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/railway-reservation-system.git
cd railway-reservation-system

👨‍💻 Author
Ali Saif – LinkedIn Profile

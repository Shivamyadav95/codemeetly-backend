# CodeMeetly Backend

CodeMeetly is a real-time coding interview platform backend built with Spring Boot.  
It provides APIs and WebSocket services for live coding interviews including chat, video signaling, code execution, and collaboration features.

---

## 🚀 Features

- 🔐 JWT Authentication
- 🔑 Google OAuth Login
- 💬 Real-time Chat (WebSocket)
- 🧑‍💻 Live Code Collaboration
- 🎥 WebRTC Signaling for Video Interview
- 🎨 Collaborative Whiteboard
- ⚡ Secure Code Execution using Docker Sandbox
- 📦 REST APIs for Problems, Rooms, Interviews, Submissions
- 🗄 MySQL Database with Spring Data JPA

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT
- WebSocket (STOMP)
- WebRTC signaling
- Docker (sandbox code execution)
- MySQL
- Maven

---

## Setup

1. Clone repository
git clone https://github.com/Shivamyadav95/codemeetly-backend.git

2. Create database
CREATE DATABASE CodeMeetly;

3. Add environment variables
Create `.env` file:

GOOGLE_CLIENT_ID=
GOOGLE_CLIENT_SECRET=
JWT_SECRET=
DB_PASSWORD=

4. Run project
mvn spring-boot:run

## Project Structure
src
 ├── config
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── websocket


 ---

## 📌 Future Improvements

- Kubernetes deployment
- Code execution queue system
- AI code evaluation
- Interview analytics dashboard

---

## 👨‍💻 Author

Shivam Yadav  
B.Tech Computer Science  
Noida International University

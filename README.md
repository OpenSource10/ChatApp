# ChatApplication

## About the Project

ChatApplication is a console-based project that uses Java Sockets for communication between a server and multiple clients. The project follows a client-server architecture, where the server handles multiple client connections, enabling real-time message exchange.

## Project Structure

```
ChatApplication/
│── .idea/                     # Project-specific settings (IntelliJ IDEA)
│── out/
│   ├── production/
│   │   ├── ChatApplication/
│   │   │   ├── ChatClient     # Compiled ChatClient class
│   │   │   ├── ChatServer     # Compiled ChatServer class
│── src/
│   ├── ChatClient/            # Contains client-side logic to connect to the server and send/receive messages
│   │   ├── ChatClient.java    # Handles user input, connects to server, and displays messages
│   ├── ChatServer/            # Manages client connections and facilitates communication between them
│   │   ├── ChatServer.java    # Listens for incoming client connections and relays messages
│── .gitignore                 # Git ignore file

```

## Source Code Explanation

The `src/` directory contains the core implementation of the chat application, divided into two main components:

- **ChatClient/**: This directory holds the client-side logic necessary for users to connect to the server and communicate. The `ChatClient.java` file is responsible for handling user input, connecting to the server via sockets, sending messages, and displaying received messages in the console.
- **ChatServer/**: This directory contains the server-side implementation, which manages multiple client connections. The `ChatServer.java` file listens for incoming connections from clients, establishes communication channels, and facilitates message transmission between clients.

## Contribution

This project is open for contributors who want to enhance it by adding a graphical user interface (GUI) to improve user experience.


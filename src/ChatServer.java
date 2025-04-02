
import java.io.*;
import java.net.*;
import java.util.*;

    // Server Code
    class ChatServer {
        private static final int PORT = 5555;
        private static Set<Socket> clients = new HashSet<>();

        public static void main(String[] args) {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("[SERVER STARTED] Listening on port " + PORT);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    synchronized (clients) {
                        clients.add(clientSocket);
                    }
                    System.out.println("[NEW CONNECTION] " + clientSocket.getInetAddress());

                    new Thread(new ClientHandler(clientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void broadcast(String message, Socket sender) {
            synchronized (clients) {
                for (Socket client : clients) {
                    if (client != sender) {
                        try {
                            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                            out.println(message);
                        } catch (IOException e) {
                            clients.remove(client);
                        }
                    }
                }
            }
        }

        private static class ClientHandler implements Runnable {
            private Socket clientSocket;

            public ClientHandler(Socket socket) {
                this.clientSocket = socket;
            }

            @Override
            public void run() {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("[MESSAGE] " + message);
                        broadcast(message, clientSocket);
                    }
                } catch (IOException e) {
                    System.out.println("[DISCONNECTED] " + clientSocket.getInetAddress());
                } finally {
                    synchronized (clients) {
                        clients.remove(clientSocket);
                    }
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


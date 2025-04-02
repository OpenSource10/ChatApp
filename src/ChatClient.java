
import java.io.*;
import java.net.*;
import java.util.*;


class ChatClient {
    private static final String SERVER_IP = "127.0.0.1";  // ip url connect the server
    private static final int PORT = 5555; // port number of server

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, PORT); //connecting chat application with server
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // For store the message
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true); //Taking output from server
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to chat server.");

            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("\nFriend: " + message);
                        System.out.print("Me: ");
                    }
                } catch (IOException e) {
                    System.out.println("Connection lost");
                }
            });
            receiveThread.start();

            while (true) {
                System.out.print("ME: ");
                String message = scanner.nextLine();
                out.println(message);
            }
        } catch (IOException e) { //only for exception if any error occur on server
            e.printStackTrace();
        }
    }
}

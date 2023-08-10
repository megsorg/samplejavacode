import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        // Create a server socket on port 8080
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server started on port 8080");

        // Accept incoming connections
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from " + clientSocket.getInetAddress());

            // Create a buffered reader to read input from the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Create a print writer to send output to the client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read the first line of input, which should be a command
            String command = in.readLine();
            System.out.println("Command received: " + command);

            // Execute the command using Runtime.exec() and send the output to the client
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = processOutput.readLine()) != null) {
                out.println(line);
            }
            processOutput.close();

            // Close the connection
            in.close();
            out.close();
            clientSocket.close();
        }
    }
}

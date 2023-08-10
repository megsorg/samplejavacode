import java.io.*;
import java.net.*;
import java.util.*;

public class CloudClient {

    // A method that sends a request to a cloud server and prints the response
    public static void sendRequest(String url, String method, String data) {
        try {
            // Create a URL object from the given url
            URL u = new URL(url);
            // Open a connection to the URL
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            // Set the request method
            conn.setRequestMethod(method);
            // Set the request property to accept JSON
            conn.setRequestProperty("Accept", "application/json");
            // If the method is POST or PUT, write the data to the output stream
            if (method.equals("POST") || method.equals("PUT")) {
                // Set the request property to send JSON
                conn.setRequestProperty("Content-Type", "application/json");
                // Enable writing to the output stream
                conn.setDoOutput(true);
                // Get the output stream of the connection
                OutputStream os = conn.getOutputStream();
                // Write the data bytes to the output stream
                os.write(data.getBytes());
                // Flush and close the output stream
                os.flush();
                os.close();
            }
            // Get the response code of the connection
            int code = conn.getResponseCode();
            // Print the response code
            System.out.println("Response code: " + code);
            // If the response code is 200 (OK), read the response from the input stream
            if (code == 200) {
                // Get the input stream of the connection
                InputStream is = conn.getInputStream();
                // Create a buffered reader to read from the input stream
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                // Create a string builder to store the response
                StringBuilder sb = new StringBuilder();
                // Read each line from the buffered reader and append it to the string builder
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                // Close the buffered reader and the input stream
                br.close();
                is.close();
                // Print the response
                System.out.println("Response: " + sb.toString());
            }
            // Close the connection
            conn.disconnect();
        } catch (Exception e) {
            // Print the exception message
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // A main method to test the code
    public static void main(String[] args) {
        // A cloud server url that handles user data (assume it is vulnerable to injection attacks)
        String url = "http://cloud.example.com/users";
        // A user data in JSON format (assume it contains malicious code)
        String data = "{\"name\":\"Alice\",\"age\":25,\"code\":\"System.exit(0);\"}";
        // Send a POST request to create a new user with the data
        sendRequest(url, "POST", data);
    }
}

package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A concurrent server that handles HTTP GET requests to serve image or document files.
 * It listens on port 35000 and spawns multiple threads to handle concurrent client connections.
 */
public class ConcurrentServerSendImageDocument {

    private static final int PORT = 35000;

    /**
     * Main method to start the concurrent server.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If an I/O error occurs when creating the server socket.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + PORT);
            System.exit(1);
        }

        System.out.println("Server is running and listening on port " + PORT + "...");

        // Create threads to handle client requests concurrently
        for (int i = 0; i < 3; i++) {
            RequestConcurrent request = new RequestConcurrent(serverSocket.accept());
            request.start();
        }
    }

    /**
     * Handles a client request by serving requested image or document files.
     *
     * @param clientSocket The socket representing the client connection.
     */
    public static void handleClientRequest(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream()) {

            // Read the request line
            String requestLine = in.readLine();
            System.out.println("Request: " + requestLine);

            // Parse the request
            String[] requestParts = requestLine.split(" ");
            if (requestParts.length != 3) {
                sendErrorResponse(out, 400, "Bad Request");
                return;
            }

            String method = requestParts[0];
            String path = requestParts[1];
            String httpVersion = requestParts[2];

            if (!method.equals("GET")) {
                sendErrorResponse(out, 405, "Method Not Allowed");
                return;
            }

            // Serve the requested file
            String filePath = "src/main/resource/" + path;
            File file = new File(filePath);
            if (!file.exists() || file.isDirectory()) {
                sendErrorResponse(out, 404, "Not Found");
                return;
            }

            // Send the response
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            String contentType = Files.probeContentType(Paths.get(filePath));

            sendSuccessResponse(out, fileContent, contentType);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sends an error response to the client.
     *
     * @param out          The output stream of the client socket.
     * @param statusCode   The HTTP status code of the error response.
     * @param statusMessage The status message corresponding to the status code.
     * @throws IOException If an I/O error occurs while sending the response.
     */
    private static void sendErrorResponse(OutputStream out, int statusCode, String statusMessage) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        writer.println("HTTP/1.1 " + statusCode + " " + statusMessage);
        writer.println("Content-Type: text/html");
        writer.println();
        writer.println("<html><body><h1>" + statusCode + " " + statusMessage + "</h1></body></html>");
        writer.flush();
    }

    /**
     * Sends a success response with content to the client.
     *
     * @param out        The output stream of the client socket.
     * @param content    The content (bytes) to send in the response body.
     * @param contentType The MIME type of the content being sent.
     * @throws IOException If an I/O error occurs while sending the response.
     */
    private static void sendSuccessResponse(OutputStream out, byte[] content, String contentType) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + content.length);
        writer.println();
        writer.flush();
        out.write(content);
        out.flush();
    }
}

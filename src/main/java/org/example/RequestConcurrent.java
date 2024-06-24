package org.example;

import java.net.Socket;
import static org.example.ConcurrentServerSendImageDocument.handleClientRequest;

/**
 * Represents a concurrent request handler thread for the server.
 * Each instance handles one client connection by invoking the {@link ConcurrentServerSendImageDocument#handleClientRequest(Socket)} method.
 */
public class RequestConcurrent extends Thread {

    private Socket clientSocket;

    /**
     * Constructs a new RequestConcurrent instance for handling a client connection.
     *
     * @param serverSocketAccept The accepted socket connection from the server.
     */
    public RequestConcurrent(Socket serverSocketAccept) {
        clientSocket = serverSocketAccept;
        System.out.println("Accepted connection from client: " + clientSocket);
    }

    /**
     * Executes the logic to handle a single client request.
     * Delegates the handling to {@link ConcurrentServerSendImageDocument#handleClientRequest(Socket)}.
     */
    @Override
    public void run() {
        handleClientRequest(clientSocket);
        return;
    }
}
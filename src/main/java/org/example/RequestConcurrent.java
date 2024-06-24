package org.example;

import java.io.IOException;
import java.net.Socket;


import static org.example.ConcurrentServerSendImageDocument.handleClientRequest;

public class RequestConcurrent extends Thread {

    private Socket clientSocket = null;
    public RequestConcurrent(Socket serverSocketAccept) {

        clientSocket = serverSocketAccept;
        System.out.println("Accepted connection from client: " + clientSocket);
    }

    @Override
    public void run() {
        // Aquí va la lógica para manejar una sola petición del cliente
        handleClientRequest(clientSocket);
        return;
    }
}
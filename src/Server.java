import java.io.*;
import java.net.*;




/*
 * Server
 *
 * This class sets up the server and its essential sockets. After that,
 * the server waits for requests from a client, then makes a thread to handle
 * that incoming request
 *
 */
public class Server {
	protected Socket clientSocket           = null;
	protected ServerSocket serverSocket     = null;
	protected ClientConnectionHandler[] threads    = null;
	protected int numClients                = 0;

	public static int SERVER_PORT = 8080;
	public static int MAX_CLIENTS = 25;

	public Server() {
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			threads = new ClientConnectionHandler[MAX_CLIENTS];
		} catch (IOException e) {
			System.err.println("IOEXception while creating server connection");
		}
	}
	
	protected void runServer() throws IOException {
		while(true) {
				clientSocket = serverSocket.accept();
				
				threads[numClients] = new ClientConnectionHandler(clientSocket);
				threads[numClients].start();
				numClients++;
		}
	}

	public static void main(String[] args) {
		Server app = new Server();
		try {
			app.runServer();
		} catch (IOException e) {
			System.err.println("IOEXception while running server");
		}
	}
}

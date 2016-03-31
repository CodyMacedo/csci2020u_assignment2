import java.io.*;
import java.net.*;
import java.util.Vector;

public class Server {
	protected Socket clientSocket           = null;
	protected ServerSocket serverSocket     = null;
	protected ClientConnectionHandler[] threads    = null;
	protected int numClients                = 0;

	public static int SERVER_PORT = 8080;
	public static int MAX_CLIENTS = 5;

	public ChatServer() {
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			threads = new ClientConnectionHandler[MAX_CLIENTS];
		} catch (IOException e) {
			System.err.println("IOEXception while creating server connection");
		}
	}
	
	protected void runServer() {
		while(true) {
				clientSocket = serverSocket.accept();
				threads[numClients] = new ClientConnectionHandler(clientSocket);
				threads[numClients].start();
				numClients++;
		}
	}

	public static void main(String[] args) {
		Server app = new Server();
	}
}

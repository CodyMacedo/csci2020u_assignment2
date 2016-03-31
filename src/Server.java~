import java.io.*;
import java.net.*;

public class Server {
	protected Socket clientSocket           = null;
	protected ServerSocket serverSocket     = null;

	public static int SERVER_PORT = 8080;

	public Server() {
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
		} catch (IOException e) {
			System.err.println("IOEXception while creating server connection");
		}
	}
	
	protected void runServer() throws IOException {
		while(true) {
				clientSocket = serverSocket.accept();
				ClientConnectionHandler thread = new ClientConnectionHandler(clientSocket);
				thread.start();
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

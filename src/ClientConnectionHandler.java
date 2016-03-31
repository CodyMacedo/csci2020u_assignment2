

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientConnectionHandler extends Thread {
	protected Socket socket       = null;
	protected OutputStream outputStream	= null;
	protected PrintWriter out     = null;
	protected BufferedReader in   = null;

	public ClientConnectionHandler(Socket socket) {
		super();
		this.socket = socket;
		try {
			OutputStream outputStream = socket.getOutputStream();
			out = new PrintWriter(outputStream, true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.err.println("IOEXception while opening a read/write connection");
		}
	}

	public void run() {
		try {
			processCommand();
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void processCommand() {
		String message = null;
		try {
			message = in.readLine();
		} catch (IOException e) {
			System.err.println("Error reading command from socket.");
		}
		if (message == null) {
			return;
		}
		StringTokenizer st = new StringTokenizer(message);
		String command = st.nextToken();
		String args = null;
		if (st.hasMoreTokens()) {
			args = message.substring(command.length()+1, message.length());
		}
		processCommand(command, args);
	}
	
	protected void processCommand(String command, String filename) {
		// these are the commands
		try {
			if (command.equalsIgnoreCase("DIR")) {
				File shared = new File("ServerFiles");
				File[] files = shared.listFiles();
				for (int i = 0; i < files.length; i++) {
					out.println(files[i].getName());
				}
			} else if (command.equalsIgnoreCase("UPLOAD")) {
				File newFile = new File("ServerFiles/" + filename);
				PrintWriter outFile = new PrintWriter(newFile);
				String line = "";
				while ((line = in.readLine()) != null) {
		            outFile.print(line);
		        }
				outFile.close();
			} else if (command.equalsIgnoreCase("DOWNLOAD")) {
				FileInputStream fileIn = new FileInputStream(filename);
				System.out.println(filename);
				copyAllBytes(fileIn,outputStream);
				fileIn.close();
			} else {
				out.println("400 Unrecognized Command: "+command);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private void copyAllBytes(InputStream fileIn, OutputStream out) 
                                               throws IOException {
        byte[] buffer = new byte[1024];
        int numBytes = -1;
        while ((numBytes = fileIn.read(buffer)) > 0) {
            out.write(buffer);
        }
    }
}

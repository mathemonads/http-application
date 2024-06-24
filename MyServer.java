
// Author: Oscar Hernandez, UH ID 29317485
//
// References:
// 	[1] https://docs.oracle.com/javase%2Ftutorial%2F/networking/sockets/clientServer.html
// 	[2] https://docs.oracle.com/javase/tutorial/essential/io/file.html

import java.io.*;
import java.net.*;

public class MyServer {
	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.out.println("Run: java server.java 8080");
		}
		int port = Integer.parseInt(args[0]);

		try (ServerSocket server = new ServerSocket(port)) { // as shown in [1]
			System.out.println("Server: Listening ...");
			try (Socket socket = server.accept()) {
				System.out.println("Server: Connected to client");

				File file = new File("server_file.txt"); // as shown in [2]
				BufferedReader reader = new BufferedReader(new FileReader(file));
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println("Server (sending): " + line);
					writer.println(line);
				}
				reader.close();
				System.out.println("Server: File read and sent");
			} 
		} catch (IOException e) {
			System.out.println("Server (exception):" + e.getMessage());
			e.printStackTrace();	
		}
	}
}

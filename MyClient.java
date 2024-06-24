
// Author: Oscar Hernandez, UH ID 29317485

import java.io.*;
import java.net.*;

public class MyClient {
	public static void main(String[] args) {
		String hostname = "127.0.0.1";
		if (args.length < 1) {
			System.out.println("Run: java client.java 8080");
		}
		int port = Integer.parseInt(args[0]);

		try (Socket socket = new Socket(hostname, port)) {
			System.out.println("Client: Connected to server");

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(new FileWriter("client_file.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println("Client (received): " + line);
				writer.println(line);
			}
			writer.close();
			System.out.println("Client: Received and wrote file");

		} catch(UnknownHostException e) {
			System.out.println("Client (exception): Unknown host");
		} catch(IOException e) {
			System.out.println("Client (exception): I/O");
		}
	}
}

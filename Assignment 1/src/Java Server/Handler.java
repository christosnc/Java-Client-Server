import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

public class Handler extends Thread {
	private Socket socket;

	public Handler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			
			String request_hello = reader.readLine();
			String request_ip_port = reader.readLine();
			String request_userid = reader.readLine();
			
			System.out.println(request_hello + " " + request_ip_port + " " + request_userid);
			
			//Random payload generation
			Random random = new Random(System.currentTimeMillis());
			int min = 300000, max = 2000000;
			int payload_size = random.nextInt((max - min) + 1) + min;
			char payload_array[] = new char[payload_size];
			Arrays.fill(payload_array, 'a');
			String payload = new String(payload_array);
			
			writer.println("WELCOME " + request_userid);
			writer.println(payload);

			socket.close();
		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}

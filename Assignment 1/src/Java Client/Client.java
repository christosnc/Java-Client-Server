import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

	private int uid;
	private String HOST;
	private int PORT;
	private int sum = 0;
	private long time_start = 0;
	private long time_end = 0;

	public Client(String HOST, int PORT, int uid){
		this.HOST = HOST;
		this.PORT = PORT;
		this.uid = uid;
	}

	public void run() {
		for(int i = 0; i < 300; i++){
			try {
				Socket socket = new Socket(HOST, PORT);

				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

				writer.println("HELLO");
				writer.println(socket.getLocalAddress() + ":" + socket.getLocalPort());
				time_start = System.currentTimeMillis();
				writer.println(uid);

				String response_welcome = reader.readLine();
				time_end = System.currentTimeMillis();
				sum += time_end - time_start;
				String response_payload = reader.readLine();

				//System.out.println(response_welcome);
				//System.out.println("Payload: " + (response_payload.length() / 1000) + "KB");

				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sum / 300);
	}
}

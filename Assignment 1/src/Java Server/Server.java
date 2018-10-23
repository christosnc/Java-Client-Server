import java.io.IOException;
import java.net.ServerSocket;

public class Server {

	public static void main(String args[]) {

		int PORT = Integer.parseInt(args[0]);
		int counter = 0;

		long time_start = 0;
		long time_end = 0;

		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server is listening on port " + PORT);
			while(true){
				if(counter == 0) time_start = System.currentTimeMillis();
				if(counter == 2999) time_end = System.currentTimeMillis();
				Handler handler = new Handler(serverSocket.accept());
				handler.run();
			}
		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
		System.out.println("Average throughput: " + (3000 / (time_end - time_start)) + " requests per second");
	}
}

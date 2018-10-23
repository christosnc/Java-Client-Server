
public class Simulator {
	public static void main(String args[]){
		String HOST = args[0];
		int PORT = Integer.parseInt(args[1]);
		int n = Integer.parseInt(args[2]);
		
		for(int i = 0; i < n; i++){
			Client c = new Client(HOST, PORT, i);
			c.start();
		}
	}
}

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Project05_B_MutiServer {
	HashMap clients;
	
	Project05_B_MutiServer(){
		clients = new HashMap<String, DataOutputStream>();
		Collections.synchronizedMap(clients);
	}
	
	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("Server Start....");
			
			while(true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+":"+socket.getPort()+"connect!");
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start(); // run 메서드가 동작함
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}//start
	
	//브로드케스트 기능
	void sendToAll(String msg) {
		Iterator iterator = clients.keySet().iterator();
		
		while(iterator.hasNext()) {
			try {
				DataOutputStream out = (DataOutputStream) clients.get(iterator.next());
				out.writeUTF(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}//sendToAll
	
	public static void main(String[] args) {
		new Project05_B_MutiServer().start();
	}//main
	
	//inner Class
	class  ServerReceiver extends Thread{
		Socket socket; 
		DataInputStream in; 
		DataOutputStream out;
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			String name = "";
			try {
				name = in.readUTF(); //클라이언트 이름 저장
				//같은 이름 존재
				if(clients.get(name) != null) {
					out.writeUTF("#Alread exist name:"+ name);
					out.writeUTF("#Please reconnect by other name");
					System.out.println(socket.getInetAddress()+":"+socket.getPort()+"disConnect!");
					in.close();
					out.close();
					socket.close();
					socket = null;
				//같은이름 없음	
				}else {
					sendToAll("#"+name+"join!");
					clients.put(name, out);
					while(in != null) {
						sendToAll(in.readUTF());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(socket != null) {
					sendToAll("#"+name+"exit!");
					clients.remove(name);
					System.out.println(socket.getInetAddress()+":"+socket.getPort()+"disconnect!");
				}
			}
		}//run
	}//ServerReceiver
}//class

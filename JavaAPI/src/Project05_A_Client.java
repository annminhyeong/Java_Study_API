import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Project05_A_Client {
	
	public static void main(String[] args) {
		try {
			//���� �����
			Socket socket = new Socket("127.0.0.1",9999); // -----> accept()
			System.out.println("Connect Success!");
			//Ű����� �Է��ϱ�
			Scanner scanner = new Scanner(System.in);
			//���� �б�
			String message =  scanner.nextLine();
			//������ �޼��� ������
			OutputStream out = socket.getOutputStream();
			//�ѱ۱��� ����
			DataOutputStream dos = new DataOutputStream(out);
			//���Ͽ� �޼��� ������
			dos.writeUTF(message);
			//�������� ���� �޼��� �б�
			InputStream in = socket.getInputStream();
			//�ѱ۱��� ����
			DataInputStream dis = new DataInputStream(in);
			//���Ͽ��� ���� ������ �б�
			System.out.println("Receive:"+dis.readUTF()+"");
			scanner.close();
			dos.close();
			dis.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
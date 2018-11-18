package notion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSideSocket {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9090);
		Socket server = serverSocket.accept(); // ������������� Socket ʵ��
		
		// ��� Socket ʵ������������ôӿͻ��˷��͹���������
		DataInputStream in = new DataInputStream(server.getInputStream());
		System.out.println(in.readUTF());
		
		// ��� Socket ʵ�����������ͻ��˷�������
		DataOutputStream out = new DataOutputStream(server.getOutputStream());
		out.writeUTF("Hello Client!");
		
		serverSocket.close();
		server.close();
	}
}

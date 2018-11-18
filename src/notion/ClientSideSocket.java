package notion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// Socket �׽���
// ���� TCP ����
// ������ͨ������  ServerSocket ʵ������ʵ����һ���Ͽ������������ͻ��˵�����
// �ͻ���ͨ������ Socket ʵ�������������������

public class ClientSideSocket {
	public static void main(String[] args) throws IOException {
		Socket client = new Socket("127.0.0.1", 9090); // �½�������Ͻ�������
		
		// ��� Socket ʵ������������ͻ������������������
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		out.writeUTF("Hello Server");
		
		// ��� Socket ʵ��������������÷��������ص�����
		DataInputStream  in = new DataInputStream(client.getInputStream());
		System.out.println(in.readUTF());
		
		client.close();
	}
}

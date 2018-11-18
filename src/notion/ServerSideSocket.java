package notion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSideSocket {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9090);
		Socket server = serverSocket.accept(); // 开启侦听并获得 Socket 实例
		
		// 获得 Socket 实例输入流，获得从客户端发送过来的数据
		DataInputStream in = new DataInputStream(server.getInputStream());
		System.out.println(in.readUTF());
		
		// 获得 Socket 实例输出流，向客户端发送数据
		DataOutputStream out = new DataOutputStream(server.getOutputStream());
		out.writeUTF("Hello Client!");
		
		serverSocket.close();
		server.close();
	}
}

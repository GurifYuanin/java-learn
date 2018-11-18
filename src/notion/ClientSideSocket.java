package notion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// Socket 套接字
// 基于 TCP 连接
// 服务器通过创建  ServerSocket 实例，该实例绑定一个断开，用于侦听客户端的请求
// 客户端通过创建 Socket 实例，向服务器发起请求

public class ClientSideSocket {
	public static void main(String[] args) throws IOException {
		Socket client = new Socket("127.0.0.1", 9090); // 新建后会马上进行连接
		
		// 获得 Socket 实例的输出流，客户端向服务器发送数据
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		out.writeUTF("Hello Server");
		
		// 获得 Socket 实例的输入流，获得服务器返回的数据
		DataInputStream  in = new DataInputStream(client.getInputStream());
		System.out.println(in.readUTF());
		
		client.close();
	}
}

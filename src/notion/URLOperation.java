package notion;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLOperation {
	public static void main(String[] args) {
		try {
			// URL 类用于生成 URL 实例
			URL url = new URL("http://www.isempty.site:80/index.php?key=value#tag");
			System.out.println(url.getProtocol()); // 获得协议
			System.out.println(url.getHost()); // 获得主机名
			System.out.println(url.getPort()); // 获得端口号
			System.out.println(url.getPath()); // 获得路径
			System.out.println(url.getQuery()); // 获得查询参数
			System.out.println(url.getRef()); // 获得片段标识符
			
			try {
				// 使用 openConnect 方法打开链接，获得 URLConnection 对象
				URLConnection connect = url.openConnection();
				DataInputStream in = new DataInputStream(connect.getInputStream());
				System.out.println(in.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

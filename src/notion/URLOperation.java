package notion;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLOperation {
	public static void main(String[] args) {
		try {
			// URL ���������� URL ʵ��
			URL url = new URL("http://www.isempty.site:80/index.php?key=value#tag");
			System.out.println(url.getProtocol()); // ���Э��
			System.out.println(url.getHost()); // ���������
			System.out.println(url.getPort()); // ��ö˿ں�
			System.out.println(url.getPath()); // ���·��
			System.out.println(url.getQuery()); // ��ò�ѯ����
			System.out.println(url.getRef()); // ���Ƭ�α�ʶ��
			
			try {
				// ʹ�� openConnect ���������ӣ���� URLConnection ����
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

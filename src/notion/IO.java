package notion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/*
 * 输入输出分为字节流和字符流
 * 
 * InputStream 和 OutputStream
 * 字节流按照字节（1byte = 8bit）来处理数据
 * 本质是通过 read 方法读取一个字节
 * 这个字节是 int 类型，再通过强转变成其他类型
 * 
 * Reader 和 Writer
 * 字符流是：
 * read 读取的时候，将各种编码的数据解码为 Unicode 码元（一个 int）
 * write 写入的时候，将 Unicode 码元（一个 int）编码为各种编码的数据
 * --- charset ---
 * 所以编码和解码需要指定编码集（Charset），默认为系统默认的字符集：
 * InputStreamReader(InputStream in, Charset cs)
 * OutputStreamWriter(OutputStream out, Charset cs)
 * */
public class IO {
	public static void main(String[] args) {
		DataInputStream in = new DataInputStream(System.in);
		DataOutputStream out = new DataOutputStream(System.out);
		try {
			int b = in.read();
			// -1 表示读取结束
			// 13 表示回车符
			while (b != -1 && b != 13) {
				out.write(b);
				b = in.read();
			}
			
			// 也可以直接按照 utf 读取
			// String s = in.readUTF();
			// out.writeUTF(s);
			
			out.flush();
			
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		InputStreamReader inReader = new InputStreamReader(System.in);
//		OutputStreamWriter outWriter = new OutputStreamWriter(System.out);
//		char ch[] = new char[20];
//		try {
//			inReader.read(ch);
//			outWriter.write(ch);
//			outWriter.flush();
//			
//			inReader.close();
//			outWriter.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}

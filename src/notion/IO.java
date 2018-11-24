package notion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/*
 * ���������Ϊ�ֽ������ַ���
 * 
 * InputStream �� OutputStream
 * �ֽ��������ֽڣ�1byte = 8bit������������
 * ������ͨ�� read ������ȡһ���ֽ�
 * ����ֽ��� int ���ͣ���ͨ��ǿת�����������
 * 
 * Reader �� Writer
 * �ַ����ǣ�
 * read ��ȡ��ʱ�򣬽����ֱ�������ݽ���Ϊ Unicode ��Ԫ��һ�� int��
 * write д���ʱ�򣬽� Unicode ��Ԫ��һ�� int������Ϊ���ֱ��������
 * --- charset ---
 * ���Ա���ͽ�����Ҫָ�����뼯��Charset����Ĭ��ΪϵͳĬ�ϵ��ַ�����
 * InputStreamReader(InputStream in, Charset cs)
 * OutputStreamWriter(OutputStream out, Charset cs)
 * */
public class IO {
	public static void main(String[] args) {
		DataInputStream in = new DataInputStream(System.in);
		DataOutputStream out = new DataOutputStream(System.out);
		try {
			int b = in.read();
			// -1 ��ʾ��ȡ����
			// 13 ��ʾ�س���
			while (b != -1 && b != 13) {
				out.write(b);
				b = in.read();
			}
			
			// Ҳ����ֱ�Ӱ��� utf ��ȡ
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

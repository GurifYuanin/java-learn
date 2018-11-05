package notion;

// class ClassName { ... }
// ����д������ java.lang.Class ��ʵ������
// �������޷�ֱ�ӻ�� java.lang.Class ��ʵ������
// ��Ϊ java.lang.Class �Ĺ��캯����˽�еģ�ֻ����������Է���
// ���������ַ������ ClassName ��Ӧ java.lang.Class ��ʵ�����󣨼��£�
// �ٷ������ʵ������Ϊ class type

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;

public class Reflection {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
//		MyClass mc = new MyClass();
		
		// ���ַ��������(���ߵȼ�)
//		System.out.println(Class.forName("forTest.MyClass"));
//		System.out.println(mc.getClass());
//		System.out.println(MyClass.class);
		
		// ��õ�һ��
		Class<?> reflection = Class.forName("notion.MyClass");
		
		// ͨ���ഴ��ʵ�����޲������
//		MyClass mc2 = (MyClass)reflection.newInstance();
		
		// ͨ���ഴ��ʵ�����в������
//		Constructor<?> con = reflection.getConstructor(String.class);
//		MyClass mc3 = (MyClass) con.newInstance("wahaha");
		
		// ������й��캯��
//		Constructor<?> conArr[] = reflection.getConstructors();
//		for (int i = 0; i < conArr.length; i++) {
//			System.out.println(conArr[i]);
//		}

		// ������з���
		// getMethods����õ�ǰ��͸�������� public �ķ���
		// getDeclaredMethods����õ�ǰ��ġ����С������ķ���
//		Method metArr[] = reflection.getMethods();
//		for (int i = 0; i < metArr.length; i++) {
//			System.out.println(metArr[i]);
//		}
		
		// ���ָ������
//		Object obj = reflection.newInstance();
//		Method setSomething = reflection.getDeclaredMethod("setSomething", String.class);
//		Method getSomething = reflection.getDeclaredMethod("getSomething");
//		setSomething.invoke(obj, "hello world");
//		System.out.println(getSomething.invoke(obj));
		
		// �����������
		// getFields����õ�ǰ��͸�������� public ������
		// getDeclaredFields����õ�ǰ��ġ����С�����
//		Field fieArr[] = reflection.getFields();
//		for (int i = 0; i < fieArr.length; i++) {
//			System.out.println(fieArr[i]);
//		}
		
		// ����ض�����
		// set(������, ...����)��setter
		// get(������)��getter
		Object obj = reflection.newInstance();
		Field something = reflection.getField("something");
		something.set(obj, "world hello");
		System.out.println(something.get(obj));
		
		// ��ת�󲻻���ʧ������
		Object o = (Object)"�����ַ���";
		System.out.println(o.getClass().getName());
		
		// ר������ö�����͵�ӳ�䣬Ч�ʸ���
		// K ������ö������
		EnumMap<Color, String> map = new EnumMap<Color, String>(Color.class);
		map.put(Color.BLUE, "��ɫ");
		System.out.println(map.get(Color.BLUE));
	}
}
class MyClass {
	public String something;
	public MyClass() {
		System.out.println("�޲ι��캯����������");
	}
	public MyClass(String str) {
		System.out.println("�вι��캯���������ˣ��������Ϊ��" + str);
	}
	void setSomething(String str) {
		this.something = str;
	}
	String getSomething() {
		return this.something;
	}
}
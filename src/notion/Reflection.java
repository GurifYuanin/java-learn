package notion;

// class ClassName { ... }
// 上面写的类是 java.lang.Class 的实例对象
// 但我们无法直接获得 java.lang.Class 的实例对象
// 因为 java.lang.Class 的构造函数是私有的，只有虚拟机可以访问
// 所以有三种方法获得 ClassName 对应 java.lang.Class 的实例对象（见下）
// 官方称这个实例对象为 class type

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;

public class Reflection {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
//		MyClass mc = new MyClass();
		
		// 三种方法获得类(三者等价)
//		System.out.println(Class.forName("forTest.MyClass"));
//		System.out.println(mc.getClass());
//		System.out.println(MyClass.class);
		
		// 最常用的一种
		Class<?> reflection = Class.forName("notion.MyClass");
		
		// 通过类创建实例（无参情况）
//		MyClass mc2 = (MyClass)reflection.newInstance();
		
		// 通过类创建实例（有参情况）
//		Constructor<?> con = reflection.getConstructor(String.class);
//		MyClass mc3 = (MyClass) con.newInstance("wahaha");
		
		// 获得所有构造函数
//		Constructor<?> conArr[] = reflection.getConstructors();
//		for (int i = 0; i < conArr.length; i++) {
//			System.out.println(conArr[i]);
//		}

		// 获得所有方法
		// getMethods：获得当前类和父类的所有 public 的方法
		// getDeclaredMethods：获得当前类的【所有】声明的方法
//		Method metArr[] = reflection.getMethods();
//		for (int i = 0; i < metArr.length; i++) {
//			System.out.println(metArr[i]);
//		}
		
		// 获得指定方法
//		Object obj = reflection.newInstance();
//		Method setSomething = reflection.getDeclaredMethod("setSomething", String.class);
//		Method getSomething = reflection.getDeclaredMethod("getSomething");
//		setSomething.invoke(obj, "hello world");
//		System.out.println(getSomething.invoke(obj));
		
		// 获得所有属性
		// getFields：获得当前类和父类的所有 public 的属性
		// getDeclaredFields：获得当前类的【所有】属性
//		Field fieArr[] = reflection.getFields();
//		for (int i = 0; i < fieArr.length; i++) {
//			System.out.println(fieArr[i]);
//		}
		
		// 获得特定属性
		// set(上下文, ...参数)：setter
		// get(上下文)：getter
		Object obj = reflection.newInstance();
		Field something = reflection.getField("something");
		something.set(obj, "world hello");
		System.out.println(something.get(obj));
		
		// 上转后不会迷失类类型
		Object o = (Object)"我是字符串";
		System.out.println(o.getClass().getName());
		
		// 专门用于枚举类型的映射，效率更高
		// K 必须是枚举类型
		EnumMap<Color, String> map = new EnumMap<Color, String>(Color.class);
		map.put(Color.BLUE, "蓝色");
		System.out.println(map.get(Color.BLUE));
	}
}
class MyClass {
	public String something;
	public MyClass() {
		System.out.println("无参构造函数被调用了");
	}
	public MyClass(String str) {
		System.out.println("有参构造函数被调用了，传入参数为：" + str);
	}
	void setSomething(String str) {
		this.something = str;
	}
	String getSomething() {
		return this.something;
	}
}
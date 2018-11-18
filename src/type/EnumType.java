package type;

// The member enum Hi can only be defined inside a top-level class or interface or in a static context
// 枚举类型只能定义在类 / 接口的顶部，或在静态上下文里

// 实际上枚举类型在编译后也是一个继承了 Enum 类的类
// 它将所有枚举值定义为 public static final 成员
// 并添加了两个静态方法 values(定义) 和 valueOf(覆盖)
// 比如：
enum Color {
	RED, GREEN, BLUE
}
// 编译后的结果是：class Color extends Enum { ... }
// 所以我们通过 Color.RED 实际上是
// 获取 Color 类上的静态成员 RED 对象
// 而 RED 对象是通过  new Color("RED") 得来的
// 所以你可以 Color.RED.RED.RED...

// 以下每个枚举值其实是通过 new Gender 得来
// 所以枚举值后的参数要和构造方法的参数一致
// Gender.MALE 获得 Gender 实例对象
// Gender.MALE.name() 获得的是 "男" 字符串
enum Gender {
	MALE("男", 0), FEMALE("女", 1); // 分号结束
	// 构造方法
	Gender(String string, int index) {
		System.out.println(index + ":" + string);
	}
	// 每个枚举值的方法
	Gender getMale() {
		return MALE;
	}
	Gender getFemale() {
		return FEMALE;
	}
}

// 枚举值不单单可以是一个 name + ordinal 的拓展了 Enum 类的对象
// 它可以是一个自定义属性和方法的对象
enum Country {
	CHINA {
		@Override
		String getName() { return "中国"; }
	},
	AMERICAN {
		@Override
		String getName() { return "美国"; }
		String getLanguage() { return "English"; }
	};
	String name = "国家";
	String getName() {
		return this.name;
	}
}
// 可以用枚举来实现单例模式
enum Singleton {
	// 通过 Singleton.INSTANCE 来获得单例对象
	// 然后就可以直接使用其下的任意成员与方法
	INSTANCE;
	String name;
	String getName() {
		return this.name();
	}
	void setName(String name) {
		this.name = name;
	}
}
public class EnumType {
	public static void main(String[] args) {
		
		// 通过 name 方法和通过 toString 方法获得枚举值是等价的
		System.out.println(Color.RED.name());
		System.out.println(Color.RED.toString());
		
		// 枚举对象.values() 获得所有枚举值
		// ordinal 是顺序
		// name 是值
		for (Color c : Color.values()) {
			System.out.println(c.ordinal() + ":" + c.name());
		}
		
		// 使用方法获得特定枚举值
		Color red = Color.valueOf(Color.class, "RED");
		System.out.println(red.name());
		
		// 只有被用到时才会调用构造方法
		Gender.values();
		Gender.values();
		
		String china = Country.CHINA.getName();
		System.out.println(china);
		
		// 使用 switch 判断枚举类型
		// case 不需要 Color 作为前缀
		switch(Color.BLUE) {
			case RED: System.out.println("it is red"); break;
			case BLUE: System.out.println("it is blue"); break;
			default: System.out.println("not found"); break;
		}

	}
}

class Demo{
	enum Fruit{
		APPLE, BANANA
	}
	Fruit getFruit(String name) {
		return Fruit.valueOf(Fruit.class, name);
	}
}
package type;

public class TypeConvert {
	public static void main(String[] args) {
		// 1、子转父
		Parent p = new Child();
		p.pay();
		// p.playGame(); // 直接变成父类实例，子类独有的成员和方法会消失
		
		// 2、父转子
		// 直接转会失败
		try {
			Child c = (Child)new Parent();
			c.playGame();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// 3、父转子
		// 原本是子，上转为父，又转回来不会失败
		// 转回来后可以使用子类拥有的成员和方法
		Child c = (Child)(Parent)new Child();
		c.playGame();
		System.out.println(c.score);
		
	}
}

class Parent{
	String name = "我是爸爸";
	Parent() { }
	void print() {
		System.out.println(this.name);
	}
	void pay() {
		System.out.println("花钱");
	}
}

class Child extends Parent {
	Child() { }
	String name = "我是儿子";
	int score = 59;
	void playGame() {
		System.out.println("游戏");
	}
}
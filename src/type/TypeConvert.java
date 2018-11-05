package type;

public class TypeConvert {
	public static void main(String[] args) {
		// 1����ת��
		Parent p = new Child();
		p.pay();
		// p.playGame(); // ֱ�ӱ�ɸ���ʵ����������еĳ�Ա�ͷ�������ʧ
		
		// 2����ת��
		// ֱ��ת��ʧ��
		try {
			Child c = (Child)new Parent();
			c.playGame();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// 3����ת��
		// ԭ�����ӣ���תΪ������ת��������ʧ��
		// ת���������ʹ������ӵ�еĳ�Ա�ͷ���
		Child c = (Child)(Parent)new Child();
		c.playGame();
		System.out.println(c.score);
		
	}
}

class Parent{
	String name = "���ǰְ�";
	Parent() { }
	void print() {
		System.out.println(this.name);
	}
	void pay() {
		System.out.println("��Ǯ");
	}
}

class Child extends Parent {
	Child() { }
	String name = "���Ƕ���";
	int score = 59;
	void playGame() {
		System.out.println("��Ϸ");
	}
}
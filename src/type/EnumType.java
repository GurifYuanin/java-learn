package type;

// The member enum Hi can only be defined inside a top-level class or interface or in a static context
// ö������ֻ�ܶ������� / �ӿڵĶ��������ھ�̬��������

// ʵ����ö�������ڱ����Ҳ��һ���̳��� Enum �����
// ��������ö��ֵ����Ϊ public static final ��Ա
// �������������̬���� values(����) �� valueOf(����)
// ���磺
enum Color {
	RED, GREEN, BLUE
}
// �����Ľ���ǣ�class Color extends Enum { ... }
// ��������ͨ�� Color.RED ʵ������
// ��ȡ Color ���ϵľ�̬��Ա RED ����
// �� RED ������ͨ��  new Color("RED") ������
// ��������� Color.RED.RED.RED...

// ����ÿ��ö��ֵ��ʵ��ͨ�� new Gender ����
// ����ö��ֵ��Ĳ���Ҫ�͹��췽���Ĳ���һ��
// Gender.MALE ��� Gender ʵ������
// Gender.MALE.name() ��õ��� "��" �ַ���
enum Gender {
	MALE("��", 0), FEMALE("Ů", 1); // �ֺŽ���
	// ���췽��
	Gender(String string, int index) {
		System.out.println(index + ":" + string);
	}
	// ÿ��ö��ֵ�ķ���
	Gender getMale() {
		return MALE;
	}
	Gender getFemale() {
		return FEMALE;
	}
}

// ö��ֵ������������һ�� name + ordinal ����չ�� Enum ��Ķ���
// ��������һ���Զ������Ժͷ����Ķ���
enum Country {
	CHINA {
		@Override
		String getName() { return "�й�"; }
	},
	AMERICAN {
		@Override
		String getName() { return "����"; }
		String getLanguage() { return "English"; }
	};
	String name = "����";
	String getName() {
		return this.name;
	}
}
// ������ö����ʵ�ֵ���ģʽ
enum Singleton {
	// ͨ�� Singleton.INSTANCE ����õ�������
	// Ȼ��Ϳ���ֱ��ʹ�����µ������Ա�뷽��
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
		
		// ͨ�� name ������ͨ�� toString �������ö��ֵ�ǵȼ۵�
		System.out.println(Color.RED.name());
		System.out.println(Color.RED.toString());
		
		// ö�ٶ���.values() �������ö��ֵ
		// ordinal ��˳��
		// name ��ֵ
		for (Color c : Color.values()) {
			System.out.println(c.ordinal() + ":" + c.name());
		}
		
		// ʹ�÷�������ض�ö��ֵ
		Color red = Color.valueOf(Color.class, "RED");
		System.out.println(red.name());
		
		// ֻ�б��õ�ʱ�Ż���ù��췽��
		Gender.values();
		Gender.values();
		
		String china = Country.CHINA.getName();
		System.out.println(china);
		
		// ʹ�� switch �ж�ö������
		// case ����Ҫ Color ��Ϊǰ׺
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
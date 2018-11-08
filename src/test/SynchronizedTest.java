package test;

// Ϊ�˱�����⣬���Ǽ��������ڹұ���
// �ȴ��أ�һ������������С����
// �����أ��ұ�ǰ�棬һ��������ȥ��

// ����ȴ��صķ�����������������µ��� wait ���������ó���

// ���뾺���صķ�����
// 	����һ������ִ�е� synchronized ��ʱ������ұ���û��������һֱ���ڹұ�ǰ��
// 	��������
//		1�����������̵߳����� notifyAll �������������ڵȴ��ص��߳̽��뾺����
//		2�����������̵߳����� notify �����������һ���ڵȴ��ص��߳̽��뾺����

// ������ķ�����ʵ�־��ھ����أ��������ֱ�����ߣ�����ȴ��黹Ȼ������

// �ͷ����ķ���
// 	����һ�������� wait �����������һعұڣ��Լ��߽�С����
// 	��������ִ���� synchronized �飬���Զ��黹


public class SynchronizedTest {
	static Object lock = new Object();
	public static void main(String[] args) {
		WaitThread t1 = new WaitThread(lock, "�߳�һ");
		WaitThread t2 = new WaitThread(lock, "�̶߳�");
		t1.start();
		t2.start();
	}
}

class WaitThread extends Thread {
	Object lock;
	String name;
	WaitThread(Object obj, String name){
		this.lock = obj;
		this.name = name;
	}
	public void run() {
		for (int i = 0; i < 10; i++) {
			synchronized(lock) {
				System.out.println(name + " get the lock");
				try {
					// �߳�һ����Ҫ�ȴ�
					if (name.equals("�߳�һ")) lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.notifyAll(); // ֪ͨ�ȴ����̣߳��������������ϣ��������ǻ�������
				try {
					// ��˯һ��
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(name + "�����: " + i);
				// һ������ synchronized �飬���͹黹�ˣ���������������
			}
		}
		
	}
}
package notion;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadLearn {
	public static void main(String[] args) {
		new VolatileTest();
		
		// ����һ���̵߳ķ���
		// һ��ͨ��ʵ��  Runnable �ӿڣ�Ȼ��ʵ�����󴫵ݸ� Thread ��
		Thread t1 = new Thread(new RunnableDemo(), "��ʽһ");
		t1.start();
		
		// �����̳� Thread ��
		Thread t2 = new ThreadDemo();
		t2.setName("��ʽ��");
		t2.start();
		
		// Callable + FutureTask + Thread
		Thread t3 = new Thread(new FutureTask<>(new CallableDemo()));
		t3.start();

		// ��ͣ���ߵļ��ַ���
		// һ��staic sleep(s, ns)����ִ��״̬������������״̬��ָ��ʱ��������״̬�������״̬������õ��� sync ����ô����״̬�ڼ�Ҳ�����ͷ�����
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// ����static yeild()����ִ��״̬�������״̬
		Thread.yield();
		
		// ����join(s, ns)�����߳�(����  xx.join �������ڵ��߳�)��ִ��״̬������������״̬���ȴ��ӽ���ִ�н�����������״̬
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// �ġ�
		// ִ����wait����ִ��״̬����ȴ�����״̬���ȴ� notify/notifyAll �����ͬ������״̬
		// ûִ�� wait ����ȡ sync ��ʧ�ܣ���ִ��״̬����ͬ������״̬
		// �鿴 SynchronizedTest �˽����
		Object lockObject = new Object();
		synchronized(lockObject) {
			boolean condition = true; // ������ĳ�������ʱ���ͷ���������ȴ�״̬
			while (condition) {
				try {
					lockObject.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		// �塢interrupt��ǿ���ж��̵߳�ִ��
		// ����̴߳���ִ��  wait/sleep/join �����У����׳� InterruptedException. 
		// ����߳����ڽ��� IO ��д�����׳� ClosedByInterruptException
		// ����֮���̵߳�״̬������
		// ����ʹ�� Thread.interrupted() �� this.isInterrupted() ����ѯ
		t1.interrupt();

	}
}

class RunnableDemo implements Runnable {
	public void run() {
		System.out.println("RunnableDemo run ...");
	}
}

class ThreadDemo extends Thread {
	public void run() {
		System.out.println("ThreadDemo run ...");
	}
}
class CallableDemo implements Callable<Object> {
	public Object call() {
		System.out.println("CallableDemo run ...");
		return null;
	}
}
// volatile �ؼ��ֱ�֤�ɼ��ԡ�������
// �ɼ��ԣ�һ���̶߳�ȡ����Ȼ���޸Ļ����ϸ��µ��ڴ棬��һ���߳���������Ѿ���ȡ�˱�����
// Ȼ��Ҫ�����޸ģ��ͻᱻ�湤���ڴ���Ч�������´������ȡ����
// �����ԣ���֤ JVM ����Ա�����ָ�����������
// ������֤ԭ���ԣ���дԭ���ԡ�һ���߳� A ��ȡ�����޸ı���������һ���߳� B �޸�ֵ��
// ��ʱ���� A �������������޸ģ���Ϊ���߳� B �޸ĵ�ʱ�� A ������û����Ҫ�޸�ֵ����˼
// �����߳� A �Ĺ����ڴ�������Ч����ʱ A �߳���ȥ�޸ı����ͻ�������ݲ�һ�µ�����

// inc ��������С�� 1W
class VolatileTest {
    public volatile static int inc = 0;
    VolatileTest() {
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                    	inc++;
                };
            }.start();
        }
         
        while(Thread.activeCount() > 1)  // ��֤ǰ����̶߳�ִ����
            Thread.yield();
        System.out.println(inc);
    }
}

// �������
// synchronized
// Lock
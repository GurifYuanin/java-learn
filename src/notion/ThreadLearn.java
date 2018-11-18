package notion;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadLearn {
	public static void main(String[] args) {
		new VolatileTest();
		
		// 创建一个线程的方法
		// 一、通过实现  Runnable 接口，然后将实例对象传递给 Thread 类
		Thread t1 = new Thread(new RunnableDemo(), "方式一");
		t1.start();
		
		// 二、继承 Thread 类
		Thread t2 = new ThreadDemo();
		t2.setName("方式二");
		t2.start();
		
		// Callable + FutureTask + Thread
		Thread t3 = new Thread(new FutureTask<>(new CallableDemo()));
		t3.start();

		// 暂停休眠的几种方法
		// 一、staic sleep(s, ns)：从执行状态进入其他阻塞状态，指定时间后从阻塞状态进入就绪状态（如果拿到了 sync 锁那么阻塞状态期间也不会释放锁）
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 二、static yeild()：从执行状态进入就绪状态
		Thread.yield();
		
		// 三、join(s, ns)：主线程(调用  xx.join 方法所在的线程)从执行状态进入其他阻塞状态，等待子进程执行结束后进入就绪状态
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 四、
		// 执行了wait：从执行状态进入等待阻塞状态，等待 notify/notifyAll 后进入同步阻塞状态
		// 没执行 wait 但获取 sync 锁失败，从执行状态进入同步阻塞状态
		// 查看 SynchronizedTest 了解机制
		Object lockObject = new Object();
		synchronized(lockObject) {
			boolean condition = true; // 当发生某种情况的时候释放锁，进入等待状态
			while (condition) {
				try {
					lockObject.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		// 五、interrupt：强行中断线程的执行
		// 如果线程处于执行  wait/sleep/join 方法中，则抛出 InterruptedException. 
		// 如果线程正在进行 IO 读写，则抛出 ClosedByInterruptException
		// 除此之外线程的状态被设置
		// 可以使用 Thread.interrupted() 或 this.isInterrupted() 来查询
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
// volatile 关键字保证可见性、有序性
// 可见性：一个线程读取变量然后修改会马上更新到内存，另一个线程如果事先已经读取了变量，
// 然后要进行修改，就会被告工作内存无效，得重新从主存读取变量
// 有序性：保证 JVM 不会对变量的指令进行重排序
// 但不保证原子性：读写原子性。一个线程 A 读取但不修改被阻塞，另一个线程 B 修改值，
// 此时进程 A 阻塞结束进行修改，因为在线程 B 修改的时候 A 阻塞且没有想要修改值的意思
// 所以线程 A 的工作内存依旧有效，此时 A 线程再去修改变量就会出现数据不一致的问题

// inc 最终总是小于 1W
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
         
        while(Thread.activeCount() > 1)  // 保证前面的线程都执行完
            Thread.yield();
        System.out.println(inc);
    }
}

// 解决方案
// synchronized
// Lock
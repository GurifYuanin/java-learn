package notion;

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
		
		// Callable 等...
		
		// 暂停休眠的几种方法
		// 一、staic sleep(s, ns)：不释放资源，指定时间后回到就绪队列
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 二、static yeild()：释放所有资源，回到阻塞队列
		Thread.yield();
		
		// 三、join(s, ns)：主线程(调用  xx. join 方法所在的线程)等待子进程执行结束
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 四、wait：线程释放对象锁，进入 lockObject 的等待池
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
		
		// interrupt：强行中断线程的执行
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
package test;

// 为了便于理解，我们假设锁放在挂壁上
// 等待池：一个与世无争的小黑屋
// 竞争池：挂壁前面，一旦有锁就去抢

// 进入等待池的方法：持有锁的情况下调用 wait 方法主动让出锁

// 进入竞争池的方法：
// 	方法一：代码执行到 synchronized 块时，如果挂壁上没有锁，就一直守在挂壁前面
// 	方法二：
//		1：持有锁的线程调用了 notifyAll 方法，叫所有在等待池的线程进入竞争池
//		2：持有锁的线程调用了 notify 方法，随机叫一个在等待池的线程进入竞争池

// 获得锁的方法：实现就在竞争池，如果有锁直接拿走，否则等待归还然后抢锁

// 释放锁的方法
// 	方法一：调用了 wait 方法，把锁挂回挂壁，自己走进小黑屋
// 	方法二：执行完 synchronized 块，锁自动归还


public class SynchronizedTest {
	static Object lock = new Object();
	public static void main(String[] args) {
		WaitThread t1 = new WaitThread(lock, "线程一");
		WaitThread t2 = new WaitThread(lock, "线程二");
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
					// 线程一总是要等待
					if (name.equals("线程一")) lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.notifyAll(); // 通知等待的线程，但锁还在我手上，所以你们还抢不到
				try {
					// 先睡一秒
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(name + "输出了: " + i);
				// 一旦出了 synchronized 块，锁就归还了，所有人重新抢锁
			}
		}
		
	}
}
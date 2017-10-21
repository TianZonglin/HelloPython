package com.imooc.transform;

public class TransferTask implements Runnable {

	private EnergySystem sys;
	private int from;
	private double max;
	private int delay = 10;
	
	public TransferTask(EnergySystem sys,int f,double m){
		this.sys = sys;
		this.from = f;
		this.max = m;
	}
	
	
	
	
	@Override
	public void run() {
		try{
			while(true){
				int to = (int)(sys.getLength()*Math.random());
				double count = max*Math.random();
				sys.transfer(from, to, count);
				//创造出同时访问同一资源的情况
				Thread.sleep((int)(delay*Math.random()));
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnergySystem s = new EnergySystem(100,1000);
		//起100个线程，即模拟每个数组元素都作为发送方进行数据交换，接收方是随机的
		for(int i = 0;i < 100;i ++){
			TransferTask t = new TransferTask(s,i,1000);
			Thread th = new Thread(t,"Trans_"+i);
			th.start();
		/*	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
}


/*
 * 出现资源的不确定性，原因：多个线程同时方位同一资源
	Trans_8从8转移    111.59能量到94当前总合:  100000.00
	Trans_9从9转移     11.24能量到91当前总合:  100000.00
	Trans_8从8转移      0.06能量到11当前总合:  100000.00
	Trans_9从9转移      4.22能量到48当前总合:  100000.00
	Trans_8从8转移      6.66能量到2当前总合:   99999.33
	Trans_9从9转移      0.67能量到24当前总合:  100000.00
	Trans_8从8转移      0.66能量到29当前总合:  100000.00
	Trans_9从9转移      5.72能量到83当前总合:  100000.00
 */

/**
 * 资源竞争
 * 多个线程同时访问某一资源，会发生资源赋值重复，使之损坏不能反应真实的线程访问情况
 * 正确的交互：互斥+同步
 * 
 * 互斥：同一资源在同一时刻只能由同一线程访问
 * 		 增加互斥锁：synchronized(intrinsic lock)		
 * 			synchronized{}块，只有获得锁的线程可以进入，且同一时间只能有一个线程获得锁
 * 同步：通信机制 - 线程通知其他线程自己的进度
 * 		同步借助这些方法实现：wait，notify，notifyAll均为Object对象的成员函数
 * 			wait - 线程进入等待Set
 * 			notifyAll() - 唤醒所有等待线程
 * 		上述两者不可能在同一线程中执行，即上述方法是互斥的 
 * 
 * 注意：等待集合waitset
 * 		共享资源：临界区（Critical Section），进入需要获得互斥锁，访问时如果锁未释放，则进程进入等待集合
 * 				当临界区中线程将锁释放后，
 * 				调用notify方法，会不可控的唤醒Set中的某一线程，
 * 				调用notifyAll方法，会将等待集合中的全部线程均唤醒，当前线程结束释放锁之后全部线程将共同竞争锁
 * 
 * 交互实现：通常是互斥+交互
 * 		synchronized{wait()}块+notifyAll()方法，只有获得锁的线程可以进入，且同一时间只能有一个线程获得锁，释放锁时使用notifyAll()通知
 * 
 * 
 * 
 * 
 * 
 * 多线程编程：
 * 		创建线程及线程的基本操作，
 * 		线程之间变量的可见性，volatile关键字
 * 		争用条件，资源竞争
 * 		线程的互斥：synchronized
 * 		线程的同步：wait/notifyAll
 * 
 * 进阶：
 * 		Java Memory Mode，JMM模型
 * 			描述Java线程如何通过内存进行交互，什么是happens-before原则，如何通过synchronized,volatile & final来实现这一原则
 * 		Locks & Condition对象，Java5.0之后引入
 * 			锁机制和等待条件的高层实现，枷锁和同步通信的机制，java.util.concurrent.locks
 * 		Java锁机制和可见性的
 * 		线程的安全性：原子性synchronized与可见性volatile
 * 		死锁
 * 		多线程交互模型：
 * 			生产者消费者模型
 * 			读写锁模型
 * 			Future模型
 * 			WorkerThread模型
 * 			有些类实现了可以直接使用
 * 		Java5中的并发编程工具
 * 			java.util.concurrent
 * 			线程池ExcutorService
 * 			Callable & Future
 * 			Blocking & Queue 
 * 
 * **/



























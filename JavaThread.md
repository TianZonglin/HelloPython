# Java Thread
	多线程，两种：Thread类 + Runnable接口 （继承自java.lang）
	两者均含有 run方法

	线程创建：
		Thread()
		Thread(String name)
		Thread(Runnable target)
		Thread(Runnable target,String name)
	线程控制：
		启动：
			void start()		
		休眠：
			static void sleep(long millis)				
			static void sleep(long millis,int nanos)
		使其他线程等待当前形成终止
			void join()
			其他线程最长需要等待的时间
			void join(long millis)
			void join(long millis,int nanos)
		当前运行线程释放处理器资源，并重新竞争资源
			static void yield()
	获取线程引用：
		返回当前线程引用
			static Thread currentThread()
	
# 实例	
	基本使用
	public void run(){
		ArmyRunnable TaskS = new ArmyRunnable();
		ArmyRunnable TaskN = new ArmyRunnable();
		Thread armyTaskS = new Thread(TaskS,"Sui");
		Thread armyTaskN = new Thread(TaskN,"Nong");
		//启动
		armyTaskS.start();
		armyTaskN.start();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("关键人物出现");
		Thread Cheng = new HeroThread();
		Cheng.setName("程咬金");
		System.out.println("停止作战");
		//此处如果使用stop()方法，则会使进程突然停止，不能确定完整执行线程类中的善后工作
		TaskS.keepRunning = false;
		TaskN.keepRunning = false;
		//armyTaskS.stop();
		//armyTaskN.stop();
		Cheng.start();
		//调用join方法，此时会等待Cheng线程的结束之后才开始顺序执行其他代码
		//如果注释掉join，则主线程不会等待Cheng线程的结束
		//注意Thread.start()之后使用join()方法会忽略掉线程中的yield方法，即线程类中使用yield也
		会使当前线程必须执行完一遍
		//在停止线程方面非常重要
		try {
			Cheng.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("战争结束");
		/**
		 * 如何正确停止线程
		 * 错误：stop()
		 * 		不要使用stop()方法，其会使线程戛然而止，不能进行善后工作
		 * 错误：interrupt()
		 * 		用途-中断线程-调用后会设置中断状态量：interrupt status
		 * 			static boolean interrupted()	静态方法
		 * 			boolean isInterrupted() 		实例方法
		 * 		之后调用上述量方法会返回一个布尔值
		 * 		在执行join(),sleep()方法时调用interrupt方法会清除 status
		 * 		//如果线程使用sleep休眠，而且主线程使用interrupt作用此线程，那么主线程则会抛出异常，
		 并且原始线程不会停止
		 * 		故，join(),sleep()的执行全部需要捕获interrupt异常
		 * 正确停止方法：
		 * 		使用 [ volatile类型的标志 + while对其的判断 ]
		 * 
		 */
	}
	
	线程交互
		
	  出现资源的不确定性，原因：多个线程同时方位同一资源
			Trans_8从8转移    111.59能量到94当前总合:  100000.00
			Trans_9从9转移     11.24能量到91当前总合:  100000.00
			Trans_8从8转移      0.06能量到11当前总合:  100000.00
			Trans_9从9转移      4.22能量到48当前总合:  100000.00
			Trans_8从8转移      6.66能量到2当前总合:   99999.33
			Trans_9从9转移      0.67能量到24当前总合:  100000.00
			Trans_8从8转移      0.66能量到29当前总合:  100000.00
			Trans_9从9转移      5.72能量到83当前总合:  100000.00
	 

	 
	  资源竞争
	  多个线程同时访问某一资源，会发生资源赋值重复，使之损坏不能反应真实的线程访问情况
	  正确的交互：互斥+同步
	  
	  互斥：同一资源在同一时刻只能由同一线程访问
	  		 增加互斥锁：synchronized(intrinsic lock)		
	  			synchronized{}块，只有获得锁的线程可以进入，且同一时间只能有一个线程获得锁
	  同步：通信机制 - 线程通知其他线程自己的进度
	  		同步借助这些方法实现：wait，notify，notifyAll均为Object对象的成员函数
	  			wait - 线程进入等待Set
	  			notifyAll() - 唤醒所有等待线程
	  		上述两者不可能在同一线程中执行，即上述方法是互斥的 
	  
	  注意：等待集合waitset
	  		共享资源：临界区（Critical Section），进入需要获得互斥锁，访问时如果锁未释放，则进程进入等待集合
	  				当临界区中线程将锁释放后，
	  				调用notify方法，会不可控的唤醒Set中的某一线程，
	  				调用notifyAll方法，会将等待集合中的全部线程均唤醒，
					当前线程结束释放锁之后全部线程将共同竞争锁
	  
	  交互实现：通常是互斥+交互
	  		synchronized{wait()}块+notifyAll()方法，只有获得锁的线程可以进入，
			且同一时间只能有一个线程获得锁，释放锁时使用notifyAll()通知
	  
	  
	  
	  
	  
	  多线程编程：
	  		创建线程及线程的基本操作，
	  		线程之间变量的可见性，volatile关键字
	  		争用条件，资源竞争
	  		线程的互斥：synchronized
	  		线程的同步：wait/notifyAll
	  
	  进阶：
	  		Java Memory Mode，JMM模型
	  			描述Java线程如何通过内存进行交互，什么是happens-before原则，
				如何通过synchronized,volatile & final来实现这一原则
	  		Locks & Condition对象，Java5.0之后引入
	  			锁机制和等待条件的高层实现，枷锁和同步通信的机制，java.util.concurrent.locks
	  		Java锁机制和可见性的
	  		线程的安全性：原子性synchronized与可见性volatile
	  		死锁
	  		多线程交互模型：
	  			生产者消费者模型
	  			读写锁模型
	  			Future模型
	  			WorkerThread模型
	  			有些类实现了可以直接使用
	  		Java5中的并发编程工具
	  			java.util.concurrent
	  			线程池ExcutorService
	  			Callable & Future
	  			Blocking & Queue 
	  
	 

	
	
	
	
	
	
	
	
	
	


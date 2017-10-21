package com.imooc.concurrent;

public class Stage extends Thread {

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
		//注意Thread.start()之后使用join()方法会忽略掉线程中的yield方法，即线程类中使用yield也会使当前线程必须执行完一遍
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
		 * 		//如果线程使用sleep休眠，而且主线程使用interrupt作用此线程，那么主线程则会抛出异常，并且原始线程不会停止
		 * 		故，join(),sleep()的执行全部需要捕获interrupt异常
		 * 正确停止方法：
		 * 		使用 [ volatile类型的标志 + while对其的判断 ]
		 * 
		 */
		
	}
	
	public static void main(String[] args){
		new Stage().start();
	}
	
}




































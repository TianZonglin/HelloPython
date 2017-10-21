package com.imooc.concurrent;

public class ArmyRunnable implements Runnable {

	//保证了线程可以读取其他线程写入的值
	//volatile保证可见性 
	//参考JMM Java内存模型，happens before 原则
	volatile boolean keepRunning = true;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(keepRunning){
			for(int i = 0;i< 5;i ++){
				System.out.println(Thread.currentThread().getName()+"进攻对方次数："+i);
				
				//让出处理器时间，每次都重新调度
				Thread.yield();
/*				停止作战
				Nong进攻对方次数：1
				Sui进攻对方次数：3
				Nong进攻对方次数：2
				Sui进攻对方次数：4
				Nong进攻对方次数：3
				Nong进攻对方次数：4
				Nong结束
				Sui结束*/
/*				if(!keepRunning)break; 添加此监测后会立即退出，安全的退出
				停止作战
				Nong结束
				Sui进攻对方次数：4
				Sui结束*/
			}
			
			//如果使用stop()方法，此处不一定会输出
			//上述for循环应该看做一种原子操作，即停止命令之后，当前循环需要运行完毕
			//或者使用break跳出循环，以便立即结束
			System.out.println(Thread.currentThread().getName()+"结束");
			
		}
	}

}

package com.imooc.transform;

public class EnergySystem {

	private final double[] boxes;
	private final Object lock = new Object();
	
	public EnergySystem(int n,int initial){
		boxes = new double[n];
		for(int i = 0;i < boxes.length; i ++){
			boxes[i] = initial;
		}
		
	}
	
	public void transfer(int from,int to,double count){
		
		//如何保证互斥
		synchronized(lock){
			 
			//if(boxes[from] < count){
			//	return;
			//}
			//避免重复获取锁的开销，应该等待锁释放成立，而不是返回重新竞争锁
			while(boxes[from] < count){
				try {
					lock.wait();
					System.out.println(Thread.currentThread().getName()+" waiting...");
					//执行后当前进程会进入等待集合： wait set
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.print(Thread.currentThread().getName());
			boxes[from] -= count;
			System.out.printf("从%d转移%10.2f能量到%d",from,count,to);
			boxes[to] += count;
			System.out.printf("当前总合: %10.2f%n",getTotal());
			
			//唤醒所有正在等待的线程（均在等待集合waitset中）
			lock.notifyAll();
		}
		
	}
	
	public double getTotal(){
		double sum = 0;
		for(int i = 0;i < boxes.length;i ++){
			sum += boxes[i];
		}
		return sum;
	}
	
	public int getLength(){
		return boxes.length;
	}
}

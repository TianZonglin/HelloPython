package com.imooc.concurrent;

public class Actor extends Thread {
	
	public void run(){
		System.out.println(getName()+"是一个演员");
		int count = 0;
		boolean keepRunning = true;
		while(keepRunning){
			System.out.println(getName()+"登台演出"+(++count));
			if(count==10){
				keepRunning  = false;
			}
			if(count%2 == 0){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(getName()+"的登台演出结束了");
	}
	
	public static void main(String[] args){
		Thread actor = new Actor();
		actor.setName("Mr.Thread");
		actor.start();
		Thread actressThread = new Thread(new Actress(),"Mr.Runnable");
		actressThread.start();
		//同一时间同一个处理器只能运行同一个线程
		//结果：
/*			Mr.Thread是一个演员
			Mr.Thread登台演出1
			Mr.Thread登台演出2
			Mr.Runnable是一个演员
			Mr.Runnable登台演出1
			Mr.Runnable登台演出2
			Mr.Thread登台演出3
			Mr.Thread登台演出4
			Mr.Runnable登台演出3
			Mr.Runnable登台演出4
			Mr.Thread登台演出5
			Mr.Thread登台演出6
			Mr.Runnable登台演出5
			Mr.Runnable登台演出6
			Mr.Thread登台演出7
			Mr.Thread登台演出8
			Mr.Runnable登台演出7
			Mr.Runnable登台演出8
			Mr.Thread登台演出9
			Mr.Thread登台演出10
			Mr.Runnable登台演出9
			Mr.Runnable登台演出10
			Mr.Thread的登台演出结束了
			Mr.Runnable的登台演出结束了*/
	}

}


class Actress implements Runnable{

	@Override
	public void run(){
		System.out.println(Thread.currentThread().getName()+"是一个演员");
		int count = 0;
		boolean keepRunning = true;
		while(keepRunning){
			System.out.println(Thread.currentThread().getName()+"登台演出"+(++count));
			if(count==10){
				keepRunning  = false;
			}
			if(count%2 == 0){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(Thread.currentThread().getName()+"的登台演出结束了");
	}
	
}
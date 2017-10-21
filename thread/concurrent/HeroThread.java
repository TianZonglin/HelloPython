package com.imooc.concurrent;

public class HeroThread extends Thread {


	public void run(){
		System.out.println(Thread.currentThread().getName()+"开始了战斗");
		for(int i = 0;i < 10;i ++){
			System.out.println(Thread.currentThread().getName()+"攻击对方");
			Thread.yield();
		}
		System.out.println(Thread.currentThread().getName()+"结束战斗");
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

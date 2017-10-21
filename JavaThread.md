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
	
	
	
	
	


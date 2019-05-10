package basical;


/**
 * 功能：测试线程安全_synchronized问题
 * 	例子：抢苹果问题
 * 	1.
 * @author Zhang
 *
 */
public class ThreadSafe_synchronized{
	
	
	public static void main(String[] args) {
		Runnable runnable=new AppleRunnable();
		Thread thread=new Thread(runnable);
		Thread thread2=new Thread(runnable);
		thread.setName("小强");
		thread2.setName("小明");
		thread.start();	//这个线程只要开启就要不停的拿，直到结束；这个就需要run来实现
		thread2.start();
		
	}
	
	
}

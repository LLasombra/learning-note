package basical;

import org.junit.Test;


/**
 * 功能：测试Thread的Sleep()/Join()方法
 * 
 * 
 */
public class testThreadJion_sleep extends Thread{
//	public testThreadJion(String namw) {
//		super(namw);
//	}
	@Override
	public void run() {
		for(int i=0;i<=100;i++){
			if(i==10){
				try {
					Thread.sleep(1000000000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String threadName=Thread.currentThread().getName();
			System.out.println(threadName+":"+i);
		}
	}
	
	/**
	 * 说明：这里测试sleep()方法和join()方法
	 * 	2.这里先执行main，在执行thread;thread.join():意思是把thread加进来
	 * 
	 */
	public static void main(String[] args) {
		Thread thread=new testThreadJion_sleep();
		thread.start();

		for(int i=0;i<100;i++){
//			if(i==10)
//				try {
//					thread.join();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			String threadName=Thread.currentThread().getName();
			System.out.println(threadName+":"+i);
		}
	}
	
	@Test
	public void test() {
		Thread thread=new testThreadJion_sleep();
		thread.start();

		for(int i=0;i<100;i++){
//			if(i==10)
//				try {
//					thread.join();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			String threadName=Thread.currentThread().getName();
			System.out.println(threadName+":"+i);
		}
	}
}



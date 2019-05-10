package basical;

import org.junit.Test;



/**
 *关于线程：
 *	1.在Java中，Thread类代表一个线程
 *
 * 	2.实现线程的两种方法：
 * 		2.1继承Thread接口
 * 		2.2实现Runnable接口
 * 
 * 	3.继承线程Tread类：
 * 		3.1必须 重写run()方法：放置实质的线程体
 * 
 * 	4.启动线程：
 * 		4.1创建线程Thread对象
 * 		4.2调用thread对象的Start()方法启动线程，而不是run()方法
 *	
 *	5.实现Runnable接口的方式：
 *		5.1创建实现Runnable接口的实现类：必须实现run()方法
 *		5.2创建 5.1对应的Runnable接口的实现类对象
 *		5.3创建Thread对象，利用Thread(Runnable mr)
 *		5.4对应Thread类start()方法启动线程
 *	
 *	6.方法
 *		6.1调用sleep方法,使线程休眠一段时间
 *		6.2调用join方法：当前线程调用其他线程
 *		6.3执行I/O操作：
 *		6.4 interrupt方法：调用阻塞线程的 ；直接结束sleep，并抛出一个InterruptedException异常
 *		6.5isAlive方法：
 *	
 *	7.线程安全问题：
 *		7.1理解并写出写出不安全的代码：多线程共享同一变量资源
 *		7.2使用synchronized关键字修饰方法：synchronized参照共同的一个对象
 *	
 *	8.关于线程通信
 *		8.1方法：wait(),notify(),notifyAll();
 *		8.2这些方法要在同步方法中调用
 *
 *	9.Notice:testThread(在main函数中创建的线程的run方法可以使用sleep；其他非main函数中，调用带有sleep的run创建的线程不会执行run方法)
 *		当run方法中使用sleep时，new出来的对象就不会执行run方法了，可以使用sleep加回来;或者在main方法中创建线程
 */
public class _Thread {
	
	
	

	/**
	 * 说明：这里测试sleep()方法和join()和Interrupt()方法
	 * 	2.这里先执行main，在执行thread;thread.join():意思是把thread加进来
	 * 	3.
	 * @throws InterruptedException 
	 * 
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread thread=new testThreadJion_sleep();//次线程；主线程是main(函数本身)
		
		//判断线程是否活着
		System.out.println(thread.isAlive());			//false
		thread.start();
		thread.interrupt();
		System.out.println(thread.isAlive());			//true
		thread.join();
		System.out.println(thread.isAlive());			//false
		
		//已经执行过的线程，再次调用会出现异常
		//		thread.start();
	}
	
	
	@Test
	/**
	 * 功能：实现Runnable()接口:
	 * 	1.在实现Runnable接口时，将i写在方法外部会成为共享变量
	 * 	2.Thread thread=new Thread(myRunnable);	//这里传进来的myRunnable是一个对象，为引用类型:由于引用所以达到了共享变量的目的
	 *	3.使用Runnable接口创建线程：
	 *		.实现Runnable接口的方式：
	 *		3.1创建实现Runnable接口的实现类：必须实现run()方法
	 *		3.2创建 3.1对应的Runnable接口的实现类对象
	 *		3.3创建Thread对象，利用Thread(Runnable mr)
	 *		3.4对应Thread类start()方法启动线程
	 */
	public void testRunnableThread() {
		MyRunnable myRunnable=new MyRunnable();
		//这里传进来的myRunnable是一个对象，为引用类型:由于引用所以达到了共享变量的目的
		Thread thread=new Thread(myRunnable);
		Thread thread2=new Thread(myRunnable);
		thread.start();
		thread2.start();
		
	}
	
	
	@Test
	/**
	 * 练习：不考虑线程安全问题：一起用i
	 * 	使用Thread类，创建两个线程，打印1-100:
	 * 		1.将变量i设置为static或使用对象(把i当做属性)
	 * 		2.使用Runable接口
	 */
	public void testThread2() {

		FirstThread thread = new FirstThread("sd" );
		FirstThread thread2 = new FirstThread( "ssjiud");
		thread.start();
		thread2.start();
	}
	
	
	@Test
	/**
	 * 功能：测试线程:不同的i
	 * 	1.在当run方法中使用sleep时，new出来的对象就不会执行run方法了，可以使用sleep加回来(在main函数中创建的线程的run方法可以使用sleep；其他非main函数中，调用带有sleep的run创建的线程不会执行run方法)
	 */
	public void testThread() {
		//1.创建线程
		Thread thread=new FirstThread();
		//这里的FristThraed如果在run方法中使用了sleep函数，则这个start开始后不会打印任何东西
		thread.start();
//		
//		for(int i=0;i<100;i++){
//			String threadName=Thread.currentThread().getName();
//			System.out.println(threadName+":"+i);
//		}
	}
}


















/**
 * 
 *	1.当run方法中使用sleep时，new出来的对象就不会执行run方法了，可以使用sleep加回来
 *		等价于：(在main函数中创建的线程的run方法可以使用sleep；其他非main函数中，调用带有sleep的run创建的线程不会执行run方法)
 */
class FirstThread extends Thread{
	private static int i;
	@Override
	public  void  run() {
		for(;i<=100;i++){
			String threadName=Thread.currentThread().getName();
			System.out.println(threadName+":"+i);
		}
	}
	public FirstThread() {
		// TODO Auto-generated constructor stub
	}
	public FirstThread(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
}

package basical;

/**
 * 
 * 线程方法：
 * 	1.yiled()	//“执行状态”下的线程可以调用yield方法，该方法用于主动出让CPU控制权。
 *
 * @author Zhang
 *
 */
public class MyRunnable implements Runnable {
	int i;//写到外面的I是共享变量
	@Override
	public void run() {
		for(;i<100;i++){
//			if(i==10)
//				Thread.yield();//“执行状态”下的线程可以调用yield方法，该方法用于主动出让CPU控制权。
			String threadName=Thread.currentThread().getName();
			System.out.println(threadName+":"+i);
		}
	}

}

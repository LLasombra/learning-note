package basical;


/**
 * 
 * 功能：测试优先级
 *
 */
public class TestThreadPriority extends Thread{
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
	public TestThreadPriority() {
		// TODO Auto-generated constructor stub
	}
	public TestThreadPriority(String name) {
		super(name);
	}
	
	
	
	public static void main(String[] args) {
		TestThreadPriority priority=new TestThreadPriority();
		priority.start();
		
		//设置优先级，但是不一定准，所以不用
		priority.setPriority(MAX_PRIORITY);
		
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
		
		System.out.println(priority.getPriority());
		System.out.println(Thread.currentThread().getPriority());
	}
}

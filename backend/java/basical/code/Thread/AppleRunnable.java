package basical;

public class AppleRunnable implements Runnable {
	private int appleCount=5;	//这个变量是共享的
	
	//一次拿一个
	synchronized boolean getApple() {
		if(appleCount>0){
			appleCount--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"拿走了一个苹果，还剩下"+appleCount+"个苹果！");
			return true;
		}else{
			System.out.println(Thread.currentThread().getName()+"已经死了！");
			return false;
		}
		
	}
	
	@Override
	//不停的拿，拿到没有结束
	public void run() {
		boolean flag=getApple();
		while(flag){
			 flag=getApple();
		}
			
	}

}

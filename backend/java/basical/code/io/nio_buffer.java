package basical;

import java.nio.ByteBuffer;

import org.junit.Test;


/**
 * 一、缓冲区(Buffer):在Java NIO中负责数据的存取，缓冲器对的实质就是数组：用于存储不同数据类型的数据
 * 
 *	1. 根据数据类型的不同(bollean除外)，提供了相应的缓冲区：
 * 		ByteBuffer
 * 		CharBuffer
 * 		ShortBuffer
 * 		IntBuffer
 * 		LongBuffer
 * 		FloatBuffer
 * 		DoubleBuffer
 * 	2.上述缓冲区的管理方法几乎都是一致的，通过allocate()创建缓冲区
 * 
 * 二、缓冲区存取数据的2个核心方法：
 * 	1.put():存数据到缓冲区
 * 	2.get():获取缓冲区的数据
 * 	3.flip():模式的转换:从写变为了读
 * 	4.rewind():表示可以重复读取数据
 * 	5.allocate():分配Buffer大小
 * 	6.clear():清空缓冲区，回到刚分配是的状态，但是缓冲区的数据依旧存在，但是处于遗忘状态
 * 	7.hasRemaining():判断缓冲区中是否还有剩余的数据
 *	8. remaining():有的话，还有几个
 *
 * 三、缓冲区中的四个核心属性:position<=limit<=capacity
 * 	1.capacity:容量，表示缓冲区中最大的存储数据的容量，一旦声明就不能改变
 * 	2.limit:界限，表示缓冲区中可以操作数据的大小.(limit之后的数据不能进行读写)
 * 	3.position:位置，表示缓冲区正在操作对的数据的位置
 *	
 *	4.mark:标记，表示记录当前的position的位置。可以通过reset()的恢复到mark的位置。
 *	
 *
 *四、直接缓冲区与非直接缓冲区：
 *	1.非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在JVM的内存中。
 *	2.直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区直接分配在系统的物理内存中，可以提高效率。
 *		2.1 ByteBuffer buffer=ByteBuffer.allocateDirect(1024);			//这里一次读取一块，所以要while进行读完
 *		2.1 MappedByteBuffer inMappedBuff=inchannel.map(MapMode.READ_ONLY,0,inchannel.size());		//一次性分配足够的内存就读完了，因此不需要while
 *		2.3 
 *			2.3.1.非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在JVM的内存中。
 *		2.3.2.直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区直接分配在系统的物理内存中，可以提高效率。
 *			2.1缺点：资源占用量大，而且数据进去之后，所有的操作权都归了OS
 *			2.2直接缓冲区是去掉了copy的步揍
 *	3.isDirect():判断是否是直接缓冲区
 *
 */
public class TestBuffer {
	
	@Test
	/**
	 * 	1.非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在JVM的内存中。
	 *	2.直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区直接分配在系统的物理内存中，可以提高效率。
	 *		2.1缺点：资源占用量大，而且数据进去之后，所有的操作权都归了OS
	 *		2.2直接缓冲区是去掉了copy的步揍
	 */
	public void testAllocate_Direct() {
		//1.在系统的物理内存上直接分配空间
		ByteBuffer buffer=ByteBuffer.allocateDirect(1024);
		System.out.println(buffer);
		System.out.println(buffer.isDirect());
	}
	
	
	@Test
	/**
	 * 功能：测试Buffer的mark()方法：mark()之后，可以通过reset()恢复到mark时的position
	 */
	public void testMark() {

		String str="abcdefg";
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		buffer.put(str.getBytes());
		buffer.flip();
		byte[]b=new byte[buffer.limit()];
		buffer.get(b, 0, 2);//将其读入到b中
		System.out.println(buffer.position());//2
		buffer.mark();
		buffer.get(b, 2, 2);//继续想b中存放，之前的不会覆盖
		System.out.println(buffer.position());//4
		buffer.reset();
		System.out.println(buffer.position());//2
		
		System.out.println(new String(b,0,b.length));//abcd
	}
	
	
	@Test
	/**
	 * 功能：
	 * 	1.测试Buffer中的capacity()/limit()/position()
	 * 	2.缓冲区的操作：
	 * 			1.put():存数据到缓冲区
	 * 			2.get():获取缓冲区的数据
	 *			3.flip():模式的转换:从写变为了读
	 * 			4.rewind():表示可以重复读取数据
	 * 			5.allocate():分配Buffer大小
	 * 			6.clear():清空缓冲区，回到刚分配是的状态，但是缓冲区的数据依旧存在，但是处于遗忘状态
	 *			7.hasRemaining():判断缓冲区中是否还有剩余的数据
	 *			8. remaining():有的话，还有几个
	 */
	public void testByteBuffer() {

		//1.创建一个指定大小的缓冲区：
		System.out.println("----------allocate--------------");
		ByteBuffer buffer=ByteBuffer.allocate(5);
		
		//2.相关的属性
		System.out.println(buffer.capacity());		//1024
		System.out.println(buffer.limit());			//1024
		System.out.println(buffer.position());		//0
		
		//3.利用put()方法存数据
		System.out.println("----------put--------------");
		String str="123456";
		buffer.put(str.getBytes());
		System.out.println(buffer.capacity());	
		System.out.println(buffer.limit());			//1024
		System.out.println(buffer.position());			//6,下一个要操作的位置
		
		//4.从写的模式转换到读取的模式
		System.out.println("----------flip--------------");
		buffer.flip();
		System.out.println(buffer.capacity());	
		System.out.println(buffer.limit());			//6
		System.out.println(buffer.position());	
		
		//5.利用get()方法取数据：
		System.out.println("----------get--------------");
		byte []b=new byte[buffer.limit()-1];
		ByteBuffer buffer2=buffer.get(b);
		System.out.println(buffer.capacity());	
		System.out.println(buffer.limit());	
		System.out.println(buffer.position());	
		System.out.println(buffer2);
		//输出读取到的内容：
		System.out.println(new String (b,0,b.length));
	
		//6.hasRemaining()
		System.out.println("----------hasRemaining--------------");
		if(buffer.hasRemaining()){
			System.out.println(buffer.remaining());
		}
		
		//7.rewind():
		System.out.println("----------rewind--------------");
		System.out.println(buffer.capacity());		 
		System.out.println(buffer.limit());			 
		System.out.println(buffer.position());		 
		
		//8.clear()
		System.out.println("----------clear--------------");
		buffer.clear();
		System.out.println(buffer.capacity());		 
		System.out.println(buffer.limit());			 
		System.out.println(buffer.position());	
		System.out.println((char)buffer.get(0)); 			//1
		
		
		
	}
	
}

package basical;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/**
 * 一、通道(Channel):用于源节点与目标节点的连接。在Java NI中负责缓冲区中数据的传输。Channel本身不存储数据(类似于铁路)，因此要配合缓冲区使用(火车)
 *
 * 二、Channel的主要实现类：
 * 	java.nio.channels.Channel接口：
 *		1.FileChannel		//本地
 *		2.SocketChannel		//网络
 *		3.ServerChannel		//网络
 *		4.DatagramChannel	//网络
 *
 *三、获取Channel：
 *	1.Java针对Channel的类提供了getChannel()方法
 *		inchannel = fileInputStream.getChannel();
 *		1.1本地：
 *			**FileInputStream、FileOutputStream
 *			**RandomAccessFile
 *		1.2网络：
 *			**Socket
 *			**ServerSocket
 *			**DatagramSocket
 *	2.在JDK1.7中的NIO2针对各个Channel提供了静态的方法open()
 *		inchannel = FileChannel.open(Paths.get("雪中悍刀行群像.rmvb"), StandardOpenOption.READ);
		outchannel = FileChannel.open(Paths.get("copy3.rmvb"), StandardOpenOption.WRITE,StandardOpenOption.CREATE	);
			
 *	3.在JDK1.7中的NIO2的Files工具类的newByteChannel()
 *
 *四、Channel之间得数据传输：
 *	1.transferFrom():outchannel.transferFrom(inchannel,0,inchannel.size());
 *	2.transferTo():inchannel.transferTo(0, inchannel.size(), outchannel);
 *
 *五、分散(Scatter)与读取(Gather):
 *	1.分散读取(Scattering Reads):将Channel中的数据分散到多个缓冲区中
 *	2.聚集写入(Gathering Writes):将多个缓冲区中的数据聚集到Channel中
 *
 */
public class TestChannel {
	
	@Test
	/**
	 * 功能：测试channel的分散写入聚集读取
	 */
	public void testChannel_Scatter_Gather() {

		RandomAccessFile accessFile=null;
		RandomAccessFile accessFile2=null;
		//1.获取Channel
		FileChannel inchannel=null;
		FileChannel outchannel=null;
		try {
			accessFile = new RandomAccessFile("雪中悍刀行群像.rmvb", "rw");
			accessFile2 = new RandomAccessFile("copy22.rmvb","rw");
			inchannel = accessFile.getChannel();
			outchannel = accessFile2.getChannel();
			
			//2.获取(创建)缓冲区数组
			ByteBuffer buffer1=ByteBuffer.allocate(1024);
			ByteBuffer buffer2=ByteBuffer.allocate(2048);
			ByteBuffer []bufs={buffer1,buffer2};
			
			while (inchannel.read(bufs)!=-1) {//3.分散写入
				//4.flip()将写模式转换为读
				for (ByteBuffer byteBuffer : bufs) {
					byteBuffer.flip();
				}
				//5.聚集写入
				outchannel.write(bufs);
				//6.恢复初分配，以便循环
				for (ByteBuffer byteBuffer : bufs) {
					byteBuffer.clear()	;
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{	//7.关闭资源
			if(outchannel!=null){
				
				try {
					outchannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(inchannel!=null){
				try {
					inchannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(accessFile2!=null){
				try {
					accessFile2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(accessFile!=null){
				
				try {
					accessFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	
	
	@Test
	/**
	 * 功能：Channel中的数据传输+至二级缓冲区
	 */
	public void testChannel_Transfer() {

		FileChannel inchannel=null;
		FileChannel outchannel=null;
		try {
			inchannel = FileChannel.open(Paths.get("雪中悍刀行群像.rmvb"), StandardOpenOption.READ);
			outchannel = FileChannel.open(Paths.get("copy3.rmvb"), StandardOpenOption.WRITE,StandardOpenOption.CREATE	);
			
			inchannel.transferTo(0, inchannel.size(), outchannel);
			//或者	outchannel.transferFrom(inchannel,0,inchannel.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(inchannel!=null){
				try {
					outchannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(outchannel!=null){
				
				try {
					inchannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Test
	/**
	 * 功能：使用	直接缓冲区(get/put)+Channel(方法2	open())
	 */

	public void testCopy_Channel() throws Exception{
		//1.获取通道
		FileChannel inchannel=FileChannel.open(Paths.get("雪中悍刀行群像.rmvb"), StandardOpenOption.READ);
		FileChannel outchannel=FileChannel.open(Paths.get("copy12.rmvb"),StandardOpenOption.WRITE, StandardOpenOption.READ,StandardOpenOption.CREATE);
		
		//2.//获取缓冲区：内存映射文件(这个文件放在了物理内存上)：与allocateDirect意思是一样的，只是获取的方式不一样
			//ByteBuffer buffer=ByteBuffer.allocate();
			//while(inchannel.read(buffer)!=-1){	//channel写入buffer中
			//		outchannel.write(buffer);		//Buffer写入Channel
			//		buffer.clear();
			//	}
		MappedByteBuffer inMappedBuff=inchannel.map(MapMode.READ_ONLY,0,inchannel.size());//将Channel中的数据转换成Buffer，读取Buffer就是读取Channel中的数据【将Channel数据写入Buffer】
		MappedByteBuffer outMappedBuff=outchannel.map(MapMode.READ_WRITE,0,inchannel.size());//将Channel中的数据转换成Buffer；向Buffer里写，就是向Channel中写【将Buffer中的数据写入Channel】
		
		//3.直接对缓冲区进行读写操作
		byte []buffer=new byte[inMappedBuff.limit()];
		inMappedBuff.get(buffer);//将缓冲区中的数据获取到buffer中
		outMappedBuff.put(buffer);	//将buffer中的数据存入缓冲区
		
		//4.关闭Channel
		outchannel.close();
		inchannel.close();
	}
	
	@Test
	/**
	 * 功能：利用Channel(方法1 getChannel()	read/write)+非直接缓冲区实现文件的复制
	 * 	1.
	 */
	public void testChannelForCopy() {
		FileInputStream fileInputStream=null;
		FileOutputStream fileOutputStream=null;
		//1.获取Channel
		FileChannel inchannel=null;
		FileChannel outchannel=null;
		try {
			fileInputStream = new FileInputStream("雪中悍刀行群像.rmvb");
			fileOutputStream = new FileOutputStream("copy.rmvb");
			
			inchannel = fileInputStream.getChannel();
			outchannel = fileOutputStream.getChannel();
			
			//2.创建缓冲区：分配一个指定大小的缓冲区：
			ByteBuffer buffer=ByteBuffer.allocate(1024);
			
			//3.将Channel中的数据存入缓冲区
			while(inchannel.read(buffer)!=-1){
				//4.存入数据之后，切换为读的模式
				buffer.flip();
				
				//5.将缓冲区的数据写入Channel中
				outchannel.write(buffer);
				buffer.clear();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//6.关闭Channel
			if(outchannel!=null){
				try {
					outchannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(inchannel!=null){
				try {
					inchannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fileOutputStream!=null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}

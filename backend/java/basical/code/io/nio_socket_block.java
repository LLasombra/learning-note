package basical;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;


/**
 * 一、使用NIO完成网络通信的三个核心：
 *	1.Channel：负责连接
 *		1.1获取Channel：
 *			***针对一般的Java流对象(包括网络Socket通信Channel)：getChannel()
 *			***针对Channel提供了open方法 Channel。open（Paths.get(),options）
 *			***在JDK1.7中的NIO2的Files工具类的newByteChannel()
 *		
 *	2.数据的存取：
 *		2.1缓冲区：put/get
 *			***将Channel转换为Buffer;对Buffer进行操作：直接缓冲区：
 *				MappedByteBuffer inMappedBuff=inchannel.map(MapMode.READ_ONLY,0,inchannel.size());//将Channel中的数据转换成Buffer，读取Buffer就是读取Channel中的数据【将Channel数据写入Buffer】
				MappedByteBuffer outMappedBuff=outchannel.map(MapMode.READ_WRITE,0,inchannel.size());//将Channel中的数据转换成Buffer；向Buffer里写，就是向Channel中写【将Buffer中的数据写入Channel】
 *		2.2Channel：write/read
 *			***非直接缓冲区：inchannel.read(Buffer buffer)+outchannel.write(buffer)+while
 *			***Channel之间得数据传输：
 *				inchannel.transferTo(0, inchannel.size(), outchannel);/outchannel.transferFrom(inchannel,0,inchannel.size());
 *			***Channel的分散写入聚集读取：
 *				等价于费直接缓冲区+多个Buffer
 *
 *	3.选择器Selector：是SelectableChannel的多路复用器，用于监控SelectorableChannel的IO状况
 *			java.nio.channels.SelectableChannel接口：
 *			   监控的是(实现了接口)：SocketChannel
 *							  ServerSocketChannel 
						      DatagarmChannel
						
 *				
 *二、实现步揍(阻塞式的)：
 *	1.Client：
 *		1.1获取Channel：SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8989));
 *		1.2读取本地 文件，并发送给服务器：By CHannel+Buffer
 *		1.3关闭资源
 *	2.Server：
 *		2.1获取Channel：ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
 *		2.2绑定端口号：serverSocketChannel.bind(new InetSocketAddress(8989));
 *		2.3读取Client发送数据的Channel:SocketChannel socketChannel=serverSocketChannel.accept();
 *		2.4读取Channel中的数据，并写入本地文件：Buffer+Channel
 *		2.4关闭资源
 *
 */


public class NIOSocket_Block {

	@Test
	/**
	 * 功能：阻塞式的Client
	 */
	public void testClient() {
		//1.获取Channel，一创建就自动向服务器发送
		SocketChannel socketChannel=null;
		//3.读取本地文件，并发送给服务器
		FileChannel fileChannel=null;
		try {
			socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8989));
			
			//2.分配缓冲区：
			ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
			
			fileChannel = FileChannel.open(Paths.get("雪中悍刀行群像.rmvb"), StandardOpenOption.READ);
			
			while(fileChannel.read(byteBuffer)!=-1){
				byteBuffer.flip();
				socketChannel.write(byteBuffer);
				byteBuffer.clear();
			}
			
			
			//NOTICE：读取服务器端发回来的反馈
			socketChannel.shutdownOutput();//关闭上一层的操作；关闭的是，server向Channel中写
			int len=0;
			while ((len=socketChannel.read(byteBuffer))!=-1) {
				byteBuffer.flip();
				System.out.println(new String(byteBuffer.array(),0,len));
				byteBuffer.clear();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{//4.关闭资源 
			if(socketChannel!=null){
				
				try {
					socketChannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fileChannel!=null){
				try {
					fileChannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		
	}
	
	
	
	@Test
	/**
	 * 功能：阻塞式的Sever
	 */
	public void testServer(){
		//1.获取Channel
		ServerSocketChannel serverSocketChannel=null;
		//3.获取客户端的Channel
		SocketChannel socketChannel=null;
		//4.读写
		FileChannel fileChannel=null;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			//2.绑定连接的端口韩
			serverSocketChannel.bind(new InetSocketAddress(8989));
			socketChannel = serverSocketChannel.accept();
			socketChannel.shutdownOutput();//关闭上一层的操作
			fileChannel = FileChannel.open(Paths.get("Copy_NIO.rmvb"), StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
			ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
			while (socketChannel.read(byteBuffer)!=-1) {
				byteBuffer.flip();
				fileChannel.write(byteBuffer);
				byteBuffer.clear();
			}
			
			//NOtice:向客户端发送反馈
			socketChannel.shutdownInput();//关闭上一层的操作
			byteBuffer.put("服务器端接受数据成功！".getBytes());
			byteBuffer.flip();
			socketChannel.write(byteBuffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 3.关闭资源

			if (socketChannel != null) {

				try {
					socketChannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fileChannel != null) {
				try {
					fileChannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (serverSocketChannel != null) {
				try {
					serverSocketChannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}

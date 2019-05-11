package basical;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

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
 *二、实现步揍(非阻塞式的)：
 *	1.Client：
 *		1.1获取Channel：SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8989));
 *		1.2转换为非阻塞式：socketChannel.configureBlocking(false);
 *		1.2读取本地 文件，并发送给服务器：By CHannel+Buffer
 *		1.3关闭资源
 *	2.Server：
 *		2.1获取Channel：ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
 *		2.2转换为非阻塞式:serverSocketChannel.configureBlocking(false);
 *		2.3绑定端口号：serverSocketChannel.bind(new InetSocketAddress(8989));
 *		2.4获取选择器:Selector selector=Selector.open();
 *		2.5将选择器注册到Channel上,并且指定"监听接受事件":
 *					serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
 *		2.6轮询式获取选择器上已经就绪的事件:
 *					while(selector.select()>0){
 *		2.7获取当前选择器上注册的所有事件:
 *					Iterator<SelectionKey>iterator=selector.selectedKeys().iterator();
 *		2.8获取准备就绪的事件:
 *					SelectionKey selectionKey = (SelectionKey) iterator.next();
 *		2.9判断什么事件准备就绪:
 *					if(selectionKey.isAcceptable()){
 *		2.10接受Client的Channel：
 *					SocketChannel socketChannel=serverSocketChannel.accept();
 *		2.11将socket转换为非阻塞式
 *					socketChannel.configureBlocking(false);
 *		2.12将接受来的数据打印：所以要注册写事件
 *					socketChannel.register(selector, SelectionKey.OP_READ);
 *		2.13写就绪了,获取SocketChannel
 *					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
 *		2.14将接受来的数据打印：(len = socketChannel.read(byteBuffer))>0 必须是这样
 *					ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
 *					int len = 0;
 *					while ((len = socketChannel.read(byteBuffer))>0 ) {
 *						//没读到结尾返回0；督导结尾返回-1
 *		//NIO:The number of bytes read, possibly zero, or -1 if the channel has reached end-of-stream 
 *	 	//IO:the total number of bytes read into the buffer, or -1 if there is no more data because the end of the stream has been reached. 
 *						System.out.println(len);
 *						byteBuffer.flip();
 *						System.out.println(new String(byteBuffer.array(), 0,len));
 *						byteBuffer.clear();
 *	 				}
 *		2.15取消选择键SelectionKey
 *				iterator.remove();
 *		
 *
 */

public class NIOSocket_NONBlock {
	@Test
	/**
	 * 功能：非阻塞式的Client：就必须有Selector
	 */
	public void testClient() {
		
		SocketChannel socketChannel=null;
		Scanner scanner=null;
		try {
			//1.获取Channel，一创建就自动向服务器发送
			socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8989));
			//2.转换为非阻塞式：
			socketChannel.configureBlocking(false);
			//3.分配缓冲区：
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			
//			byteBuffer.put(new Date().toString().getBytes());
//			byteBuffer.flip();
//			//4.向Channel中写入数据
//			socketChannel.write(byteBuffer);
//			
//			
			scanner=new Scanner(System.in);
			while(scanner.hasNext()){
				String str=scanner.nextLine();
				
				// 有个问题，若一次的数据大于capacity，会出错的
				byteBuffer.put((new Date().toString() + "\n" + str).getBytes());

				byteBuffer.flip();
				socketChannel.write(byteBuffer);
				byteBuffer.clear();
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{//4.关闭资源 
			if(scanner!=null){
				scanner.close();
			}
			if(socketChannel!=null){
				try {
					socketChannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}
		
		
	}
	
	
	
	@Test
	/**
	 * 功能：非阻塞式的Sever
	 */
	public void testServer() throws IOException{
		//1.获取ServerSocket的Channel
		ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
		//2.转换为非阻塞式
		serverSocketChannel.configureBlocking(false);
		//3.绑定连接：
		serverSocketChannel.bind(new InetSocketAddress(8989));
		//4.获取选择器
		Selector selector=Selector.open();
		//5.将选择器注册到Channel上,并且指定"监听接受事件"
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		//6.轮询式获取选择器上已经就绪的事件
		while(selector.select()>0){
			//7.获取当前选择器上注册的所有事件
			Iterator<SelectionKey>iterator=selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				//8.获取准备就绪的事件
				SelectionKey selectionKey = (SelectionKey) iterator.next();
				//9.判断什么事件准备就绪.
				if(selectionKey.isAcceptable()){
					//9.1接受Client的Channel
					SocketChannel socketChannel=serverSocketChannel.accept();
					//10.将socket转换为非阻塞式
					socketChannel.configureBlocking(false);
					//11.将接受来的数据打印：所以要注册写事件
					socketChannel.register(selector, SelectionKey.OP_READ);
				} else if (selectionKey.isReadable()) {
					// 12.写就绪了,获取SocketChannel
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					// 13.将接受来的数据打印
					ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
					int len = 0;
					//第一次就读到了结尾，若返回0的话，!=-1;则会不停的循环；因此要>0
					while ((len = socketChannel.read(byteBuffer))>0 ) {
						//读到结尾返回可能0或-1
						//NIO:The number of bytes read, possibly zero, or -1 if the channel has reached end-of-stream 
						//IO:the total number of bytes read into the buffer, or -1 if there is no more data because the end of the stream has been reached. 
						System.out.println(len);
						byteBuffer.flip();
						System.out.println(new String(byteBuffer.array(), 0,len));
						byteBuffer.clear();
					}
				}
				//14.取消选择键SelectionKey
				iterator.remove();
			}
		}
	}
}

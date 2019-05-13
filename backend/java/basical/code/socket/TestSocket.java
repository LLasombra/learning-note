package basical;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * 1.IP和端口号的具体意思：
 * 	1.1IP定位网络中的一台主机
 * 	1.2端口号定位主机的一个网络程序
 * 
 * 2.InetAddress：对象表示网络中的一个地址
 * 	2.1InetAddress address=InetAddress.getByName("127.0.01");
 * 	
 * 3.TCP/IP编程：
 * 	3.1服务器/客户端：客户端发送请求到服务器，服务器接受请求，给予响应到客户端
 * 	3.2ServerSocket
 */

/**
 * 
 * Socket1测试的是打印字符串
 * Socket2测试的是文件：带缓冲流
 * Socket2测试的是图片：不带缓冲流
 *
 */
public class TestSocket {

	
	
	@Test
	/**
	 * 客户端的Socket
	 */
	public void testClientSocket3() throws Exception{
		//1.创建Socket对象：ip+port;同时会自动向服务器端发出请求
		InetAddress address=InetAddress.getByName("127.0.0.1");
		Socket socket=new Socket(address,8989);
		
		//2.相关操作:读取输出流
		//2.1获取输入流：ServerSocket传过来的
		InputStream inputStream=socket.getInputStream();
		//2.3创建输出流：输出到本地硬盘(带有缓冲)
		OutputStream outputStream=new FileOutputStream("123.png");
		//2.4写入
		int len= 0;
		byte[]buffer=new byte[1024];
		while ((len = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer,0,len);
		}
		//2.5关闭流资源
		inputStream.close();
		outputStream.close();
		
		//3.关闭Socket
		socket.close();
	}
	
	
	@Test
	/**
	 * 服务器端的Socket
	 */
	public void testServerSockeet3() throws IOException{
		//1.创建ServerSocket:
		ServerSocket serverSocket=new ServerSocket(8989);
		//2.接受客户端的请求，并得到Socket套接字：ip+port
		Socket socket=serverSocket.accept();
		
		//3.相关操作：将要输出的对象弄进输出流；(曾本地读取的话就需要输入流)涉及到流资源，一般先是字节流获取，然后字符流处理
		//3.1获取输出流：ServerSocket要输出的
		OutputStream outputStream=socket.getOutputStream();
		//3.3创建要输入流(要输出的)，并转换为带有缓冲的字符流
		InputStream inputStream=new FileInputStream("abc.png");
		//3.4.读写
		int len= 0;
		byte[]buffer=new byte[1024];
		while ((len = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer,0,len);
		}
		//3.5关闭流资源
		inputStream.close();
		outputStream.close();
		//4.关闭Socket资源
		socket.close();
		serverSocket.close();
	}
	
	
	
	@Test
	/**
	 * 客户端的Socket
	 */
	public void testClientSocket2() throws Exception{
		//1.创建Socket对象：ip+port;同时会自动向服务器端发出请求
		InetAddress address=InetAddress.getByName("127.0.0.1");
		Socket socket=new Socket(address,8989);
		
		//2.相关操作:读取输出流
		//2.1获取输入流：ServerSocket传过来的
		InputStream inputStream=socket.getInputStream();
		//2.2将传过来的流转换为带缓冲的字符流
		BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
		
		//2.3创建输出流：输出到本地硬盘(带有缓冲)
		OutputStream OutputStream=new FileOutputStream("123.png");
		BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(OutputStream);
		
		//2.4写入
		int len=0;
		byte []buffer=new byte[1024];
		while((len=bufferedInputStream.read(buffer))!=-1){
			bufferedOutputStream.write(buffer,0,len);
		}
		//2.5关闭流资源
		bufferedOutputStream.close();
		bufferedInputStream.close();
		
		//3.关闭Socket
		socket.close();
	}
	
	
	@Test
	/**
	 * 服务器端的Socket
	 */
	public void testServerSockeet2() throws IOException{
		//1.创建ServerSocket:
		ServerSocket serverSocket=new ServerSocket(8989);
		//2.接受客户端的请求，并得到Socket套接字：ip+port
		Socket socket=serverSocket.accept();
		
		//3.相关操作：将要输出的对象弄进输出流；(曾本地读取的话就需要输入流)涉及到流资源，一般先是字节流获取，然后字符流处理
		//3.1获取输出流：ServerSocket要输出的
		OutputStream outputStream=socket.getOutputStream();
		//3.2将输出流转换为带有缓冲的字符流
		BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(outputStream);
		//3.3创建要输入流(要输出的)，并转换为带有缓冲的字符流
		InputStream inputStream=new FileInputStream("abc.png");
		BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
		//3.4.读写
		int len=0;
		byte []buffer=new byte[1024];
		while ((len = bufferedInputStream.read(buffer)) != -1) {
			bufferedOutputStream.write(buffer,0,len);
		}
		//3.5关闭流资源
		bufferedInputStream.close();
		bufferedOutputStream.close();
		//4.关闭Socket资源
		socket.close();
		serverSocket.close();
	}
	
	
	
	@Test
	/**
	 * 客户端的Socket
	 */
	public void testClientSocket() throws Exception{
		//1.创建Socket对象：ip+port;同时会自动向服务器端发出请求
		InetAddress address=InetAddress.getByName("127.0.0.1");
		Socket socket=new Socket(address,8989);
		
		//2.相关操作
		InputStream inputStream=socket.getInputStream();
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
		System.out.println("^-^:"+bufferedReader.readLine());
		inputStream.close();
		bufferedReader.close();
		//3.关闭Socket
		socket.close();
	}
	
	
	@Test
	/**
	 * 服务器端的Socket
	 */
	public void testServerSockeet() throws IOException{
		//1.创建ServerSocket:
		ServerSocket serverSocket=new ServerSocket(8989);
		//2.接受客户端的请求，并得到Socket套接字：ip+port
		Socket socket=serverSocket.accept();
		
		//3.相关操作：socket读取输出流，并打印：涉及到流资源，一般先是字节流获取，然后字符流处理
		OutputStream outputStream=socket.getOutputStream();
		PrintWriter printWriter=new PrintWriter(outputStream);
		printWriter.write("来自服务器的问候...");
		
		printWriter.close();
		outputStream.close();
		
		//4.关闭Socket资源
		socket.close();
		serverSocket.close();
	}
	
	
	@Test
	/**
	 * 功能：测试InetAddress类：
	 * 	1.获取IP地址的信息
	 */
	public void testInetAddress() throws Exception {

			InetAddress address=InetAddress.getLocalHost();
			System.out.println(address);
			
			InetAddress address1=InetAddress.getByName("www.baidu.com");
			System.out.println(address1);	//www.baidu.com/119.75.213.61
	}
}

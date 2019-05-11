package basical;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

import org.junit.Test;

/**
 * 功能：测试IO流：
 *	1.基本知识：中文乱码
 *	2.文件复制 
 *
 */
public class _InputStream {

	@SuppressWarnings("unused")
	@Test
	/**
	 * 功能：测试InputStream：
	 * 	1.FileInputStream读取中文会出现中文乱码：使用Reader解决
	 * 	2.2种方式：
	 * 		逐个字节读取;
	 * 		一次读取一组数据;
	 * @throws Exception
	 */
	public void testInputStream() throws Exception{
		//1.创建输入流对象:读取中文乱码则用Reader
		InputStream inputStream=new FileInputStream("hello.txt");
		/**
		 * 说明：这里解决中文乱码则用Reader：
		 * 	2.这里若是不注释掉，下面语句不能执行：流资源已经被占用
		 *
		 * Reader reader=new InputStreamReader(inputStream,"UTF-8");
		 * //2.读取
		 * //2.1逐字节的读取;返回值为int型;读取结束返回-1.
		 * int result=reader.read();
		 * while(result!=-1){
		 * 		//System.out.print((char)result);
		 * 		//result=reader.read();
		 * }
		 */
		//2.2一次读取一组数据;存在最后一次读取问题:也是中文乱码
		
		//优化：2.2.2
		byte[] b=new byte[10];
		int len=0;//每次实际读取的长度，主要是为最后一次准备的
		while ((len=inputStream.read(b))!=-1) {
			for(byte a:b){
				System.out.print((char)a);
			}
		}
		
		//未优化 2.2.1
		/*byte[] b=new byte[10];
		int result2=inputStream.read(b);
		int len=0;//每次实际读取的长度，主要是为最后一次准备的
		while ((len=result2)!=-1) {
			for(byte a:b){
				System.out.print(a);
			}
			result2=inputStream.read(b);
		}*/
		
		
		
		//3.关闭流资源
		inputStream.close();
	}

	
	@Test
	/**
	 *功能：测试OutputStream抽象类：
	 *	1.写入String字符串：outputStream.write(content.getBytes());
	 *	2.逐字节的写入:乱码了解决
	 */
	public void testOutputStream() throws Exception{
		//1.创建输出流对象：
		 OutputStream outputStream=new FileOutputStream("whello.txt");
		 Writer writer=new OutputStreamWriter(outputStream);
		String content="张壮壮那几次吨你 擦拭 的就看DJ打开！";
		//2.向文健中写入：
		//2.1写入String字符串
//		outputStream.write(content.getBytes());
		//2.2逐字节的写入:乱码了
		char[]cb=content.toCharArray();
		for(int i=0;i<cb.length;i++){
//			System.out.println((int)cb[i]);
			writer.write(cb[i]);
		}
		/*//2.3使用byte数组进行读取
		byte[] b = new byte[10];
		int len=0;
		while((len=in.read(b))!=-1){
			//3.写入
			out.write(b, 0, len);
		}*/

		
		//3.关闭流资源
		writer.close();
		outputStream.close();
	}


	@Test
	/**
	 * 测试InputStream
	 * @throws IOException
	 */
	public void testInputStream2() throws IOException {
		// TODO Auto-generated method stub
		//1.创建一个字节输入流
		InputStream in=new FileInputStream("F:\\Java\\Algorithms\\algs4-data\\shellsST.txt");
		
		//2.读取文件内容
		//2.1一个字节一个字节的读取 ：read()且读到结尾处返回-1.但是不建议使用，效率太低
		int result=in.read();
		while(result!=-1){
			System.out.print((char)result);
			result=in.read();
		}
		
		//2.2一次读取一定数组长度的数据
		byte []b=new byte [10];
		int len =0;
		while((len=in.read(b))!=-1){
			for(int i=0;i<len;i++){
				System.out.print((char)b[i]);
			}
		}
		
		//2.3把内容读取到字节数组的部分连续的元素中
		byte []b2 = new byte[1024*10];					//把元素督导数组的指定位置
		int end=in.read(b2,10,in.available());//从10开始放，一直放到了38的位置结束
		System.out.println(end);
		
		//Notify
		String str="sjihdggvycbhxjkm";
		byte[] str2=str.getBytes();
		System.err.println(String.valueOf(str2));
		System.err.println(new String(str2));
		//3.关闭流资源
		in.close();
	}

	
	@Test
	/**
	 * 文件的复制：字节流文件复制 (可以处理所有类型文件)
	 * 	1.while((len=in.read(b))!=-1){ 
	 * 		//3.写入 
	 * 		out.write(b, 0, len);
	 * 		}
	 * @throws IOException
	 */
	public void testCpoyFile_ByInANDOutputStream() throws IOException {
		//1.创建文件的输入输出流
		InputStream in=new FileInputStream("F:\\Java\\Algorithms\\algs4-data\\shellsST.txt");
		OutputStream out=new FileOutputStream("copy.txt");
		
		//2.使用byte数组进行读取
		byte[] b = new byte[10];
		int len=0;
		while((len=in.read(b))!=-1){
			//3.写入
			out.write(b, 0, len);
		}
		
		//4.关闭流资源
		out.close();
		in.close();
	}
	
	
	@Test
	/**
	 * 文件的复制：只能处理文本文件：reader =new FileReader(url)
	 * @throws IOException 
	 * 
	 */
	public void testCopy_ByBufferedReaderANDWriter() throws IOException {
		//1.创建BufferedReader和BufferedWriter
		Reader in =new FileReader("F:\\Java\\Algorithms\\algs4-data\\shellsST.txt");
		BufferedReader bufferedReader=new BufferedReader(in);
		
		Writer out=new FileWriter("copy2.txt");
		BufferedWriter bufferedWriter=new BufferedWriter(out)	;
		
		//2.读写
		String str=null;
		while((str=bufferedReader.readLine())!=null){
			bufferedWriter.write(str);
			
		}
		//3.关闭流资源
		bufferedWriter.close();
		out.close();
		bufferedReader.close();
		in.close();
	}
	
	
	@Test
	/**
	 * 文件的复制，所有文件都可以
	 * @throws IOException 
	 */
	public void testCopy_ByAll() throws IOException {
		//1.获取文件的字节流
		InputStream in=new FileInputStream("F:\\Java\\Algorithms\\algs4-data\\#a.txt");
		OutputStream out=new FileOutputStream("copy_K.txt");
		//2.转换为带有缓存的字符流
		BufferedInputStream bufferedReader=new BufferedInputStream(in);
		BufferedOutputStream bufferedWriter=new BufferedOutputStream(out);
		//3.读写,使用数组
		int len=0;
		int count=0;
		byte []buffer=new byte[10];
		while((len=bufferedReader.read(buffer))!=-1){
			count=count+1;
			bufferedWriter.write(buffer,0,len);
		}
		count++;
		System.out.println(count);
		//4.关闭流资源
		bufferedWriter.close();
		bufferedReader.close();
		System.out.println("sdjuhyfg");
	}
	
	
	@Test
	/**
	 * 测试RandomAccessFile对象
	 * @throws IOException
	 */
	public void testRandomAccessFile() throws IOException {
		
		//1.创建RandomAccessFile对象
		RandomAccessFile accessFile=new RandomAccessFile("copy.txt", "rw");
/*		//这段代码要注释掉，后面的才可以运行
  		//2.读写文件
		String str=null;
		while ((str=accessFile.readLine())!=null) {
			System.out.print(str);
		}
		
		//3.项文件尾部写入"jashbdg"
		accessFile.writeChars("abcdefg");//不好 a b c d e f 
		accessFile.writeBytes("abcdefg");//OK
		accessFile.seek(accessFile.length());
		//向指定的位置写入字符串：会把原来的替换
		//accessFile.seek(20);
		
		accessFile.writeBytes("sugdyf");
		//3.1获取当前指针所在的位置
		long location=accessFile.getFilePointer();
		System.out.println(location);
*/
		
		
		//4.在原文件中的第二行插入一句话"456132":
			//4.1读取第一行：
			String line=accessFile.readLine();
			//4.2将剩下的文件复制
			byte []buffer=new byte[(int) (accessFile.length()-line.length())];
			accessFile.read(buffer);
			System.out.println("----------------------");
			//4.3将指针移回第一行结束为止
			accessFile.seek(line.length());
			//4.4接着第一行后面写入：会覆盖之后的内容
			accessFile.writeBytes("\n45613\n");
			accessFile.write(buffer);
		
		//4.关闭RandomAccessFile对象
		accessFile.close();
	}

	
	
	@Test
	/**
	 * 功能：测试对象序列化：使用OutputStream写入本地文件;对象反序列化：使用InputStream读取
	 * 	1.作用：对象序列化的目标是将对象保存到磁盘上，或允许在网络中直接传输对象。
	 * 	2.序列化机制是 JavaEE 平台的基础
	 * 	3.实现可序列化：两个接口之一
	 * 		Serializable
	 * 		Externalizable
	 * 	4.序列化文件本身就是乱码
	 * 	5.小结：
	 * 		对象本身实现Serializable接口则这个对象是序列化的。
	 * 		将可序列化的对象写入本地文件，使用ObjectOutputStream；(这里的文件本身就是经过编码的)
	 * 		将在本地的序列化对象读取，使用ObjectInputStream。
	 */
	public void testSerializable() throws Exception{
		//1.创建可序列化对象
		Person person=new Person("张壮壮",12);
		
		//2.使用ObjectOutputStream将对象写入本地文件(硬盘上)
		OutputStream out=new FileOutputStream("序列化对象.txt");
		ObjectOutputStream objectOutputStream=new ObjectOutputStream(out);
		objectOutputStream.writeObject(person);
		
		//3.关闭对象流资源
		objectOutputStream.close();
	}

	
	@Test
	/**
	 * 功能：测试对象反序列化：InputStream
	 * 	
	 */
	public void testNoNSerializable() throws Exception{
		//1.使用ObjectOutputStream将对象从本地文件(硬盘上)读取
		InputStream inputStream=new FileInputStream("序列化对象.txt");
		ObjectInputStream inputStream2=new ObjectInputStream(inputStream);
		
		Person person=(Person) inputStream2.readObject();
		System.out.println(person);
		inputStream2.close();
		inputStream.close();
	}
}












/**
 * 功能：这个类时为测试Serializable准备的
 *
 */
class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

}
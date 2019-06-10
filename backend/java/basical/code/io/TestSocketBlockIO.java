
import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题3：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。
 * 并关闭相应的连接。
 * @create 2019 下午 4:13
 */
public class TestSocketBlockIO {

    private static final String TEST_DATA_PATH = "resource/test-data.md";
    private static final String TEST_DATA_PATH_NEW = "resource/test-data-new.md";

    @Test
    public void client() throws IOException {
        //1. 创建客户端的 Socket
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);
        //2. 创建输出流, 向服务器端输出数据
        OutputStream os = socket.getOutputStream();
        //3. 读取本地文件, 并写入面向服务器的输出流中
        FileInputStream fis = new FileInputStream(new File(TEST_DATA_PATH));
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        //4. 关闭数据的输出
        socket.shutdownOutput();

        //5. 接收来自于服务器端的数据，并显示到控制台上
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bufferr = new byte[20];
        int len1;
        while((len1 = is.read(buffer)) != -1){
            baos.write(buffer,0,len1);
        }
        System.out.println(baos.toString());

        //6. 关闭资源
        fis.close();
        os.close();
        socket.close();
        baos.close();
    }

    /*
    这里涉及到的异常，应该使用try-catch-finally处理
     */
    @Test
    public void server() throws IOException {
        //1. 创建服务器端的 Socket
        ServerSocket ss = new ServerSocket(9090);
        //2. 接受客户端的 Socket
        Socket socket = ss.accept();
        //3. 接受客户端的数据输入流
        InputStream is = socket.getInputStream();
        //4. 将接受到的客户端数据写到本地文件
        FileOutputStream fos = new FileOutputStream(new File(TEST_DATA_PATH_NEW));
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }

        System.out.println("图片传输完成");

        //5.服务器端给予客户端反馈
        OutputStream os = socket.getOutputStream();
        os.write("你好，美女，照片我已收到，非常漂亮！".getBytes());

        //6. 关闭资源
        fos.close();
        is.close();
        socket.close();
        ss.close();
        os.close();
    }
}

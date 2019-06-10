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
 * @author zack
 * @create 2019-06-10 14:05
 * @function: 测试IO流：
 * 1.基本知识：中文乱码
 * 2.文件复制
 */
public class TestIO {
    private static final String TEST_DATA_PATH = "resource/test-data.md";
    private static final String TEST_DATA_PATH_NEW = "resource/test-data-new.md";

    @Test
    /**
     * @function: 测试 InputStream：
     *   1. FileInputStream 读取中文会出现中文乱码：使用 Reader 解决
     *      Reader reader=new InputStreamReader(inputStream,"UTF-8");
     *   2. 2种方式：
     *     逐个字节读取: .read()
     *     一次读取一组数据: .read(b)
     * @throws Exception
     */ public void testInputStream() throws Exception {
        // 1. 创建输入流对象: 读取中文乱码则用Reader
        InputStream inputStream = new FileInputStream(TEST_DATA_PATH);

        // 2. 读取 inputStream 的指针移动了, 三份代码不能一起出现
        //2.1一个字节一个字节的读取 ：read()且读到结尾处返回-1.但是不建议使用，效率太低
        int result = inputStream.read();
        while (result != -1) {
            System.out.print((char) result);
            result = inputStream.read();
        }
        //2.2一次读取一定数组长度的数据
        byte[] b = new byte[10];
        int len = 0;
        while ((len = inputStream.read(b)) != -1) {
            for (byte a : b) {
                System.out.print((char) a);
            }
        }
        // 2.3把内容读取到字节数组的部分连续的元素中
        byte[] b2 = new byte[1024 * 10];  //把元素督导数组的指定位置
        int end = inputStream.read(b2, 10, inputStream.available()); //从10开始放，一直放到了38的位置结束
        System.out.println(end);

        //3.关闭流资源
        inputStream.close();

        // TEST String
        String str = "sjihdggvycbhxjkm";
        byte[] str2 = str.getBytes();
        System.err.println(String.valueOf(str2)); // ClassName
        System.err.println(new String(str2)); // StringValue
    }


    @Test
    /**
     *功能：测试 OutputStream 抽象类：
     *  1.写入String字符串：
     *      outputStream.write(content.getBytes());
     *  2.逐字节的写入: 乱码了解决
     */ public void testOutputStream() throws Exception {
        // 1. 创建输出流对象：
        OutputStream outputStream = new FileOutputStream(TEST_DATA_PATH);
        Writer writer = new OutputStreamWriter(outputStream);
        String content = "张壮壮那几次吨你 擦拭 的就看DJ打开！";

        // 2. 向文件中写入：
        char[] cb = content.toCharArray();
        for (int i = 0; i < cb.length; i++) {
            writer.write(cb[i]);
        }

        // 3.关闭流资源
        writer.close();
        outputStream.close();
    }


    @Test
    /**
     * 文件的字节复制：字节流文件复制 (可以处理所有类型文件)
     *  while((len=in.read(b))!=-1){
     *      out.write(b, 0, len);
     *  }
     * @throws IOException
     */ public void testCopyWithInputStreamAndOutputStream() throws IOException {
        // 1.创建文件的输入输出流
        InputStream in = new FileInputStream(TEST_DATA_PATH);
        OutputStream out = new FileOutputStream(TEST_DATA_PATH_NEW);

        // 2.使用byte数组进行读取
        byte[] b = new byte[10];
        int len = 0;
        while ((len = in.read(b)) != -1) {
            // 3.写入
            out.write(b, 0, len);
        }

        // 4.关闭流资源
        out.close();
        in.close();
    }


    @Test
    /**
     * 文件的字符复制：只能处理文本文件：reader =new FileReader(url)
     * @throws IOException
     */ public void testCopyWithBufferedReaderAndBufferedWriter() throws IOException {
        // 1.创建 BufferedReader 和 BufferedWriter
        Reader in = new FileReader(TEST_DATA_PATH);
        BufferedReader bufferedReader = new BufferedReader(in);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(TEST_DATA_PATH_NEW));

        // 2.读写
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            bufferedWriter.write(str);
        }
        // 3.关闭流资源
        bufferedWriter.close();
        bufferedReader.close();
        in.close();
    }


    @Test
    /**
     * 文件的字节复制，所有文件都可以
     * @throws IOException
     */ public void testCopyWithBufferedInputStreamAndBufferedOutputStream() throws IOException {
        // 1.获取文件的字节流
        InputStream in = new FileInputStream(TEST_DATA_PATH);
        OutputStream out = new FileOutputStream(TEST_DATA_PATH_NEW);
        // 2.转换为带有缓存的字符流
        BufferedInputStream bufferedReader = new BufferedInputStream(in);
        BufferedOutputStream bufferedWriter = new BufferedOutputStream(out);
        // 3.读写,使用数组
        int len = 0;
        int count = 0;
        byte[] buffer = new byte[10];
        while ((len = bufferedReader.read(buffer)) != -1) {
            bufferedWriter.write(buffer, 0, len);
        }
        // 4.关闭流资源
        bufferedWriter.close();
        bufferedReader.close();
    }


    @Test
    /**
     * 测试RandomAccessFile对象
     * @throws IOException
     */ public void testRandomAccessFile() throws IOException {
        // 1.创建RandomAccessFile对象
        RandomAccessFile accessFile = new RandomAccessFile(TEST_DATA_PATH, "rw");
        //这段代码要注释掉，后面的才可以运行
        //        // 2.读写文件
        //        String str = null;
        //        while ((str = accessFile.readLine()) != null) {
        //            System.out.print(str);
        //        }
        //        // 3.项文件尾部写入"jashbdg"
        //        accessFile.writeChars("abcdefg"); //不好 a b c d e f
        //        accessFile.writeBytes("abcdefg"); //OK
        //        accessFile.seek(accessFile.length());
        //        //向指定的位置写入字符串：会把原来的替换
        //        accessFile.seek(20);
        //
        //        accessFile.writeBytes("sugdyf");
        //        // 3.1获取当前指针所在的位置
        //        long location=accessFile.getFilePointer();
        //        System.out.println(location);

        // -------
        //4.在原文件中的第二行插入一句话"456132":
        //4.1读取第一行：
        String line = accessFile.readLine();
        //4.2将剩下的文件复制
        byte[] buffer = new byte[(int) (accessFile.length() - line.length())];
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
}
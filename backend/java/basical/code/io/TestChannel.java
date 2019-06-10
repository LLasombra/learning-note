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
 * @author zack
 * @create 2019-06-10 16:22
 * @function
 */
public class TestChannel {
    private static final String TEST_DATA_PATH = "resource/test-data.md";
    private static final String TEST_DATA_PATH_NEW = "resource/test-data-new.md";

    /**
     * 功能：测试 channel 的分散写入、聚集读取
     */
    @Test
    public void testCopyWithChannelAndRandomAccessFile() throws IOException {

        RandomAccessFile inputAccessFile = null;
        RandomAccessFile outAccessFile = null;
        //1.获取Channel
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inputAccessFile = new RandomAccessFile(TEST_DATA_PATH, "rw");
            outAccessFile = new RandomAccessFile(TEST_DATA_PATH_NEW, "rw");
            inChannel = inputAccessFile.getChannel();
            outChannel = outAccessFile.getChannel();

            //2.获取(创建)缓冲区数组
            ByteBuffer buffer1 = ByteBuffer.allocate(1024);
            ByteBuffer buffer2 = ByteBuffer.allocate(2048);
            ByteBuffer[] bufs = {buffer1, buffer2};

            while (inChannel.read(bufs) != -1) {//3.分散写入
                //4.flip()将写模式转换为读
                for (ByteBuffer byteBuffer : bufs) {
                    byteBuffer.flip();
                }
                //5.聚集写入
                outChannel.write(bufs);
                //6.恢复初分配，以便循环
                for (ByteBuffer byteBuffer : bufs) {
                    byteBuffer.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //7.关闭资源
            outChannel.close();
            inChannel.close();
            outAccessFile.close();
            inputAccessFile.close();
        }
    }

    /**
     * 功能：利用Channel(方法1 getChannel() read/write) + 非直接缓冲区实现文件的复制
     */
    @Test
    public void testCopyWithChannelAndBuffer() throws IOException {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get(TEST_DATA_PATH), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get(TEST_DATA_PATH_NEW), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            //2.获取(创建)缓冲区数组
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) != -1) {//3.分散写入
                //4.flip()将写模式转换为读
                buffer.flip();
            }
            //5.聚集写入
            outChannel.write(buffer);
            //6.恢复初分配，以便循环
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //7.关闭资源
            outChannel.close();
            inChannel.close();
        }
    }

    /**
     * 功能：Channel中的数据传输 + 至二级缓冲区
     */
    @Test
    public void testChannelTransfer() throws IOException {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get(TEST_DATA_PATH), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get(TEST_DATA_PATH_NEW), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            inChannel.transferTo(0, inChannel.size(), outChannel);
            //或者 outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outChannel.close();
            inChannel.close();
        }
    }

    /**
     * 功能：利用Channel(方法1 open()) + 非直接缓冲区实现文件的复制
     */
    @Test
    public void testCopyWithChannel() throws Exception {
        //1.获取通道
        FileChannel inChannel = FileChannel.open(Paths.get(TEST_DATA_PATH), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(TEST_DATA_PATH_NEW), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //2. //获取缓冲区：内存映射文件(这个文件放在了物理内存上)：与 allocateDirect 意思是一样的，只是获取的方式不一样
        //        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        //        while(inChannel.read(buffer) != -1){ // Channel 写入 buffer 中
        //            buffer.flip();
        //            outChannel.write(buffer); //Buffer 写入 Channel
        //            buffer.clear();
        //        }
        MappedByteBuffer inMappedBuff = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());//将Channel中的数据转换成Buffer，读取Buffer就是读取Channel中的数据【将Channel数据写入Buffer】
        MappedByteBuffer outMappedBuff = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());//将Channel中的数据转换成Buffer；向Buffer里写，就是向Channel中写【将Buffer中的数据写入Channel】

        //3.直接对缓冲区进行读写操作
        byte[] buffer = new byte[inMappedBuff.limit()];
        inMappedBuff.get(buffer);//将缓冲区中的数据获取到buffer中
        outMappedBuff.put(buffer);    //将buffer中的数据存入缓冲区

        //4.关闭Channel
        outChannel.close();
        inChannel.close();
    }
}


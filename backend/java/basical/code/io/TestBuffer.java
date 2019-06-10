import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * @author zack
 * @create 2019-06-10 15:57
 * @function
 */

public class TestBuffer {

    /**
     * 1.非直接缓冲区: allocate() 在JVM的内存中
     * 2.直接缓冲区: allocateDirect() 系统的物理内存中
     * 2.1 缺点: 资源占用量大，而且数据进去之后，所有的操作权都归了OS
     * 2.2 直接缓冲区是去掉了 copy 的步骤
     */
    @Test
    public void testAllocateDirect() {
        // 1.在系统的物理内存上直接分配空间
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer);
        System.out.println(buffer.isDirect());
    }

    @Test
    /**
     * 功能: 测试 Buffer 的 mark()方法: mark()之后，可以通过 reset()恢复到 mark 时的 position
     */ public void testMark() {
        String str = "abcdefg";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        buffer.flip(); // 从写变为了读
        byte[] b = new byte[buffer.limit()];
        buffer.get(b, 0, 2);//将其读入到b中
        System.out.println(buffer.position());//2
        buffer.mark();
        buffer.get(b, 2, 2);//继续想b中存放，之前的不会覆盖
        System.out.println(buffer.position());//4
        buffer.reset();
        System.out.println(buffer.position());//2

        System.out.println(new String(b, 0, b.length)); //abcd
    }


    /**
     * 功能:
     * 1.测试Buffer中的capacity()/limit()/position()
     * 2.缓冲区的操作:
     * 1. put():存数据到缓冲区
     * 2. get():获取缓冲区的数据
     * 3. flip():模式的转换:从写变为了读
     * 4. rewind():表示可以重复读取数据
     * 5. allocate():分配Buffer大小
     * 6. clear():清空缓冲区，回到刚分配是的状态，但是缓冲区的数据依旧存在，但是处于遗忘状态
     * 7. hasRemaining():判断缓冲区中是否还有剩余的数据
     * 8. remaining():有的话，还有几个
     */
    @Test
    public void testByteBuffer() {
        //1.创建一个指定大小的缓冲区:
        System.out.println("----------allocate--------------");
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //2.相关的属性
        System.out.println(buffer.capacity());        //1024
        System.out.println(buffer.limit());            //1024
        System.out.println(buffer.position());        //0

        //3.利用put()方法存数据
        System.out.println("----------put--------------");
        String str = "123456";
        buffer.put(str.getBytes());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());            //1024
        System.out.println(buffer.position());            //6,下一个要操作的位置

        //4.从写的模式转换到读取的模式
        System.out.println("----------flip--------------");
        buffer.flip();
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());            //6
        System.out.println(buffer.position());    // 6

        //5.利用get()方法取数据: 
        System.out.println("----------get--------------");
        byte[] b = new byte[buffer.limit() - 1];
        ByteBuffer buffer2 = buffer.get(b);
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());
        System.out.println(buffer2);
        //输出读取到的内容: 
        System.out.println(new String(b, 0, b.length));

        //6.hasRemaining()
        System.out.println("----------hasRemaining--------------");
        if (buffer.hasRemaining()) {
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
        System.out.println((char) buffer.get(0));            // 遗忘状态的 1
    }
}

## IO

### IO

#### IO 流体系

- 图表

|    分类    |      字节输入流      |            字节输出流 |    字符输入流     |     字符输出流     |
| :--------: | :------------------: | --------------------: | :---------------: | :----------------: |
|  抽象基类  |     InputStream      |          OutputStream |      Reader       |       Writer       |
|  访问文件  |   FileInputStream    |      FileOutputStream |    FileReader     |     FileWriter     |
|  访问数组  | ByteArrayInputStream | ByteArrayOutputStream |    CharReader     |     CharWriter     |
|  访问管道  |   PipedInputStream   |     PipedOutputStream |    PipedReader    |    PipedWriter     |
| 访问字符串 |        ------        |                 ----- |    FileReader     |     FileWriter     |
|   缓冲流   | BufferedInputStream  |  BufferedOutputStream |  BufferedReader   |   BufferedWriter   |
|   转换流   |        ------        |                 ----- | InputStreamReader | OutputStreamWriter |
|   对象流   |  ObjectIntputStream  |    ObjectOutputStream |       -----       |       -----        |
|  抽象基类  |  FilterInputStream   |    FilterOutputStream |   FilterReader    |    FilterWriter    |
|   打印流   |        ------        |           PrintStream |       -----       |    PrintWriter     |
| 推回输入流 | PushbackInputStream  |                 ----- |  PushbackReader   |       -----        |
|   特殊流   |   DataInputStream    |      DataOutputStream |       -----       |       -----        |

- 解释
  > InputStream
  ```java
  int read()
  int read(byte[] b)
  int read(byte[] b, int off, int len)
  ```
  > Reader
  ```java
  int read()
  int read(char [] c)
  int read(char [] c, int off, int len)
  ```
  > OutputStream
  ```java
  void write(byte write/int c)
  void []/char[] buff)
  void write(byte[]/char[] buff, int off, int len);
  ```
  > Writer
  ```java
  void write(String str);
  void write(String str, int off, int len)
  ```

#### RandomAccessFile

- RandomAccessFile 类既可以读取文件内容, 也可以向文件输出数据[随机访问文件的任何位置]
- 移动记录指针
  ```java
  long getFilePointer(): 获取文件记录指针的当前位置
  void seek(long pos): 将文件记录指针定位到 pos 位置
  ```
- 访问模式
  ```java
  r: 以只读方式打开
  rw: 以读、写方式打开
  ```

#### 对象的序列化

- 方式
  > Serializable
  > Externalizable
- 实现
  > ObjectOutputStream 对象的 writeObject()
  > ObjectInputStream 调用 readObject()

### NIO

#### Buffer 缓冲区

1. 缓冲区(Buffer): 在 Java 中 NIO 中负责数据的存取, 缓冲器对的实质就是数组: 用于存储不同数据类型的数据

   - 根据数据类型的不同(boolean 除外), 提供了相应的缓冲区:

   ```java
   ByteBuffer
   CharBuffer
   ShortBuffer
   IntBuffer
   LongBuffer
   FloatBuffer
   DoubleBuffer
   ```

   - 上述缓冲区的管理方法几乎都是一致的, 通过 `allocate()` **创建缓冲区**

2. 缓冲区存取数据的 2 个`核心方法`:

   ```java
   put() // 存数据到缓冲区
   get() // 获取缓冲区的数据
   flip() // 模式的转换:从写变为了读
   rewind() // 表示可以重复读取数据
   allocate() // 分配Buffer大小
   clear() // 清空缓冲区, 回到刚分配是的状态, 但是缓冲区的数据依旧存在, 但是处于遗忘状态
   hasRemaining() // 判断缓冲区中是否还有剩余的数据
   remaining() // 有的话, 还有几个
   ```

3. 缓冲区中的`四个核心属性`: **`position <= limit <= capacity`**

   - capacity: 容量, 表示缓冲区中最大的存储数据的容量, 一旦声明就不能改变
   - limit: 界限, 表示缓冲区中可以操作数据的大小.(limit 之后的数据不能进行读写)
   - position: 位置, 表示缓冲区正在操作对的数据的位置
   - mark: 标记, 表示记录当前的 position 的位置. 可以通过 `reset()的恢复` 到标记的位置

4. 直接缓冲区与非直接缓冲区:

   - 非直接缓冲区: 通过 allocate() 方法分配缓冲区, 将缓冲区建立在 JVM 的内存中.
   - 直接缓冲区: 通过 allocateDirect() 方法分配直接缓冲区, 将缓冲区直接分配在系统的物理内存中`[资源占用量大, 而且数据进去之后, 所有的操作权都归了OS;]`, `可以提高效率[ 直接缓冲区是去掉了copy的步揍]`.

     ```java
     ByteBuffer buffer = ByteBuffer.allocateDirect(1024); //这里一次读取一块, 所以要while进行读完
     MappedByteBuffer inMappedBuff = inchannel.map(MapMode.READ_ONLY, 0, inchannel.size()); //一次性分配足够的内存就读完了, 因此不需要while
     isDirect() //判断是否是直接缓冲区
     ```

#### Channel

1. 通道(Channel): 用于源节点与目标节点的连接. 在 Java NIO 中`负责缓冲区中数据的传输`. **Channel 本身不存储数据(类似于铁路), 因此要配合缓冲区使用(火车)**

2. Channel 的主要实现类:

   ```java
   java.nio.channels.Channel接口:
       FileChannel //本地
       SocketChannel //网络
       ServerChannel //网络
       DatagramChannel //网络
   ```

3. 获取 Channel:

   - Java 针对 Channel 的类提供了 `getChannel()` 方法

   ```java
   inchannel = fileInputStream.getChannel();
   // 本地:
   FileInputStream、FileOutputStream
   RandomAccessFile
   // 网络:
   Socket
   ServerSocket
   DatagramSocket
   ```

   - 在 JDK1.7 中的 NIO2 针对各个 Channel 提供了静态的方法 `open()`

   ```java
   inchannel = FileChannel.open(Paths.get("雪中悍刀行群像.rmvb"), StandardOpenOption.READ);
   outchannel = FileChannel.open(Paths.get("copy3.rmvb"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
   ```

   - 在 JDK1.7 中的 NIO2 的 Files 工具类的 `newByteChannel()`

4. Channel 之间得数据传输:
   ```java
   // transferFrom():
   outchannel.transferFrom(inchannel,0,inchannel.size());
   // transferTo():
   inchannel.transferTo(0, inchannel.size(), outchannel);
   ```

#### Channel 与 Buffer 的关系

1. 将 Channel 中的数据写入 Buffer:
   - 使用 Channel:
     ```java
     inchannel.read(buffer) //channel写入buffer中
     ```
   - 将 Channel 中的数据转换为 Buffer, 如果从其中读(Buffer)取就是讲 Channel 中的数据写入 Buffer 中:
     ```java
     // 将Channel中的数据转换成Buffer, 读取Buffer就是读取Channel中的数据 [将Channel数据写入Buffer]
     MappedByteBuffer inMappedBuff=inchannel.map(MapMode.READ_ONLY,0,inchannel.size());
     inMappedBuff.get(buffer);//将缓冲区中的数据获取到buffer中
     ```
2. 将 Buffer 中的数据写入 Channel:

   - 使用 Channel:
     ```java
     outchannel.write(buffer); //Buffer写入Channel
     ```
   - 将 Channel 中的数据转换为 Buffer, 如果向其中写(Buffer)入就是讲 Buffer 中的数据写入 Channel 中:
     ```java
     // 将 Channel 中的数据转换成 Buffer; 向Buffer里写, 就是向Channel中写[将Buffer中的数据写入Channel]
     MappedByteBuffer outMappedBuff=outchannel.map(MapMode.READ_WRITE,0,inchannel.size());
     outMappedBuff.put(buffer);  //将buffer中的数据存入缓冲区
     ```

3. IO 与 Channel 与 Selector 之间得关系:
   ```java
   1. IO 就是一个一个的 Channel (每一次的IO读取, 都是建立了一个连接类似于 Channel); 使用多线程解决 IO 阻塞占用问题; CPU占用
   2. Channel 的出现解决了这个问题: 她建立了一个 CHannel, 所有的数据都是使用 Buffer 在 Channel 中传输的(准备工作也是在这里了): 可以有多个Channel, 但是依旧减少了IO阻塞占用
   3. Selector 是源资源与目标资源之间建立一个机制, 当准备工作什么的都做好了, 才会连接目标资源成为 Channel[纯正的传输数据]
   ```

#### pipe

### 使用 NIO 完成网络通信

1. 使用 NIO 完成网络通信的三个核心:

   - Channel: 负责连接

     ```java
     获取Channel:
         针对一般的 Java 流对象(包括网络 Socket 通信 Channel): getChannel()
         针对 Channel 提供了 open 方法 Channel.open(Paths.get(),options)
         在JDK 1.7 中的 NIO2 的 Files 工具类的 newByteChannel()
     ```

   - 数据的存取:

     ```java
     1.缓冲区: put/get
         将 Channel 转换为 Buffer; 对B uffer 进行操作: 直接缓冲区:
             // 将 Channel 中的数据转换成 Buffer, 读取 Buffer 就是读取 Channel 中的数据[将Channel数据写入Buffer]
             MappedByteBuffer inMappedBuff=inchannel.map(MapMode.READ_ONLY,0,inchannel.size());
             // 将 Channel 中的数据转换成 Buffer; 向 Buffer 里写, 就是向 Channel 中写[将Buffer中的数据写入Channel]
             MappedByteBuffer outMappedBuff=outchannel.map(MapMode.READ_WRITE,0,inchannel.size());
     2.Channel: write/read
         // 非直接缓冲区:
         inchannel.read(Buffer buffer) + outchannel.write(buffer) + while
         // Channel之间得数据传输:
         inchannel.transferTo(0, inchannel.size(), outchannel);
         outchannel.transferFrom(inchannel,0,inchannel.size());
         // Channel的分散写入聚集读取: 等价于费直接缓冲区 + 多个Buffer
     ```

   - 选择器 Selector: 是 SelectableChannel 的多路复用器, 用于监控 SelectorableChannel 的 IO 状况

   ```java
   java.nio.channels.SelectableChannel接口:
       监控的是(实现了接口): SocketChannel
                       ServerSocketChannel
                       DatagarmChannel
   ```

2. 实现步揍(阻塞式的):

   - Client:
     ```java
     1. 获取Channel: SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8989));
     2. 读取本地 文件, 并发送给服务器: By CHannel+Buffer
     3. 关闭资源
     ```
   - Server:
     ```java
     1. 获取Channel: ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
     2. 绑定端口号: serverSocketChannel.bind(new InetSocketAddress(8989));
     3. 读取Client发送数据的Channel:SocketChannel socketChannel=serverSocketChannel.accept();
     4. 读取Channel中的数据, 并写入本地文件: Buffer+Channel
     5. 关闭资源
     ```

3. 实现步揍(非阻塞式的):

   - Client:
     ```java
     1. 获取Channel: SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8989));
     2. 转换为非阻塞式: socketChannel.configureBlocking(false);
     3. 读取本地 文件, 并发送给服务器: By CHannel+Buffer
     4. 关闭资源
     ```
   - Server:
     ```java
     1. 获取Channel: ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
     2. 转换为非阻塞式:serverSocketChannel.configureBlocking(false);
     3. 绑定端口号: serverSocketChannel.bind(new InetSocketAddress(8989));
     4. 获取选择器:Selector selector=Selector.open();
     5. 将选择器注册到Channel上,并且指定"监听接受事件":
         serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
     6. 轮询式获取选择器上已经就绪的事件:
         while(selector.select()>0){
     7. 获取当前选择器上注册的所有事件:
         Iterator<SelectionKey>iterator=selector.selectedKeys().iterator();
     8. 获取准备就绪的事件:
         SelectionKey selectionKey = (SelectionKey) iterator.next();
     9. 判断什么事件准备就绪:
         if(selectionKey.isAcceptable()){
     10. 接受Client的Channel:
         SocketChannel socketChannel=serverSocketChannel.accept();
     11. 将socket转换为非阻塞式
         socketChannel.configureBlocking(false);
     12. 将接受来的数据打印: 所以要注册写事件
         socketChannel.register(selector, SelectionKey.OP_READ);
     13. 写就绪了, 获取SocketChannel
         SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
     14. 将接受来的数据打印: (len = socketChannel.read(byteBuffer))>0 必须是这样
         ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
         int len = 0;
         while ((len = socketChannel.read(byteBuffer))>0 ) {
         // 没读到结尾返回0; 督导结尾返回-1
         // NIO:The number of bytes read, possibly zero, or -1 if the channel has reached end-of-stream
         // IO:the total number of bytes read into the buffer, or -1 if there is no more data because the end of the stream has been reached.
             System.out.println(len);
             byteBuffer.flip();
             System.out.println(new String(byteBuffer.array(), 0,len));
             byteBuffer.clear();
         }
     15. 取消选择键SelectionKey
         iterator.remove();
     ```

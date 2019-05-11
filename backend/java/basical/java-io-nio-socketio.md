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

- RandomAccessFile 类既可以读取文件内容，也可以向文件输出数据[随机访问文件的任何位置]
- 移动记录指针
  ```java
  long getFilePointer()：获取文件记录指针的当前位置
  void seek(long pos)：将文件记录指针定位到 pos 位置
  ```
- 访问模式
  ```java
  r: 以只读方式打开
  rw：以读、写方式打开
  ```

#### 对象的序列化

- 方式
  > Serializable
  > Externalizable
- 实现
  > ObjectOutputStream 对象的 writeObject()
  > ObjectInputStream 调用 readObject()

### NIO

#### channel

#### pipe

### SocketIO

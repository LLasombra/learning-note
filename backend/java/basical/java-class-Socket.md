## Socket

### 计算机网络基础

- 分层
  - 物理层
  - 数据链路层：PPP、MAC
  - 网络层：IP
  - 运输层：TCP(有连接)、UDP(无连接)
  - 应用层：DNS、FTP、WWW、SMTP、P2P

* 网络基础:

  1. 网络编程的目的就是指直接或间接地通过网络协议与其它计算机进行通讯。
  2. 网络编程中有两个主要的问题:
     - 如何准确地定位网络上一台或多台主机
     - 找到主机后如何可靠高效地进行数据传输。
  3. 要想让处于网络中的主机互相通信, 只是知道通信双方地址还是不够的, 还必须遵循一定的规则。有两套参考模型：

     - OSI 参考模型：模型过于理想化, 未能在因特网上进行广泛推广
     - TCP/IP 参考模型(或 TCP/IP 协议)：事实上的国际标准。

  4. 端口号与 IP 地址的组合得出一个网络套接字(socket)。

     - 端口号应该使用 1024~65535 这些端口中的某一个进行通信, 以免发生端口冲突。
     - socket 套接字的作用
       - 连接到远程主机
       - 绑定到端口
       - 接收从远程机器来的连接请求
       - 监听到达的数据
       - 发送数据
       - 接收数据
       - 关闭连接。

  5. 小结
     - IP 和端口号的具体意思:
       - IP 定位网络中的一台主机
       - 端口号定位主机的一个网络程序
     - InetAddress：对象表示网络中的一个地址
       - InetAddress address=InetAddress.getByName("127.0.01");
     - TCP/IP 编程：
       - 服务器/客户端：客户端发送请求到服务器, 服务器接受请求, 给予响应到客户端
       - ServerSocket/clientSocket

### Socket 常见的方法

|                           Method                            |                                             Function                                              |
| :---------------------------------------------------------: | :-----------------------------------------------------------------------------------------------: |
|                InetAddress getLocalAddress()                |                            返回对方 Socket 中的 IP 的 InetAddress 对象                            |
|                     int getLocalPort()                      |                                    返回本地 Socket 中的端口号                                     |
|                InetAddress getInetAddress()                 |                                    返回对方 Socket 中 IP 地址                                     |
|                        int getPort()                        |                                    返回对方 Socket 中的端口号                                     |
|               void close() throws IOException               |                                       关闭 Socket, 释放资源                                       |
|       InputStream getInputStream() throws IOException       |                     获取与 Socket 相关联的字节输入流, 用于从 Socket 中读数据                      |
|      OutputStream getOutputStream() throws IOException      |                    获取与 Socket 相关联的字节输出流, 用于向 Socket 中写数据。                     |
|             Socket accept() throws IOException              |                   等待客户端的连接请求, 返回与该客户端进行通信用的 Socket 对象                    |
|    void setSoTimeout(int timeout) throws SocketException    |                  等待时间 timeout, 未连接 InterruptedIOException(可继用,不阻塞);                  |
| void setSoTimeout(int timeout) throws SocketException(same) | timeout 值为 0, 则表示 accept()永远等待,该方法必须在倾听 Socket 创建后, 在 accept()之前调用才有效 |
|               void close()throws IOException                |                                          关闭监听 Socket                                          |
|                InetAddress getInetAddress()                 |                                   返回此服务器套接字的本地地址                                    |
|                     int getLocalPort()                      |                                  返回此套接字在其上监听的端口号                                   |

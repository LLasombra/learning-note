## Thread

1. 在 Java 中, `Thread 类代表一个线程`
2. 实现线程的两种方法:
   - 继承 `Thread` 接口
   - 实现 `Runnable` 接口
3. 继承线程 `Tread` 类:

   - 必须 重写 `run()` 方法: 放置实质的线程体

4. 启动线程:
   - 创建线程 Thread 对象
   - 调用 thread 对象的 `start()` 方法启动线程, 而不是 run()方法
5. 实现 `Runnable` 接口的方式:
   - 创建实现 `Runnable` 接口的实现类: 必须实现 `run()` 方法
   - 创建 5.1 对应的 `Runnable` 接口的实现类对象
   - 创建 `Thread` 对象, 利用 `Thread(Runnable mr)`
   - 对应 `Thread` 类 `start()` 方法启动线程
6. 方法
   - 调用 `sleep` 方法,使线程休眠一段时间
   - 调用 `join` 方法: `当前线程调用其他线程`
   - 执行 I/O 操作:
   - `interrupt` 方法: 调用阻塞线程的 ; 直接结束 `sleep`, 并抛出一个 `InterruptedException` 异常
   - `isAlive` 方法:
   - `线程优先级`
7. 关于线程通信
   - 方法: `wait(), notify(), notifyAll();`
   - 这些方法要在同步方法中调用
8. Notice:
   - testThread(在 main 函数中创建的线程的 run 方法可以使用 sleep; 其他非 main 函数中, 调用带有 sleep 的 run 创建的线程不会执行 run 方法)
   - 当 run 方法中使用 sleep 时, new 出来的对象就不会执行 run 方法了, 可以使用 sleep 加回来; 或者在 main 方法中创建线程

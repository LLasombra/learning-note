## File 类

- 常用方法
  - new File(PATH);
  - getName()
  - getAbsolutePath()
  - isDirectory()
  - isFile()
  - createNewFile()
  - exists()
  - delete()
  - length()
  - renameTo()

```java
import java.io.File;
import java.io.IOException;
import org.junit.Test;

/**
 *    功能：测试File类。
 *        File 类代表与平台无关的文件和目录。
 *        File能新建、删除、重命名文件和目录, 但 File 不能访问文件内容本身。
 *        如果需要访问文件内容本身, 则需要使用输入/输出流。
 */
public class _File {
    @Test
    public void testFile() throws IOException {
        //1.创建File对象: 这里真是URL, 而且必须是工程根目录下的；如果根目录下没有这个文件的话, 则一些操作无效2.1 2.2有效
        File file = new File("hello.txt");    //这里是工程根目录

        //2.测试File对象的方法
        //2.1文件名相关方法
        String fileName = file.getName();
        System.out.println(fileName);
        //2.2访问文件的绝对路径
        String path = file.getAbsolutePath();
        System.out.println(path);
        //2.3重命名
        // file.renameTo(new File("1.txt"));
        //这里应该是两个操作:改名, 重新放到String参数代表的目录

        //3.文件检测：
        //3.1文件是否存在：
        System.out.println(file.exists());
        File dir = new File("Java_Project") ;
        //3.2是否为文件、目录
        System.out.println(dir.isDirectory());
        File dir2 = new File("hello.txt") ;
        System.out.println(dir2.exists());
        System.out.println(dir2.isFile());

        //4.获取文件的常规信息：
        System.out.println(file.length());

        System.out.println("--------------------------------");
        //5.文件相关：
        File file2 = new File("hello2.txt") ;
        System.out.println(file2.exists());        //false
        file2.createNewFile();    //创建文件
        System.out.println(file2.exists());        //true
        file2.delete();
        System.out.println(file2.exists());        //false
    }
}
```

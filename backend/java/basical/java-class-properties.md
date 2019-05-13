## Properties

### 知识点

1. `Properties 类是 Hashtable 的子类`, 该对象用于处理属性文件
   由于属性文件里的 key、value 都是字符串类型，所以 properties 里的 Key 和 Value 都是字符串类型的

2. demo
   ```java
    //1.创建Properties对象
    Properties properties=new Properties();
    //2.调用Properties对象的.load()方法加载属性文件所对应的输入流
    InputStream inStream=new FileInputStream("./src/jdbc.properties");    //OK    FileInputStream(String URL) :这里的URL为根目录
    // InputStream inStream=TestProperties.class.getClassLoader().getResourceAsStream("jdbc.properties");  //OK
    //InputStream inStream=TestProperties.class.getResourceAsStream("jdbc.properties");   //error
    properties.load(inStream);
    //3.调用getProperties(String Key)方法获取属性值
    String ps=properties.getProperty("root");
    System.out.println(ps);
   ```

### demo

```java
/**
 * 功能:
 *  1. Properties类对应.properties 属性文件
 *     Properties里存放的是键值对，Key Value 都是String
 *  2. FileInputStream(String URL): 这里的URL为根目录
 */
public class TestProperties {
  public static void main(String[] args) throws IOException {
    // 1.创建Properties对象
    Properties properties = new Properties();
    // 2.调用Properties对象的.load()方法加载属性文件所对应的输入流
    InputStream inStream=new FileInputStream("./src/jdbc.properties");
    // InputStream inStream=TestProperties.class.getClassLoader().getResourceAsStream("jdbc.properties");
    // InputStream inStream=TestProperties.class.getResourceAsStream("jdbc.properties");   //error
    properties.load(inStream);
    //3.调用getProperties(String Key)方法获取属性值
    String ps=properties.getProperty("root");
    System.out.println(ps);
  }
}
```

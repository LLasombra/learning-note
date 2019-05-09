## Singleton

```java

/***
 * 功能：测试单子模式
 * 	1. 当类加载时就创建了对象
 *
 * 	2. 与自定义的枚举类的区别：
 *      单子模式：(private + getter)
 *      枚举类：默认的[public static final], 所以 Define_Enumration2.SPRING is Ok!
 */
public class Singleton {
    // 1.由于只有有一个类的实例, 所以要私有化构造函数
    private Singleton() {
        //代码
    }
    // 2.要创建一个实例, 并且要让外部可以访问这个实例, 所以要是有static关键字修饰对象
    private static Singleton singleton = new Singleton();
    // 3.为了让外部不能修改该实例, 所以使用getter使其只读
    public static Singleton getSingleton() {
        return singleton;
    }
}

/***
 * 功能：当需要使用时, 才会创建对象
 */
class Singleton2 {
    // 1.由于只有有一个类的实例, 所以要私有化构造函数
    private Singleton2() {
        //代码
    }
    // 2.要创建一个实例, 并且要让外部可以访问这个实例, 所以要是有static关键字修饰对象
    private static Singleton2 singleton = null;
    // 3.为了让外部不能修改该实例, 所以使用getter使其只读
    public static Singleton2 getSingleton() {
        if(singleton==null)
            singleton=new Singleton2();
        return singleton;
    }
}

public class testSingleton {
    public static void main(String[] args) {
        // 测试单子模式代码01
        Singleton instance = Singleton.getSingleton();
        System.out.println(instance);
    }
}
```

```java

/**
 *	功能：自定义创建枚举类
 *	测试类：test.Traversal.java
 */
enum Define_Enumration2 implements info{

    //1.第一行直接定义枚举常量；等价于创建对象
    SPRING("春天", "345"){//这里也可以在最后重写getInfo()方法, 使用Switch一个一个判断;
        @Override
        public String getInfo() {
            return "chu";
        }
    },SUMMer("夏天", "678"){
        @Override
        public String getInfo() {
            return "xia";
        }
    },FALL("秋天", "91011"){
        @Override
        public String getInfo() {
            return "qiu";
        }
    }, WINTER("冬天", "1212"){
        @Override
        public String getInfo() {
            return "dong";
        }
    };
    //2.定义枚举类的变量为final类型变量
    private final String desc;
    private final String name;
    //3.私有化构造器：枚举对象是唯一的
    private Define_Enumration2(String name,String desc) {
        this.desc = desc;
        this.name = name;
    }

    //4.书写getter方法, 暴露枚举对象的成员(只读)
    public String getDesc() {
        return desc;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Sea_Enumeration [desc=" + desc + ", name=" + name + "]";
    }

}
```

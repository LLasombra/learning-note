## this

1.  this 关键字：是当前类的一个对象的引用；**`是调用这个方法的对象`**。

    ```java
    // 在方法中应用当前对象的属性：
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // 在构造方法 中调用重载的构造方法;this 必须放在构造函数的第一行：
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String lastName, Account account){
        // this 关键字调用重载的其它构造方法
        this(firstName, lastName);
        this.account = account;
    }
    ```

2.  覆盖方法不能使用比被覆盖方法更严格的访问权限。(多态)

3.  super 关键字：**`是在子类对象方法中对父类对象的引用`**

    - 在子类对象的方法中引用父类对象的成员
    - 在子类构造器中调用父类的构造器
    - 可以在子类中通过 super(参数列表) 的方式来调用父类的构造器
    - 在默认情况下子类的构造器调用父类的无参构造器
    - 若父类定义了带参数的构造器, 子系统将不再为父类提供无参数的构造器。而子类构造器必须调用父类的一个构造器。
      > 父类显示定义无参构造器
      > 在子类构造器中显示调用`弗雷德带参数的构造器`
      ```java
      public Student (String name,int age,String school){
          super(name,age);
          this.school=school;
      }
      ```
    - 与 this 类似, super(参数列表)必须放在参数构造器的第一行。这说明 this(参数列表)和 super(参数列表)不能同时存在。

4.  super 与 this 关键字：

    > super(参数列表)语句调用父类的构造方法
    > **this `关键字调用重载的其它构造方法`**。

5.  多态：同样类型的变量, 调用同样的方法, 却产生完全不同的行为。

    > 1. 当父类类型的变量指向一个子类对象, 调用父类中已经被重写的方法的时候。
    > 2. 在多态的情况下(当父类类型的变量指向一个子类对象):不能调用子类中新添加的方法
    > 3. 在多态的情况下, 可以对对象进行强制类型转换。

        ```java
        Person p=new Man();
        Man man=(Man)p;

    > 4. 父类对象可以转换为任何子类类型, 但可能发生类型转换错误

    ```java
    子类转换为父类(上转型转换)是安全的
    只有在有父子关系的情况下, 才能进行类型的强制转换
    ```

    > 5. instanceOf 运算符：如果 x 属于类 A 的子类 B, x instanceof A 值也为 true。

### demo

```java
public class This {

    private int a=30;

    public test(int b) {
        {
            int a=26;
            {
                int c=560;
                this.a = c;
                System.out.println(a);
                System.out.println(this.a);
                System.out.println(this instanceof test);  //true
            }
            System.out.println(this.a);
        }
        System.out.println(a);
        // this.a = a;
    }

    public static void main(String[] args) {
        test test1=new test(5);
        System.out.println(test1.a);  //26
    }
}
```

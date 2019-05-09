## java

### int 转 string:

    string s=string.valueOf(int i);
    string s=Integer.toString(int i);
    string s=''+i;

### string 转 int:

    int i=Integer.parseInt(string s);
    int i=Integer.ValueOf(string s).intValue();
    int i=s-0;

## js

### int 转 string:

    string s=Integer.toString(int i);
    string s=''+i;

### string 转 int:

    int i=Integer.parseInt(string s);
    int i=s-0;

## memory

```java
package basical;


/**
 * 功能：测试一些内存的分配：
 * 	1.Integer
 * 	2.String
 * 	3.int基本数据类型
 * @author Zhang
 *
 */
public class testSapce {
    public static void main(String[] args) {
        Integer integer=new Integer(2);
        Integer integer2=new Integer(2);
        System.out.println(integer==integer2);  		//false
        System.out.println(integer.equals(integer2));	//true

        Integer integer3=2;		//这里自动装箱
        Integer integer4=2;
        System.out.println(integer3==integer4);  	//这里自动拆箱	//true
        System.out.println(integer3.equals(integer4));	//true

        String string="123";
        String string2="123";
        System.out.println(string==string2);  		//true
        System.out.println(string.equals(string2));	//true
    }
}
```

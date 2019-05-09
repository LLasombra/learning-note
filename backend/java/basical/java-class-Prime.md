## Prime 自定义对象

```java
import java.util.Scanner;

/**
 * 功能：判断一个数是否为素数
 * @author Zhang
 */
public class TestPrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入要判斷的數字：");
        int n = scanner.nextInt();
        scanner.close();
        Prime(n);

        /**
         * 功能：判断一定范围内的所有素数
         */
        long begin=System.currentTimeMillis();
        for (int i = 2; i < 20000; i++) {
            System.out.println(i + "是素数：" + Prime(i));
        }
        long end=System.currentTimeMillis();
        System.out.println("代码所用时间："+(end-begin));
    }

    public static boolean Prime(int n) {
        int i = 2;
        //int sn=(int) Math.sqrt(n);    //负优化
        for (; i <=Math.sqrt(n); i++) {
            if (n % i == 0)
                break;
            else
                continue;
        }
        if (i >= Math.sqrt(n))
            return true;
        else
            return false;
    }
}
```

## switch

- switch(表达式) 中表达式的返回值必须是下述几种类型之一:
  byte, char, short, int,枚举, 字符串；
- case 子句中的值必须是常量, 且所有 case 子句中的值应是不同的;
  default 子句是任选的;
- break 语句用来在执行完一个 case 分支后使程序跳出 switch 语句块；
- **如果没有 break, 正确的 case 之后全部执行; 且用 break 之后 case 真能执行一个. 可以在一定的情况下不使用 break**
- 输入？月？日, 求出是这一年的第几天

  ```java
  import java.util.Scanner;

  /**
  * 功能：输入？月？日, 求出是这一年的第几天
  * @author Zhang
  */
  public class TestSwitch {
      public static void main(String[] args) {
          int i = 1;
          int days = 0;
          Scanner scanner = new Scanner(System.in);
          System.out.println("请输入月份：");
          int month = scanner.nextInt();
          System.out.println("请输入日期：");
          int day = scanner.nextInt();
          scanner.close();

          switch (month) {
              case 12:
                  days += 30;
              case 11:
                  days += 31;
              case 10:
                  days += 30;
              case 9:
                  days += 31;
              case 8:
                  days += 31;
              case 7:
                  days += 30;
              case 6:
                  days += 31;
              case 5:
                  days += 30;
              case 4:
                  days += 31;
              case 3:
                  days += 28;
              case 2:
                  days += 31;
              case 1:
                  days += day;
              default:
                  break;
          }
          System.out.println(days);
      }
  }
  ```

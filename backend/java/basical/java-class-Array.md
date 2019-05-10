## Array

### 创建数组

```java
int[] a1 = new int[] { 1, 2, 3 };
int[] a2 = { 1, 2, 3 };
int[][] a = new int[5][];
// 循环
int[] a3 = new int[3];
for (int a : a3) {
    a = 0;
}
for (int i = 0; i < a1.length; ji++) {
}
```

### 数组排序

- 基本方法

  ```java
  Collections.sort(List<>);
  Collections.sort(List<>, Comparator);
  Arrays.sort(int[])
  Arrays.asList()
  ```

- 自定义的排序 List：comparator 方法
  ```java
  List<Integer> list02 = new ArrayList<>(...);
  // 自定义Comparator对象, 自定义排序 功能：降序;最好使用compareTo方法
  Comparator<Integer> c = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
          return o2.compareTo(o1);
      }
  };
  Collections.sort(list02, c);
  ```

## demo

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.junit.Test;

/**
 * 功能：
 *  1.数组的初始化的三种方法
 *  2.数组的排序问题：Arrays.sort(int[])、Collections.sort(List<>);
 *  3.二维数组的
 *  4.打印杨辉三角
 */
public class Array {

    @Test
    /**
     * 4.打印杨辉三角
     */
    public void printTriangle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要输出的行数：");
        int n = scanner.nextInt();
        scanner.close();
        //声明一个二维数组
        int [][]a=new int [n][];
        a[0]=new  int[]{1};
        a[1]=new  int[]{1,1};

        for(int i=2;i<n;i++){
            a[i]=new int[i+1];  //a[0]有一个元素
            for(int j=1;j<a[i].length-1;j++){
                //初始化二维数组为杨辉三角
                a[i][0]=1;
                a[i][a[i].length-1]=1;
                a[i][j]=a[i-1][j-1]+a[i-1][j];
            }
        }
        //遍历
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++){
                System.out.print(a[i][j]+"\t");
            }
            System.out.println();
        }
    }

    @Test
    /**
     * 2.数组的排序问题：Collections.sort(List<>);
     */
    public void sort_Array_01() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生人数：");
        int n = scanner.nextInt();

        // 初始化学生的分数
        List<Integer> score = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("请输入第" + (i + 1) + "名同学的成绩：");
            score.add(scanner.nextInt());
        }
        scanner.close();

        // 对sort进行排序
        Collections.sort(score);
        Arrays.sort(score); // ok
    }
}
```

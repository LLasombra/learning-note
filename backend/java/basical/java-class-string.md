## String

1.  String 是 `final` 类型的不可变字符序列
2.  关于字符串缓冲池, 直接通过 `=` 为字符串赋值, 会先在缓冲池中查找有无一样的字符串, 若有就把字符串的引用付给字符串变量; 否则会创建一个新的字符串, 并将其放入字符串缓冲池中, 在赋值
3.  字符串的常用方法
    ```java
    trim() // 去除前后的空格
    subString(fromIndex) // 求子字符串 fromIndex--end
    subString(fromIndex ,toIndex)
    indexOf(String str,int fromIndex) // 求字符串的索引
    split() // split(" {1,}")
    charAt(int index)
    byte[] getBytes()
    ```
4.  字符串与数组的转换:

    ```java
    new String(charArray) str.toCharArray()
    ```

5.  字符串与 int 的转换:

    ```java
    // int convert to int
    string s = string.valueOf(int i);
    string s = Integer.toString(int i);
    string s = ""+i;

    // int convert to String
    int i = Integer.ValueOf(string s).intValue();
    int i = Integer.parseInt(string s);
    int i = s-0;
    ```

6.  `StringBuffer(线程安全)/StringBuilder(常用):可修改的字符序列`

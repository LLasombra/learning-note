## Collection

### 结构图

![avatar](https://img-blog.csdnimg.cn/20190513192228212.gif)

1. Java 集合可分为 Set、List 和 Map 三种体系: collection 包含 Set、List、Queue; `不包含 Map`s
   > Set: 无序、不可重复的集合
   > List: 有序, 可重复的集合
   > Map: 具有映射关系的集合

### 知识点

1. Collection: `List有序、 Set无序`
2. 用于集合添加元素的方法:
   ```java
   add(E e) //E为泛型元素
   addAll(Collection <? extends E>c)
   ```
3. 用于范围集合的方法:
   - 获取集合的长度: size()
   - 对集合进行遍历 的方法: iterator()可以得到对应的 Iterator 接口对象
   - 移除元素:
   ```java
   remove()  // 移除某一指定的元素通过equals方法来判断要移除的元素是否在集合中, 以及是否移除成功
   removeAll() //  collection.removeAll(collection);
   clear()  // 使集合中的元素置空  collection.clear();
   ```
   - 用于检测集合的方法
   ```java
   contains(obj)
   containsAll(Collection <? extends E>c)
   isEmpty()
   toArray() // Person []pers = persons.toArray(new Person[0]);
   ```
4. Iterator: 迭代器[在集合中无法获取某一个具体元素]
   - 获取 Iterator 接口对象
   - 使用 while 和 Iterator 遍历集合中的每一个元素: 具体使用 Iterator 接口的 `hasNext()` 和 `next()` 方法
   ```java
   Iterator iterator=collection.iterator();
   while(iterator.hasNext()){
       System.out.println(iterator.next());
   }
   ```
5. 测试 Collections 工具类

   - 获取线程安全集合

     - **ArrayList、HashMap、HashSet...都不是线程安全的**
     - 调用 `Collections.synchronizedXxx()` 获取线程安全

     ```java
     // 线程安全集合
     Collections.synchronizedXxx()
     // 获取线程安全的List对象, 使用synchronizedList()
     java.util.List<Object> list = Collections.synchronizedList(new ArrayList<>());
     ```

   - 对 `Enumeration` 对象进行遍历: `hasMoreElements()` `netElement()`

   ```java
   Enumeration names = Collections.enumeration(new ArrayList<>());
   ```

   - 排序操作:
     ```java
     reverse(List) // 反转 List 中元素的顺序
     shuffle(List) // 对 List 集合元素进行随机排序
     sort(List) // 根据元素的自然顺序对指定 List 集合元素按升序排序
     sort(List, Comparator) // 根据指定的 Comparator 产生的顺序对 List 集合元素进行排序
     swap(List, int,  int) // 将指定 list 集合中i 处元素和 j 处元素进行交换
     Object max(Collection) // 根据元素的自然顺序,返回给定集合中的最大元素
     Object max(Collection, Comparator) // 根据 Comparator 指定的顺序, 返回给定集合中的最大元素
     Object min(Collection) //自然排序: 对象要实现Comparable接口
     Object min(Collection, Comparator) //定制排序
     int frequency(Collection, Object) // 返回指定集合中指定元素的出现次数
     boolean replaceAll(List list, Object oldVal, Object newVal) // 使用新值替换 List 对象的所有旧值
     ```

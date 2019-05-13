## Set

1. Set 是 Collection 的子接口, Collection 中的方法都适应
2. Set 中不允许存放相同元素, 判断相等使用 equals 方法, 返回 true
3. HashSet

   - 不能保证元素的排列顺序
   - `HashSet不是线程安全的`
   - 集合元素可以使用 null
   - 对于 HashSet: `如果两个对象通过equals()方法返回true, 这两个对象的HashCode值也应该相同`

4. LinkedHashSet:

   - LinkedHashSet 是 HashSet 的子类
   - 使用链表维护元素的次序, 这使得元素看起来是以插入形式保存的
   - LinkedHashSet 不许允许放相同元素

5. TreeSet:

   - 如果使用 TreeSet() 无参构造器创建一个 TreeSet 对象, 则要求放入其中的元素类必须实现 Comparable 接口, 所以其中不能放 null 元素
   - 必须放入同样类的对象, 否则可能会发生类型转换异常
   - 两个对象通过 Comparable 接口的 CompareTo(obj)方法的返回值来比较大小, 并进行升序排序
   - 当需要把一个对象放入 TreeSet 中, 重写该对象对应得 equals()方法时, 应该保证方法与 compareTo(obj)方法有一致的结果

   ```java
   Comparator<String> comparator=new Comparator<String>() {
       @Override
       public int compare(String o1, String o2) {
           // TODO Auto-generated method stub
           return 0;
       }
   };
   TreeSet<String> set=new TreeSet<>(comparator);
   ```

   - TreeSet 方法
     > Comparator comparator()
     > Object first()
     > Object last()
     > Object lower(Object e)
     > Object higher(Object e)
     > SortedSet subSet(fromElement, toElement)
     > SortedSet headSet(toElement)
     > SortedSet tailSet(fromElement)

6. TreeSet 的实现:

   - TreeSet[TreeMap 的键]的两种排序方式：自然排序[`对象重写 CompareTo`]、[`定制排序 comparator`]
     > 自然排序：被比较的实现类要实现 Comparable 接口的 CompareTo 方法
     > 定制排序：实现 Comparator 接口；创建 TreeSet 时做参数传入. TreeSet<Person>pers=new TreeSet<>(Compartor);

   ```java
   // 1.自然排序: 不需要Comparator;但是被比较的对象要实现Comparable接口并重写CompareTo方法
   @Test
   /**
   * 说明: 这里Person类没有实现Comparable接口中的CompareTo方法
   * TreeSet:必须实现comparator接口来定制对象(必须实现CompareTo或者是带有*CompareTo方法的类型)有序, 不可重复
   *   结果:
   *       Person [name=AA, age=10]
   *       Person [name=BB, age=11]
   *       Person [name=CC, age=12]
   *       Person [name=DD, age=13]
   *       Person [name=EE, age=14]
   */
   public void TestTreeSet() {
       Set<Person> persons = new TreeSet<>();

       persons.add(new Person("AA", 10));
       persons.add(new Person("DD", 13));
       persons.add(new Person("EE", 14));
       persons.add(new Person("BB", 11));
       persons.add(new Person("CC", 12));

       Iterator<Person> it = persons.iterator();
       while (it.hasNext()) {
           System.out.println(it.next());
       }
   }

   // 2.定制排序带有Comparator
   @Test
   /**
   * 说明: 这里Person2类没有实现CompareTo方法
   * TreeSet2:必须实现comparator接口来定制对象(必须实现CompareTo或者是带有*CompareTo方法的类型)有序, 不可重复
   *   结果:
   *       Person [name=AA, age=10]
   *       Person [name=BB, age=11]
   *       Person [name=CC, age=12]
   *       Person [name=DD, age=13]
   *       Person [name=EE, age=14]
   */
   public void TestTreeSet2() {
       Comparator<Person2> comparator = new Comparator<Person2>() {
           @Override
           public int compare(Person2 o1, Person2 o2) {
               // 按照age比较
               return (o1.getAge()).compareTo(o2.getAge());
           }
       };

       Set<Person> persons = new TreeSet<>(comparator);

       persons.add(new Person("AA", 10));
       persons.add(new Person("DD", 13));
       persons.add(new Person("EE", 14));
       persons.add(new Person("BB", 11));
       persons.add(new Person("CC", 12));

       Iterator<Person> it = persons.iterator();
       while (it.hasNext()) {
           System.out.println(it.next());
       }
   }
   ```

7. 小结
   - HashSet: 无序, 不可重复
   - LinkedHashSet: 有序, 不可重复
   - TreeSet: 必须实现 Comparator 接口来定制对象(必须实现 CompareTo 或者是带有 CompareTo 方法的类型); 有序不可重复

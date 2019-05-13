## List

1. 特性:

```js
List 代表元素有序、且可以重复的集合，集合中每个元素都有 其对应得顺序索引  ArrayList 是其典型实现
List 允许使用重复元素，可以通过索引来访问指定位置的元素
List默认按照元素添加顺序设置元素的索引
```

2. 返回一个只读形的 List, 不是 ArrayList 也不是 Vector

```java
Arrays.asList(Object …args);
```

3. 方法:

```java
void add(int index ,Object element)
boolean addAll(int index ,Collection elements)
Object get(int index)
int indexOf(Object obj)    //获取指定元素的索引值		charAt(int i)
int lastIndexof(Object obj)
Object remove(int index)
Object set(int index ,Object element)   //替换
List subList(int fromIndex,int toIndex)
```

4. List 的遍历:

```java
List<String> list = new ArrayList<String>();
list.add("aaa");
list.add("bbb");
list.add("ccc");

// 1.超级for循环遍历
for(String attribute : list) {
    System.out.println(attribute);
}
// 2.对于ArrayList来说速度比较快, 用for循环, 以size为条件遍历:
for(int i = 0 ; i < list.size() ; i++) {
    system.out.println(list.get(i));
}
// 3.集合类的通用遍历方式, 从很早的版本就有, 用迭代器迭代
Iterator it = list.iterator();
while(it.hasNext()) {
    System.ou.println(it.next);
}
// 4.ListIterator:
```

5. List 中元素的排序:

```java
Collections.sort(persons, new Comparator<Person>() {
    @Override
    public int compare(Person o1, Person o2) {
        // TODO Auto-generated method stub
        return o1.getAge().compareTo(o2.getAge());
    }
});
```

## Map: HashMap 是其典型的实现, 不属于 Collection

1. Map 用以保存具有映射关系的数据, 因此 Map 集合例保存着两组值 `<key ,values>`
2. Map 中的 `key values` 可以是 `任何引用类型` 的数据
3. Map 中的 key `不允许重复`, 即同一个 Map 对象的任何两个 key 通过 equals 方法比较都要返回 false
4. key 和 values 之间单项一对一关系, 即通过指定的 key 总能找到 `唯一` 的 values
5. HashMap 定义了 HashSet: `HashMap 中的 key 就是 HashSet 里的元素`
6. `LinkedHashMap` 是有序的
7. TreeMap:

   - 如果使用 TreeMap() 无参构造器创建一个 TreeMap 对象, 则要求放入其中的元素类 `必须实现Comparable接口`, 所以其中不能放 null 元素
   - `必须放入同样类的对象`, 否则可能会发生类型转换异常
   - 两个对象通过 Comparable 接口的 `CompareTo(obj)` 方法的返回值来比较大小, 并进行`升序排序`
   - 当需要把一个对象放入 TreeMap 中, 重写该对象对应得 equals() 方法时, 应该保证方法与 compareTo(obj)方法有一致的结果

   ```java
   Comparator<String> comparator=new Comparator<String>() {
       @Override
       public int compare(String o1, String o2) {
           // TODO Auto-generated method stub
           return 0;
       }
   };
   TreeMap<String> set=new TreeMap<>(comparator);
   ```

8. 方法:

```java
// 1.添加元素
put(key,value)   ;put(map);	putAll(Map<? extends K,? extends V> map)

// 2.从map中取出元素
// 2.1得到键的集合:
Set map.KeySet()
// 2.2 利用键得到值
Value map.get(key)
//2.3 得到值的集合:
Collection<V> map.values()
//2.4 得到键值对的集合 entrySet() :
Set<Map.Entry<K,V>> entrySet()
for(Map.Entry<String, Object> entry : map.entrySet()){
    String key = entry.getKey();
    Object val = entry.getValues();
}
// 2.5 判断值是否在:
Boolean map.containsValue(value)
Boolean map.containsKey(key)
// 2.6 移除:
map.remove(key)
// 2.7 工具方法:
size()
isEmpty()

// 3.遍历:
// 3.1 map.entrySet()得到键值对
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
}
// 3.2在for-each循环中遍历 keys 或 values
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
for (Integer key : map.keySet()) {
    System.out.println("Key = " + key);
}
for (Integer value : map.values()) {
    System.out.println("Value = " + value);
}
// 3.3使用 entrySet().Iterator() 遍历
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
while (entries.hasNext()) {
    Map.Entry<Integer, Integer> entry = entries.next();
    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
}
// 3.4不使用泛型:
Map map = new HashMap();
Iterator entries = map.entrySet().iterator();
while (entries.hasNext()) {
    Map.Entry entry = (Map.Entry) entries.next();
    Integer key = (Integer)entry.getKey();
    Integer value = (Integer)entry.getValue();
    System.out.println("Key = " + key + ", Value = " + value);
}
```

9. TreeSet 中的排序

   ```java
   /**
    * 按照key排序: 键已经实现了Comparator接口
    */
   Comparator<String> comparator = new Comparator<String>() {
       @Override
       public int compare(String o1, String o2) {
           return o1.compareTo(o2);
       }
   };
   Map<String, Integer> map2 = new TreeMap<>(comparator);
   map2.putAll(map);

   /**
    * 案值排序:相对计较麻烦
    * 原理: 将 map 中的键值对放入 list 列表中, Collections.sort(List<T> list, Comparator<? super T> c), 之后装入map中
    */
   // 将 map 中的键值对放入 list 列表中
   List<Map.Entry<String, Integer>> entriesList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
   Collections.sort(entriesList,new Comparator<Map.Entry<String, Integer>>() {
       @Override
       public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
           return o1.getValue().compareTo(o2.getValue());
       }
   });

   Map<String, Integer> map3 = new LinkedHashMap<>(); //LinkedHashMap是有序的

   Iterator<Map.Entry<String, Integer>>iterator = entriesList.iterator();
   Entry<String, Integer>entry=null;
   while(iterator.hasNext()){
       entry=iterator.next();
       map3.put(entry.getKey(),entry.getValue());
   } // over

   //这个方法也是可以的
   for(Map.Entry<String, Integer>ma:entriesList){
       map3.put(ma.getKey(), ma.getValue());
   }
   ```

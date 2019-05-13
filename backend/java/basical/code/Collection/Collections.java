package basical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;


/**
 *
 * 功能：测试Collections工具类
 *     1.Collections.synchronizedXxx()     //获取线程安全
 *     2.排序操作：
 *       reverse(List)：反转 List 中元素的顺序
 *       shuffle(List)：对 List 集合元素进行随机排序
 *       sort(List)：根据元素的自然顺序对指定 List 集合元素按升序排序
 *       sort(List，Comparator)：根据指定的 Comparator 产生的顺序对 List 集合元素进行排序  //TsetList.java
 *       swap(List，int， int)：将指定 list 集合中的 i 处元素和 j 处元素进行交换
 *       Object max(Collection)：根据元素的自然顺序，返回给定集合中的最大元素
 *       Object max(Collection，Comparator)：根据 Comparator 指定的顺序，返回给定集合中的最大元素
 *       Object min(Collection)
 *       Object min(Collection，Comparator)
 *       int frequency(Collection，Object)：返回指定集合中指定元素的出现次数
 *       boolean replaceAll(List list，Object oldVal，Object newVal)：使用新值替换 List 对象的所有旧值
 *    3. Person []pers=persons.toArray(new Person[0]);
 */
public class TestCollections {
    @SuppressWarnings("unused")
    @Test
    /**
     * 功能：ArrayList、HashMap、HashSet...都不是线程安全的，
     *     获取线程安全的对象调用Collections.synchronizedXxx() 方法
     */
    public void TestSynchronized() {

        //获取List线程安全：  List<Integer>integers3=Collections.synchronizedList(new ArrayList<Integer>());
        List<Integer>integers=new ArrayList<>();
        List<Integer>integers2=Collections.synchronizedList(integers);
    }


    @Test
    /**
     * 功能：取集合Collection中最小值(按需求找)
     *   1.这里测试的是List：但是对于所有Collection都适应
     * Object min(Collection)         //自然排序：对象要实现Comparable接口
     * Object min(Collection，Comparator)  //定制排序，姓名按升序排列
     */
    public void TestMin() {

        //1.1List中的min():对象要实现Comparable接口
        List<Person2>Pers=new ArrayList<>();
        Pers.add(new Person2("d",20));
        Pers.add(new Person2("b",15));
        Pers.add(new Person2("c",60));
        Pers.add(new Person2("a",28));
        Pers.add(new Person2("e",12));
        Pers.add(new Person2("f",42));
        Person2 per=Collections.min(Pers);
        System.out.println(per);

        //1.2List中的min():定制排序，姓名按升序排列
        List<Person>persons=new ArrayList<>();
        persons.add(new Person("d",20));
        persons.add(new Person("b",15));
        persons.add(new Person("c",60));
        persons.add(new Person("a",28));
        persons.add(new Person("e",12));
        persons.add(new Person("f",42));

        Person person=Collections.min(persons,new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                // TODO Auto-generated method stub
                return o1.getName().compareTo(o2.getName());
            }
        });

        System.out.println(person);
    }

    /**
     * 功能：讲Person类型的集合转换为Person的数组
     */
    public void TesttoArray() {
        //创建
        Collection<Person>persons=new ArrayList<>();
        //添加
        persons.add(new Person("d",20));
        persons.add(new Person("b",15));
        persons.add(new Person("c",60));
        persons.add(new Person("a",28));
        persons.add(new Person("e",12));
        persons.add(new Person("f",42));
        //将Person类型的集合转换为Person的数组
        Person []pers=persons.toArray(new Person[0]);
        System.out.println(pers.length);
    }

}













/**
 * 功能：这个类时为测试List准备的
 * @author Zhang
 *
 */
class Person2 implements Comparable<Person2> {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person2 [name=" + name + ", age=" + age + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person2 other = (Person2) obj;
        if (age != other.age)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public Person2() {
        // TODO Auto-generated constructor stub
    }

    public Person2(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person2 o) {
        // TODO Auto-generated method stub
        return (this.age).compareTo(o.age);
    }

}

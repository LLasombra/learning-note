package basical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;



/**
 * 功能：	1.这里只写了一个不定个数参数的测试
 * 		2.Arrays.asList(Object …args);	//返回一个只读形的List,不是ArrayList也不是Vector
 *
 */
public class TestList {

	//@Test
	/**
	 * 功能：这里只写了一个不定个数参数的测试
	 */
	public void testArguments_length(String ...args) {
		//System.out.println(args.length);
	}
	
	@Test
	/**
	 * 功能：对List列表中的数据按年龄生序排序
	 */
	public void ListSort(){
		List<Person>persons=new ArrayList<>();
		persons.add(new Person("张壮壮",20));
		persons.add(new Person("连顺",15));
		persons.add(new Person("王凡",60));
		persons.add(new Person("李壮",28));
		persons.add(new Person("王壮",12));
		persons.add(new Person("孙壮",42));
		
		
		Iterator< Person>iterator=persons.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
		Collections.sort(persons, new Comparator<Person>() {
			
			@Override
			public int compare(Person o1, Person o2) {
				// TODO Auto-generated method stub
				return o1.getAge().compareTo(o2.getAge());
			}
		});
		
		Iterator< Person>iterator2=persons.iterator();
		while(iterator2.hasNext()){
			System.out.println(iterator2.next());
		}
	}
	
	
}




/**
 * 功能：这个类时为测试List准备的
 * @author Zhang
 *
 */
class Person {
	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
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
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

}
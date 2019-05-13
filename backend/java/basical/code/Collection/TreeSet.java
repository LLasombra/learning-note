package basical;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class _TreeSet {

	@Test
	/**
	 * HashSet:无序，不可重复
	 *
	 * 结果：	随意的(无序)
	 */
	public void TestHashSet() {
		Set<Person> persons = new HashSet<>();

		persons.add(new Person("AA", 10));
		persons.add(new Person("BB", 11));
		persons.add(new Person("CC", 12));
		persons.add(new Person("DD", 13));
		persons.add(new Person("EE", 14));

		Iterator<Person> it = persons.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());

		}

	}

	@Test
	/**
	 * LinkedHashSet:有序，不可重复
	 *
	 * 结果：	Person [name=AA, age=10]
	 *		Person [name=BB, age=11]
	 *		Person [name=EE, age=14]
	 *		Person [name=CC, age=12]
	 *		Person [name=DD, age=13]
	 */
	public void TestLinkedHashSet() {
		Set<Person> persons = new LinkedHashSet<>();

		persons.add(new Person("AA", 10));
		persons.add(new Person("BB", 11));
		persons.add(new Person("EE", 14));
		persons.add(new Person("CC", 12));
		persons.add(new Person("DD", 13));

		Iterator<Person> it = persons.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());

		}
	}


	@Test
	/**
	 * 说明：这里Person类没有实现Comparable接口中的CompareTo方法
	 * TreeSet:必须实现comparator接口来定制对象(必须实现CompareTo或者是带有CompareTo方法的类型)有序，不可重复
	 *	结果：
	 *		Person [name=AA, age=10]
	 *		Person [name=BB, age=11]
	 *		Person [name=CC, age=12]
	 *		Person [name=DD, age=13]3
	 *		Person [name=EE, age=14]
	 */
	public void TestTreeSet() {

//		Comparator<Person> comparator = new Comparator<Person>() {
//
//			@Override
//			public int compare(Person o1, Person o2) {
//				// 按照age比较
//				return o1.compareTo(o2);
//			}
//		};


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

	@Test
	/**
	 * 说明：这里Person2类没有实现CompareTo方法
	 * TreeSet2:必须实现comparator接口来定制对象(必须实现CompareTo或者是带有CompareTo方法的类型)有序，不可重复
	 *	结果：
	 *		Person [name=AA, age=10]
	 *		Person [name=BB, age=11]
	 *		Person [name=CC, age=12]
	 *		Person [name=DD, age=13]
	 *		Person [name=EE, age=14]
	 */
	public void TestTreeSet2() {

		Comparator<Person2> comparator = new Comparator<Person2>() {

			@Override
			public int compare(Person2 o1, Person2 o2) {
				// 按照age比较
				return (o1.getAge()).compareTo(o2.getAge());
			}
		};


		Set<Person2> persons = new TreeSet<>(comparator);

		persons.add(new Person2("AA", 10));
		persons.add(new Person2("DD", 13));
		persons.add(new Person2("EE", 14));
		persons.add(new Person2("BB", 11));
		persons.add(new Person2("CC", 12));


		Iterator<Person2> it = persons.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());

		}
	}

}

class Person implements Comparable<Person> {
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
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return (this.age).compareTo(o.age);
	}

}

class Person2 {
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

}

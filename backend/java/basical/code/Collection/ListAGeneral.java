package basical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * 功能：这里主要是想说明
 * 		1.	//ERROR:List<Object> objlist不是List<String> strlist的父类
 *			List<Object> objlist=strlist;;//ERROR:
 *		//这里是面向过程编程，不是面向对象编程
 *		2.	public  static void printPersonZInfo2(List<? extends PersonZ>PersonZs) {
 *				for(PersonZ PersonZ:PersonZs){
 *					System.out.println(PersonZ);
 *				}
 *			}
 *		//这里要是转换成面向对象：就等价于子类重写父类的方法，并且可以通过父类.方法名来调用
 *
 */
public class List_General {
	public static void main(String[] args) {
		List<String> strlist=Arrays.asList("AA","BB");System.out.println(strlist);
		//ERROR:List<Object> objlist不是List<String> strlist的父类
		//List<Object> objlist=strlist;//ERROR:


		//test List<PersonZ>
		List<PersonZ>pers=new ArrayList<>();
		pers.add(new PersonZ(20,"zhang"));
		pers.add(new PersonZ(22,"lug"));
		pers.add(new PersonZ(40,"1465"));
		//printPersonZInfo(pers);

		//test List<Student>
		List<Student>stus=new ArrayList<>();
		stus.add(new Student(20,"zhang","hazgu"));
		stus.add(new Student(22,"lug","sefrg"));
		stus.add(new Student(40,"1465","dwefgr"));
		//printPersonZInfo(stus);  //ERROR
		printPersonZInfo2(stus);

	}


	public  static void printPersonZInfo(List<PersonZ>PersonZs) {
		for(PersonZ PersonZ:PersonZs){
			System.out.println(PersonZ);
		}
	}
	/**
	 *List<? extends PersonZ>PersonZs：存在通配符時，寫入是非法的
	 * @param PersonZs
	 */
	public  static void printPersonZInfo2(List<? extends PersonZ>PersonZs) {
//		PersonZs.add(new Student(12, "s", "23"));//  error
		for(PersonZ PersonZ:PersonZs){
			System.out.println(PersonZ);
		}
	}

	/**
	 * 在這個方法中，传入任何数据都是错误的，除了null
	 * @param collection
	 */
	public void printCollection(Collection<?>collection) {
		collection.add(null);//在這個方法中，传入任何数据都是错误的，除了null

	}
}




class Student extends PersonZ{
	private String school;


	public Student(int age,String name,String school) {
		super(age,name);
		this.school = school;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Override
	public String toString() {

		return "Student	 [age=" + super.getAge() + ", nanme=" + super.getNanme() + " [school=" + school + "]";
	}


}

class PersonZ{
	private int age;
	private String nanme;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getNanme() {
		return nanme;
	}
	public void setNanme(String nanme) {
		this.nanme = nanme;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((nanme == null) ? 0 : nanme.hashCode());
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
		PersonZ other = (PersonZ) obj;
		if (age != other.age)
			return false;
		if (nanme == null) {
			if (other.nanme != null)
				return false;
		} else if (!nanme.equals(other.nanme))
			return false;
		return true;
	}
	public PersonZ() {
		// TODO Auto-generated constructor stub
	}
	public PersonZ(int age, String nanme) {
		this.age = age;
		this.nanme = nanme;
	}
	@Override
	public String toString() {
		return "PersonZ [age=" + age + ", nanme=" + nanme + "]";
	}

}

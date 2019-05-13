package basical;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;

import test.ReflectionUtils;




@SuppressWarnings({"unchecked","unused"})
/**
 *功能：测试Class反射类：Method、field、constructor、annotation
 *	1. 特性：
 *		*对象照镜子后可以得到的信息:Method、field、constructor、annotation、interface
 *		*Class 对象只能由系统建立对象
 *	2.NewInstance：
 *		Person person=clazz3.newInstance();//实际上就是调用那个无参构造器。
 *	3.ClassLoader:
 *		//??1.获取一个系统的类加载器
 *		//2.获取系统列加载器的父类：扩展类加载器
 *		//3.获取扩展类加载器的父类：引导类加载器
 *		//??4.测试当前类由哪个类加载器加载
 *		//5.测试JDK中String类是由哪个类加载器加载
 *		//6.关于类加载器的一个主要方法：读取当前工程下的.properties的输入流:this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
 *	4.Method相关：父类私有的问题
 *	5.Field相关：父类私有的问题
 *	6.Constructor相关：一个重要方法：获取当前工程下的流资源
 *	7.Annotation相关：
 *	8.泛型相关：
 *		
 */
public class _Class {
	
	
	@Test
	/**
	 * 功能：获取Class对象的三种方法：
	 * 		1.Person.class
	 * 		2.person.getClass()
	 * 		3.Class.forName("basical.Person")
	 */
	public void testClass() throws Exception {
		//1.获取Class对象：3方法
		Class< Person>clazz=Person.class;
		Class<Person>clazz2=(Class<Person>) (new Person("123",12)).getClass();
		Class<Person>clazz3=(Class<Person>) Class.forName("basical.Person");
		System.out.println(clazz3);    	//class basical.Person
	}
	
	
	@Test
	/**
	 * 功能：测试Class类的NewInstance()方法
	 * 			//实际上就是调用那个无参构造器。
	 */
	public void testNewInstance() throws Exception {
		Class<Person>clazz3=(Class<Person>) Class.forName("basical.Person");
		Person person=clazz3.newInstance();//实际上就是调用那个无参构造器。
		System.out.println(person);	//Person [name=null, age=null]
	}

	
	@Test
	/**
	 * 功能：测试类加载器:
	 * 	带??的是一样的。
	 */
	public void testClassLoader() throws Exception{
		//??1.获取一个系统的类加载器
		ClassLoader classLoader=ClassLoader.getSystemClassLoader();
		System.out.println(classLoader);	//sun.misc.Launcher$AppClassLoader@456d3d51
		//2.获取系统列加载器的父类：扩展类加载器
		ClassLoader classLoader2=classLoader.getParent();
		System.out.println(classLoader2);	//sun.misc.Launcher$ExtClassLoader@6d4b473
		//3.获取扩展类加载器的父类：引导类加载器
		ClassLoader classLoader3=classLoader2.getParent();	
		System.out.println(classLoader3);	//获取不到，返回null
		//??
		ClassLoader classLoader6=this.getClass().getClassLoader();
		System.out.println(classLoader6);
		
		//??4.测试当前类由哪个类加载器加载
		Class <Person>clazz=(Class<Person>) Class.forName("basical._Class");
		ClassLoader classLoader4=clazz.getClassLoader();
		System.out.println(classLoader4); 	//sun.misc.Launcher$AppClassLoader@456d3d51：和上一个系统类加载器一样，说明是由系统类加载器加载
		
		//5.测试JDK中String类是由哪个类加载器加载
		ClassLoader classLoader5=String.class.getClassLoader();
		System.out.println(classLoader5);	//null,说明由引导类加载器加载
		
		//6.关于类加载器的一个主要方法：读取当前工程下的.properties的输入流:this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
//		InputStream inputStream=new FileInputStream("./src/jdbc.properties");
		InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties=new Properties();
		properties.load(inputStream);
		System.out.println(properties.getProperty("root"));
		
	}

	
	@Test
	/**
	 * 功能：测试Method的相关：
	 * 	4.获取对象实例的方法：Method
			4.1得到Class所对应的类中有哪些方法，不能获取private方法
				Method []methods=claz.getMethods();
			4.2获取所有的方法，包括private方法，但只获取当前类声明的方法：
				Method []methods=claz.getDeclaredMethods();
				4.2.2获取指定的方法：
					Method method=claz.getDeclaredMethod("setName");		//无参
					Method method=claz.getDeclaredMethod("setName",String.class);		//获取的是带有String类型参数的setName方法
					Method method=claz.getDeclaredMethod("setName",String.class,Integer.class);		//获取的是带有String类型和Integer类型参数的setName方法
			4.3获取当前类室友那个类加载器加载的：
				ClassLoader classLoader=Class.forName("test._String").getClassLoader();
			4.4获取扩展加载器的父类加载器：
				classLoader=classLoader.getParent();
			4.5测试JDK提供的Object类是由那个类加载器加载的：
				ClassLoader classLoader=Class.forName("java.lang.Object").getClassLoader();
				4.5.2获取一个系统类加载器：
					ClassLoader classLoader=ClassLoader.getSystemClassLoader();
				
			4.6关于类加载器的一个主要方法：调用getResourceAsStream获取类路径下的文件对应的输出流
				InputStream in=person.getClass().getClassLoader().getResourceAsStream(URL);
				
			4.1补：Method:对应类中的方法：
			1.获取Method：
				1.1获取类的方法数组：claz.getDeclaredMethods()
				1.2获取类的指定方法：getDeclaredMethod(String name,Class<?>...parameterTypes)
					说明：	name:方法名
							parameterTypes：参数类型列表(类描述)
					例：
						Method method=claz.getDeclaredMethod("setName");		//无参
						Method method=claz.getDeclaredMethod("setName",String.class);		//获取的是带有String类型参数的setName方法
						Method method=claz.getDeclaredMethod("setName",String.class,Integer.class);		//获取的是带有String类型和Integer类型参数的setName方法
				1.3通过method对象执行方法：
					public Object invoke(Object obj,String MethodName,Object...args){//自定义
						//1.获取参数列表
						Class[]parameterTypes=new Class[args.length];
						for(int i=0;i<parameterTypes.length;i++){
							parameterTypes[i]=args[i].getClass();
						}
						
						try {
							//2.获取方法对象
							Method method=obj.getClass().getDeclaredMethod(MethodName, parameterTypes);
							//3.执行方法
							return method.invoke(obj, args);
						}catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
					
					说明：	obj:执行那个对象的方法
							args:执行方法是所需要传入的参数
					例：
						Object obj=claz.newInstance();
						method.invoke(obj,"张壮壮"，12)；
			
			2.获取当前类的父类：这里通过迭代可以遍历除所有的方法，包括由继承来的
				Class claz=Class.forName("");
				Class superClaz=claz.getSuperclass();
			
			3.若通过反射执行私有方法，则必须加这句话：
				method.setAccessible(true);
	 */
	public void TestMethods() throws Exception{
		Class<Person> clazz=(Class<Person>)Class.forName("basical.Person");
		//1.得到类中的方法：所有的方法,但是获取不了私有方法
		Method []methods=clazz.getMethods();
		testForMethod.printMethods(methods);
		//2.得到类中的方法：获取当前类的所有方法，包括私有方法
		Method []methods2=clazz.getDeclaredMethods();
		testForMethod.printMethods(methods2);
		//3.获取指定的方法
		Method method=clazz.getDeclaredMethod("method1");
		System.out.println(method.getName());	//method1
		Method method2=clazz.getDeclaredMethod("test", String.class,Integer.class);
		System.out.println(method2.getName());
		//4.执行方法
		//创建一个对象
		Person person=clazz.newInstance();
		//若方法是私有的要使其可访问
		method2.setAccessible(true);
		method2.invoke(person,"123",12);
		
		//5.1自己定义一个定义方法：invoke(obj,MethodName,Object...aegs)
		testForMethod.invoke(person,"test","132",12);//把上面的步揍分装成函数
		System.out.println(testForMethod.invoke(person,"getName"));
		//5.2自己定义一个定义方法：invoke(String className,String methodName, Object...args)
		testForMethod.invoke("basical.Person","test","132",12);
		System.out.println(testForMethod.invoke("basical.Person","getName"));
		
		//5.3自己定义一个定义方法，使其父类私有方法也可以调用：invoke2(String className,String methodName, Object...args)
		Object object=testForMethod.invoke2("java.text.SimpleDateFormat", "format", new Date());
		System.out.println(object);			//18-5-31 下午6:24
		
		testForMethod.invoke2("basical.Student", "S_test", "张壮壮",12);
	}
	
	
	@Test
	/**
	 * 5.获取对象实例的属性：Field:封装信息
	 *	5.1获取字段：该字段可能是私有的，也可能是父类的
	 *		5.1.1 Field []fields=claz.getDeclaredFields();
	 *		5.1.2 Field field=claz.getDeclaredField("age");
	 *		5.1.3  父类的字段：
	 *			public  static Field _getField(Class<?> clazz,String fieldName) {
					//这里通过循环
					for(;clazz!=Object.class;clazz=(Class<?>) clazz.getSuperclass()){
						try {
							return clazz.getDeclaredField(fieldName);
						} catch (Exception e){
						//				e.printStackTrace();
						}
					}
					
					return null;
				}
	 *	5.2获取指定对象的指定字段的值。
	 *		public Object get(Object obj)
	 *		说明： 	obj为字段 所在的对象？？？？
	 *		Field field=claz.getDeclaredField("age");
	 *		Object val=field.get(person);
	 *	5.3设置指定字段的指定值：
	 *		public void set (Object obj,Object value)
	 *		说明：	obj为字段所在的对象
	 *				value:要设置的值
	 *		field.set(person,"张壮壮");
	 *	5.4若该字段为私有，则需要调用setAccessible(true)方法
	 */
	public void testFields() throws Exception{
		Class<?>clazz=(Class<?>)Class.forName("basical.Student");
		Object obj=clazz.newInstance();		//创建一个对象实例
		//1.获取字段数组：
		Field []fields=clazz.getDeclaredFields();
		testForField.printFields(fields);		//school
		Field []fields2=clazz.getFields();		//null
		
		//2.获取指定的field
		Field field=clazz.getDeclaredField("school");
		System.out.println(field.getName());	//school
		
		//2.2获取的字段是弗雷斯有的
		Field field2=testForField._getField(clazz, "name");
		System.out.println(field2);
		System.out.println("----------------------------------------");
		//注意私有属性问题
		field.setAccessible(true);
		
		//3.获取指定的field得值：
		Object name=field.get(obj);		//获取name的值
		System.out.println(name);
		
		//4.为指定属性赋值：
		field.set(obj, "连顺");
		System.out.println(field.get(obj));
		
	}

	
	@Test
	/**
	 * 7.获取对象实例的构造器：Constructor
		Class<Person>claz=(Class<Person>)Class.forName(className);
		7.1获取构造器对象：
			Constructor<Person> constructors=claz.getConstructors();
		7.2获取指定的构造器：
			Constructor<Person>constructor=claz.getConstructor(String.class,Integer.class);
		7.3调用构造器的newInstance()方法创建对象：
			Object obj=constructor.newInstance("张壮壮"，12)；
	 */
	public void testConstructor() throws Exception{
		Class<?>clazz=Class.forName("basical.Person");
		//1.获取Constructors数组
		Constructor<?>[]constructors=clazz.getConstructors();
		for(Constructor<?>c:constructors){
			System.out.println(c);
		}
		
		//2.获取指定参数的Constructor:
		Constructor<?>constructor=clazz.getConstructor(String.class,int.class);
		System.out.println(constructor);
		
		//3.调用构造器的newInstance()方法创建对象：
		Object obj=constructor.newInstance("张壮壮",12);
		System.out.println(obj);
	}


	@Test
	/**
	 * 功能：对setAge()方法进行限制：min=18,max=100,超出这个范围都会抛出异常
	 * 	1.声明注解：
	 *	 		@Retention(RetentionPolicy.RUNTIME)
				@Target(value={ElementType.METHOD})
				public @interface AgeValidator {
					public int min();
					public int max();
				}
		2.对相应的方法添加注解：
				@AgeValidator(min=18,max=100)
				public void setAge(int age) {
					this.age = age;
				}
		3.调用注解的属性进行判断：
			*3.1 创建一个对象实例，得到带有Annotation的方法：
			*3.2 得到该方法的Annotation对象：
			*3.3 使用注解：强制转换;和属性一样
	 */
	public void testAnnotation() throws Exception{
		Class<?>clazz=Class.forName("basical.Person");
		Object obj=clazz.newInstance();
		//1.创建一个对象实例，得到带有Annotation的方法：
		Method method=testForMethod._getMethod(clazz, "setAge", int.class);
		//2.得到Annotation对象：
		Annotation annotation=method.getAnnotation(AgeValidator.class);
		//3.判断Annotation对象的元素：满足条件或不满足条件
		
		int val=30; 		//要设置给age的值
		if(annotation!=null&&annotation instanceof AgeValidator){
			AgeValidator ageValidator=(AgeValidator) annotation;
			if(val>ageValidator.min()&&val<ageValidator.max()){
				method.invoke(obj, val);
			}else
				throw new Exception("年龄非法！");
		}
		System.out.println(((Person) obj).getAge());		//30
	}


	@Test
	/**
	 * 功能：测试泛型：非常重要
	 * 	1.获取泛型参数的类型：在Dao的Constructor中
	 */
	public void testGeneral() {
		PersonDao dao=new PersonDao();
		Person entity=new Person();
		dao.save(entity);
		Person person=dao.get(1);
	}


	
	@Test
	/**
	 * 功能：测试getSupperClassGenericType(clazz,)
	 * @throws Exception
	 */
	public void testGeneral2()throws Exception {
		Class<?> clazz=Class.forName("basical.PersonDao");
		Class<?> argsClass=testForGeneral.getSupperClassGenericType(clazz, 0);
		System.out.println(argsClass);
		Class<?> argsClass2=ReflectionUtils.getSupperClassGenericType(clazz, 1);
		System.out.println(argsClass2);
	}

//
//	public Class getSupperClassGenericType(Class<?> clazz, int index) {
//		//1.获取带泛型父类的Class对象
//		Type type=clazz.getGenericSuperclass();
//		if(type instanceof ParameterizedType){
//			//2.强制转换，获取实际参数数组
//			ParameterizedType parameterizedType=(ParameterizedType) type;
//			Type []args=parameterizedType.getActualTypeArguments();
//			if(args!=null&args.length>0){
//				return (Class<?>) args[index];
//			}
//		}
//		return null;
//	}
	
}












/**
 * 功能：这个类是为了测试testGeneral2()方法准备的
 *
 */
class testForGeneral{
	
	/**
	 * 功能：通过反射，获取定义Class时声明的父类的泛型参数的类型
	 * 如：public EmployeeDao extends BaseDao<Employee,String>
	 * @param clazz:子类对应的Class对象
	 * @param index:子类继承父类时传入的泛型索引，从0开始
	 */
	public static Class<?> getSupperClassGenericType(Class<?> clazz,int index) {
		//1.获取带泛型父类的Class对象
		Type type=clazz.getGenericSuperclass();
		if(type instanceof ParameterizedType){
			//2.强制转换，获取实际参数数组
			ParameterizedType parameterizedType=(ParameterizedType) type;
			Type []args=parameterizedType.getActualTypeArguments();
			if(args!=null&args.length>0){
				return (Class<?>) args[index];
			}
		}
		return null;
	}
	
}



/**
 * 功能：这个类是为了测试TestFileds()方法准备的
 *
 */
class testForField{
	/**
	 * 功能：打印Methods中的所有方法
	 * @param methods
	 */
	public  static void printFields(Field []fields) {
		for(Field m:fields){
			System.out.println(m.getName());
		}
		System.out.println();
	}
	/**
	 * 功能：获取FieldName方法，该方法可能是私有的，也可能是父类的(私有)方法
	 * @param clazz
	 * @param className
	 * @param paramerTypes
	 */
	public  static Field _getField(Class<?> clazz,String fieldName) {
		//这里通过循环
		for(;clazz!=Object.class;clazz=(Class<?>) clazz.getSuperclass()){
			try {
				return clazz.getDeclaredField(fieldName);
			} catch (Exception e){
//				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
}



/**
 * 功能：这个类是为了测试TestMethods()方法准备的
 *
 */
class testForMethod{
	
	/**
	 * 自己定义的执行对象方法的方法1:invoke2(String className,String methodName, Object...args)
	 * @param obj:调用方法所在的对象
	 * @param methodName：方法名，该方法可能是私有方法，也可能是父类的(私有)方法
	 * @param args：方法所需的参数
	 */
	public static  Object invoke2(String className,String methodName, Object...args){
		try {
			//1.获取这个方法
			Class<?>clazz=(Class<?>) Class.forName(className);
			Class<?>[]paramerTypes=(Class<?>[])new Class[args.length];
			for(int i=0;i<args.length;i++){
				paramerTypes[i]=(Class<?>) args[i].getClass();
			}
			Method method=_getMethod(clazz, methodName, paramerTypes);
			//2.创建一个对象实例：
			Object obj=clazz.newInstance();
			//3.使私有化的方法可访问
			method.setAccessible(true);
			return method.invoke(obj, args);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 功能：获取methodName方法，该方法可能是私有的，也可能是父类的(私有)方法
	 * @param clazz
	 * @param className
	 * @param paramerTypes
	 */
	public  static Method _getMethod(Class<?> clazz,String methodName,Class<?>...paramerTypes) {
		//这里通过循环
		for(;clazz!=Object.class;clazz=(Class<?>) clazz.getSuperclass()){
			try {
				Method method=clazz.getDeclaredMethod(methodName, paramerTypes);
				return method;
			} catch (Exception e){
//				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	/**
	 * 自己定义的执行对象方法的方法1:invoke(Object obj,String methodName, Object...args)
	 * @param obj:调用方法所在的对象
	 * @param methodName：方法名，该方法可能是私有方法；
	 * @param args：方法所需的参数
	 */
	public static Object invoke(Object obj,String methodName, Object...args) throws Exception {
			//1.找到这个方法
			Class<?>clazz=(Class<?>) obj.getClass();
			Class<?> []parameterTypes=new Class[args.length];
			for(int i=0;i<args.length;i++){
				parameterTypes[i]=(Class<?>) args[i].getClass();
			}
//			Method method=clazz.getDeclaredMethod(methodName, parameterTypes);
			Method method=_getMethod(clazz, methodName, parameterTypes);
			//2.执行方法
			method.setAccessible(true);
			return method.invoke(obj, args);
	}

	
	/**
	 * 自己定义的执行对象方法的方法1:invoke(Object obj,String methodName, Object...args)
	 * @param className:全类名
	 * @param methodName：方法名，该方法可能是私有方法；
	 * @param args：方法所需的参数
	 */
	public static  Object invoke(String className,String methodName, Object...args){
		try {
			//1.获取对赢得方法
			Class<?>clazz=(Class<?>) Class.forName(className);
			Class<?>[]parameterTypes=(Class<?>[]) new Class[args.length];
			for(int i=0;i<args.length;i++){
				parameterTypes[i]=(Class<?>) args[i].getClass();
			}
			Method method=clazz.getDeclaredMethod(methodName, parameterTypes);
			//2.创建一个对象实例
			Object obj=clazz.newInstance();
			method.setAccessible(true);
			return method.invoke(obj, args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能：打印Methods中的所有方法
	 * @param methods
	 */
	public  static void printMethods(Method []methods) {
		for(Method m:methods){
			System.out.println(m.getName());
		}
		System.out.println();
	}

}



@SuppressWarnings("unused")
/**
 * 功能：这个类时为测试Class准备的
 * @author Zhang
 *
 */
class Person {
	private String name;
	private Integer age;

	private void method1() {
		System.out.println("bu 带参数的方法！");
	}
	
	private void test(String name,Integer i) {
		System.out.println(name+"带参数的方法！"+i);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}
	
	@AgeValidator(min=18,max=100)
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
		this.name="张壮壮";
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

}

@SuppressWarnings("unused")
class Student extends Person{
	private String school="南通大学";
	private void S_test(String name ,Integer i) {
		System.out.println("这是父类中的私有方法");
	}
}
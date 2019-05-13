package basical;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *构造器的调用顺序：先调用匪类的构造器，再调用子类的构造器 
 */
public class Dao<T,PK> {
	private Class<T> clazz;
	
	
	/**
	 * 功能：Dao的Constructor：
	 * 	1.得到泛型参数T：
	 * 		1.1 获取带有泛型的类的Class对象:这里必须是有子类的(确定了T的类型)，因为不可以获取本身的泛型：没有意义
	 * 		1.2 想办法获取泛型参数：Type type=this.getClass().getGenericSuperclass();
	 * 			1.2.1 若是带参数的type,进行强制转换：ParameterizedType parameterizedType=(ParameterizedType) type;
	 * 			1.2.2 获取实际参数数组的第一个元素，为泛型T的类型
	 */
	@SuppressWarnings("unchecked")
	public Dao() {
//		System.out.println("DAO");
		//构造器的调用顺序：先调用匪类的构造器，再调用子类的构造器 
		System.out.println(this);  			//basical.PersonDao@315c9cb8,子类调用的父类构造器
		//1.获取Dao子类的父类：就是获取Dao本身
		this.getClass().getSuperclass();
		//2.获取带参数的Dao子类的父类：就是获取Dao<T>本身
		Type type=this.getClass().getGenericSuperclass();		//Type类型的
		//3.想办法获取参数T
		if(type instanceof ParameterizedType){
			//3.1若是带参数的type,进行强制转换
			ParameterizedType parameterizedType=(ParameterizedType) type;
			//3.2获取实际参数数组
			Type []args=parameterizedType.getActualTypeArguments();
			
			if(args!=null&&args.length>0){
				//3.3若是其中args不为null,则获取第一个参数(是Class<T>),并将其付给clazz变量
				clazz=(Class<T>) args[0];
			}
		}
	}
	public T get(Integer index){
		System.out.println(clazz);
		return null;
	}
	
	public void save(T entity){
		
	}
}

package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.junit.Test;

import basical.ArithmeticCaculator;
import basical.ArithmeticCaculatorImpl;
import basical.Person;
import basical.Service;
import basical.ServiceImpl;


/**
 * 功能：测试动态代理：
 * 	1. Notice:提供具体实现类：ServiceImpl	(必须要在方法中自定义异常)	//如果在方法中不自定义异常，，代理调用方法时永远都不会出异常；必须要在方法中自定义异常
 *	2.具体过程：testProxy2很详细
 *		
 */
public class ProxyTest {
	@Test
	/**
	 * 1.定义一个Service接口
	 * 	定义方法如下：
	 * 	
	 * 		addNew(Person person);
	 * 		delete(Integer id);
	 * 		update(Person person);
	 * 
	 * 2.提供具体实现类：ServiceImpl	(必须要在方法中自定义异常)
	 * 		//如果在方法中不自定义异常，，代理调用方法时永远都不会出异常；必须要在方法中自定义异常
	 * 
	 * 3.使用动态代理实现事务操作
	 * 		3.1再具体定义每个Service方法前，都打印：开始事务
	 * 		3.2方法正常结束，都打印：事务提交
	 * 		3.3在定义方法目标出现异常情况下：打印：回滚事务
	 */
	public void testPersonService() {
		final Service target=new ServiceImpl();
		Service proxy=(Service) Proxy.newProxyInstance(Service.class.getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						Object obj = null;
						if (args == null) {

							try {
								obj = method.invoke(target);
							} catch (Exception e1) {
							}
							return obj;
						} else {
							try {
								// 3.1再具体定义每个Service方法前，都打印：开始事务
								System.out.println("开始事务");
								obj = method.invoke(target, args);//如果在方法中不自定义异常，，这里永远都不会出异常；必须要在方法中自定义异常
								// 3.2方法正常结束，都打印：事务提交
								System.out.println("事务提交");
							} catch (Exception e) {
								// 3.3在定义方法目标出现异常情况下：打印：回滚事务
								System.out.println("事务回滚");
//								e.printStackTrace();
							}
							return obj;
						}
					}
				});
		//增加
		proxy.addNew(new Person("连顺",1003));
		proxy.printPersons();//这里args为空，会出现空指针异常，已经忽略
		//删除
		proxy.delete(1002);	//ok
		proxy.delete(1006);//回滚事务
		proxy.printPersons();
		//修改
		proxy.update(new Person("张壮壮",1005));
		//查询
		proxy.printPersons();
		
	}
	
	
	
	@Test
	/**
	 * 功能：使用代理调用方法，并加上了一些限制(Annotation)
	 * 	1.创建一个被代理对象final类型:为执行方法的提供对象(局部变量);因为被代理对象在代理对象proxy还在使用的时候，就会被当做垃圾回收了。
	 * 	2.创建代理对象proxy：3个参数 Proxy.newProxyInstance(ClassLoader,Class<?>[],InvocationHandler)
	 * 		2.1类加载器：一般就是被代理对象的类加载器(这里由于存在多态，所以最好用被代理对象实现的接口的类加载器)
	 * 		2.2获取被代理对象实现的接口的Class数组:
	 * 			2.2.1new Class[]{ ArithmeticCaculator.class}
	 * 			2.2.2若代理对象不需要实现被代理对象实现的意外的接口方法【不需要代理对象去实现接口的方法】，可以使用：target.getClass().getInterfaces()
	 * 		2.3创建InvocationHandler对象(通常使用匿名内部类的方式):1个方法有3个参数		invoke(Object proxy, Method method,Object[] args){method.invoke(obj,args)}
	 *			2.3.1proxy：//正在被返回的代理对象，一般不会使用   class com.sun.proxy.$Proxy4
	 *			2.3.2method：正在被调用的方法
	 *			2.3.3args:调用方法时传入的参数
	 *	3.调用代理对象执行方法
	 *		proxy.methdName(Objrct...args);
	 *
	 *	Notice:提供具体实现类：ServiceImpl	(必须要在方法中自定义异常)
	 * 		//如果在方法中不自定义异常，，代理调用方法时永远都不会出异常；必须要在方法中自定义异常
	 */

	public void testProxy2() {
		final ArithmeticCaculator target=new ArithmeticCaculatorImpl();
		ArithmeticCaculator proxy=(ArithmeticCaculator) Proxy.newProxyInstance(ArithmeticCaculator.class.getClassLoader(),
				target.getClass().getInterfaces(),new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						//在这里调用方法
						return method.invoke(target, args);
					}
				});
		proxy.add(1, 2);
	}
	
	@Test
	/**	
	 * 功能：实现了可以日志。
	 * 	1.创建代理对象的返回类型一般是被代理对象实现的接口类型：这里就一般是ArithmeticCaculator类型
	 * 
	 * 	2.说明3各参数：
	 * 		2.1.ClassLoader :动态代理产生的对象(arithmeticCaculator)是由那个类加载器加载的：通常是和被代理对象使用一样的类加载器
	 * 		2.2.Class<T> :动态代理产生的对象(arithmeticCaculator)需要实现的接口的Class数组:被代理对象实现的接口
	 * 		2.3.InvocationHandler:当具体调用代理方法时，将产生的行为
	 */
	public void testProxy() {
		final ArithmeticCaculator arithmeticCaculator =new ArithmeticCaculatorImpl();
		//1.创建代理对象：返回类型一般是被代理对象实现的接口类型；但是必须是接口
		ArithmeticCaculator proxy=	(ArithmeticCaculator) Proxy
				.newProxyInstance(
						//2.获取类加载器：一般是被代理对象的类加载器(这里由于存在多态，所以最好用被代理对象实现的接口的类加载器)
						ArithmeticCaculator.class.getClassLoader(),
						//3.获取被代理对象实现的接口的Class数组:target.getClass().getInterfaces()
						new Class<?>[] { ArithmeticCaculator.class },
						//4.创建InvocationHandler对象(通常使用匿名内部类的方式)，在其中实现行为invoke(Object proxy, Method method,Object[] args){method.invoke(obj,args)}
						new InvocationHandler() {
							@Override
							/**
							 * 说明3个参数：
							 * 	1.proxy
							 * 	2.method：正在被调用的方法
							 * 	3.args:调用方法时传入的参数
							 */
							public Object invoke(Object proxy, Method method,
									Object[] args) throws Throwable {
//								//打印method
//								System.out.println(method);
//								//打印args:
//								System.out.println(Arrays.asList(args));
//								//打印proxy
								System.out.println(proxy.getClass());		//class com.sun.proxy.$Proxy4
								
								//写日志
								System.out.println("The method "+method.getName()+" start with "+Arrays.asList(args));
								
								//调用被代理类的方法
								Object obj=method.invoke(arithmeticCaculator, args);
								
								//写日志
								System.out.println("The method "+method.getName()+" ends with "+obj );
								return obj;
							}
						});
		//5.调用代理对象执行方法
		System.out.println(proxy.add(1, 2));
		proxy.mul(1, 2);
	}
}


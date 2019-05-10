package test;

import org.junit.Test;

import basical.Define_Enumration;
import basical.Define_Enumration2;

public class Traversal {
	
	@Test
	/**
	 * 1.枚举类遍历values/Class[claz.getEnumConstants()]
	 * 2.获取每一个枚举值：valueOf
	 * 	Define_Enumration.valueOf(Define_Enumration.class,"SPRING");
	 * 	Define_Enumration.valueOf("SPRING");
	 * 3.枚举类的名称、索引、
	 * 		
	 */
	public void test_Traversal_Enumeration() {
		System.out.println("第一种通过枚举静态方法values()：");  
		for (Define_Enumration sea :Define_Enumration.values()){
			System.out.println(sea);
		}
		
		System.out.println("第二种通过反射：.getEnumConstants");
		Class<Define_Enumration> claz=Define_Enumration.class;
		for(Define_Enumration sea : claz.getEnumConstants()){
			System.out.println(sea);
		}
		
		System.out.println("查询枚举类的某一个属性：.valueOf(Define_Enumration.class,'SPRING')");
		Define_Enumration s=Define_Enumration.valueOf(Define_Enumration.class,"SPRING");
		System.out.println(s);    //打印重写的toString方法:Sea_Enumeration [desc=春天, name=345]
		
		System.out.println(Define_Enumration.FALL.getName());
		
		
		//测试Define_Enumration2中的getInfo()方法
		System.out.println(Define_Enumration2.FALL.getInfo());
		System.out.println("Define_Enumration2的第二种通过反射：.getEnumConstants");
		Class<Define_Enumration2> claz2=Define_Enumration2.class;
		for(Define_Enumration2 sea : claz2.getEnumConstants()){
			System.out.println(sea.getInfo());
		}
		
		Define_Enumration2 define_Enumration2=Define_Enumration2.FALL;
		String name=define_Enumration2.name();
		int index=define_Enumration2.ordinal();
		System.out.println(name+"  "+index);
		
		//valueOf
//		Define_Enumration s=Define_Enumration.valueOf(Define_Enumration.class,"SPRING");
		Define_Enumration2 s1=Define_Enumration2.valueOf("SPRING");
		System.out.println(s1);
	}
}

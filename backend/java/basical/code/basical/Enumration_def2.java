package basical;

/**
 *	功能：自定义创建枚举类
 *	测试类：test.Traversal.java
 */

public interface info {
	String getInfo();
}

public enum Define_Enumration2 implements info{

	//1.第一行直接定义枚举常量；等价于创建对象
	SPRING("春天", "345"){//这里也可以在最后重写getInfo()方法，使用Switch一个一个判断;
		@Override
		public String getInfo() {
			return "chu";

		}
	},SUMMer("夏天", "678"){
		@Override
		public String getInfo() {
			return "xia";

		}
	},FALL("秋天", "91011"){
		@Override
		public String getInfo() {
			return "qiu";

		}
	}, WINTER("冬天", "1212"){
		@Override
		public String getInfo() {
			return "dong";

		}
	};
	//2.定义枚举类的变量为final类型变量
	private final String desc;
	private final String name;
	//3.私有化构造器：枚举对象是唯一的
	private Define_Enumration2(String name,String desc) {
		this.desc = desc;
		this.name = name;
	}

	//4.书写getter方法，暴露枚举对象的成员(只读)
	public String getDesc() {
		return desc;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Sea_Enumeration [desc=" + desc + ", name=" + name + "]";
	}

}

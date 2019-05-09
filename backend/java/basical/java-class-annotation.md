## Annotation

- Annotation 定义
  - 使用 @interface 来声明注解
  - 使用接口中方法的方式声明注解的注解: 其中返回值称为属性的类型, 方法名为属性的名称

```java

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明：
 *  1.使用 @interface 来声明注解
 *  2. 使用接口中方法的方式声明注解的注解: 其中返回值称为属性的类型, 方法名为属性的名称
 */
@Target(value={ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)   //元注解
public @interface HelloAnnotation {
    // 使用接口中方法的方式声明注解的注解
    String major();
    int age();
    @Deprecated		//@override	 @SuppressWarnings
    String school() default "南通";
}


import basical.HelloAnnotation;

@HelloAnnotation(age=12,major="Java")
public class TestAnnotation {
    @HelloAnnotation(age=12,major="Java")
    public void test() {
    }
}
```

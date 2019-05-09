## Spring-Boot 的 Annotation 简介:

- **common:**

```java
// 进行JrS303校验 @Email：表示为email格式
@Validated
// 将方法返回值直接写给浏览器
@ResponseBody
// 请求参数映射
@RequestMapping("/sayHello")
// @Controller + @ResponseBody
@RestController
// 将主配置类的所在包以及下面的所有字包里面的所有组件扫描注册到SpringIOC中
@SpringBootApplication
@Autowired 自动装配
@RunWith(SpringRunner.class) + @SpringBootTest ======> spring-boot 测试类
```

---

- **配置类:**

```java
// 配置类，相当于 spring 的配置文件[注入属性之类的]
@Configuration + @Bean
// spring-boot 默认不支持XML文件向IOC容器中注入，可以使用其开启。
@ImportResource(locations = { "classpath:testService.xml" })
```

---

- **属性注入:**

```java
// ----------1--------------
// 从配置文件中读取数据并且装配到这个bean对象
// 将上面的配置文件数据单独成一个文件，并引入, 必须和  @ConfigurationProperties()一起使用
@PropertySource(value = { "classpath:person.properties" })
@ConfigurationProperties(prefix = "person")
public class Person {
 ...
}

// ----------2--------------
@Configuration
public class DruidConfig{

  @ConfigurationProperties(prefix="spring.datasource")
  @Bean
  public DataSource DruidConfig(){
    ...
  }
  ...
}

// ----------3--------------
public calss Person {
   // 从配置文件中读取属性值
   // @Value("#{2*3}")、@Value("${person.last-name}")
   @Value("${person.last-name}")
   private Integer age;
   ....
   // 静态类中注入 xx.properties 文件中的值时要提供 set 方法.
   @Value("${user.api.host}")
   public void setUserApiHost(String host) {
     USER_API_HOST = host;
   }
}

```

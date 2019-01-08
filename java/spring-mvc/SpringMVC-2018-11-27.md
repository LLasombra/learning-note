## spring-boot
  ### spring-mvc 的相关配置文件:
   * *web.xml ===> 找到MVC的配置（servlet）===> mvc.xml ===> scan的包 ===> 在相应的包下去找 Controller ===> 通过MapRequest找到相应的方法，并执行===>视图解析器，转发*

   * **url-pattern的解释:**
	 ```xml
	 <!--
	 会匹配到/login这样的路径型url，不会匹配到模式为*.jsp这样的后缀型url;
	 原因是这会使用<mvc:default-servlet-handler/> 将在 SpringMVC 上下文中定义一个 DefaultServletHttpRequestHandler，只处理经过映射的url,否则直接交给web的默认处理器处理。
	 -->
	 < url-pattern>/</url-pattern>
	 ```
	 ```xml
	 <!--
	 会匹配所有url：路径型的和后缀型的url(包括/login,*.jsp,*.js和*.html等) 
	 -->
	 < url-pattern>/*</url-pattern>
	 ```

  * **处理静态资源:<br/>**
	```
	a) 若将 DispatcherServlet 请求映射配置为 /，则 Spring MVC 将捕获WEB 容器的所有请求，包括静态资源的请求，
		SpringMVC 会将他们当成一个普通请求处理，因找不到对应处理器将导致错误。<br/>
	b) 在 SpringMVC 的配置文件中配置 <mvc:default-servlet-handler/> 的方式解决静态资源的问题：<br/>
		<mvc:default-servlet-handler/> 将在 SpringMVC 上下文中定义一个 DefaultServletHttpRequestHandler，
		它会对进入 DispatcherServlet 的请求进行筛查，如果发现是没有经过映射的请求，就将该请求交由 WEB 应用服务器默认的 Servlet 处理，如果不是静态资源的请求，才由DispatcherServlet 继续处理.<br/>
	c) 一般 WEB 应用服务器默认的 Servlet 的名称都是 default。若所使用的WEB 服务器的默认 Servlet 名称不是 default，
		则需要通过 default-servlet-name 属性显式指定.<br/>
	```

### web.xml相关配置
  * 文件 springmvc 相关【配置 servlet 和 servlet_mapping 】 Spring相关 【 context-param 和 listener 】
	 + **applicationContext.xml :  Spring 配置 Listerner 和 业务逻辑处理事务相关 和 要扫描的包**
	 + **springmvcContext.xml : springmvc 配置要扫描的包 和 视图解析器**

	* **@RequestMapping(value,method,params,heads)解析:**
		```java
		// @RequestMapping(value,method,params,heads) 可以写在类上; 也可以写在方法上;
		// 还可以将 URL 中占位符参数绑定到控制器处理方法的入参中
		@RequestMapping(value="/hello",method=RequestMethod.POST,params={“param1=value1”, “param2”},heads)
			// ?：匹配文件名中的一个字符
			// *：匹配文件名中的任意字符
			// **：** 匹配多层路径

		// @PathVariable("id")：这个是URL中的
		@RequestMapping("/delete/{id}")
		public String delete(@PathVariable("id") int id){
			....
		}

		// @RequestParam 可以把请求参数传递给请求方法 ：这个是URL参数中的[?之后]
		@RequestMapping("/delete")
		public String delete(@RequestParam(value="id",required=false，defaultValve="0") int id){
			....
		}

		// @RequestHeader 即可将请求头中的属性值绑定到处理方法的入参中
		@RequestMapping("/delete")
		public String delete(@@RequestHeader("Accept-Encoding") int id){
			....
		}

		// @CookieValue 可让处理方法入参绑定某个 Cookie 值
		@RequestMapping("/delete")
		public String delete(@@CookieValue(value="sessionId",required=false) int sessionId){
			....
		}

		// 会按请求参数名和 POJO 属性名进行自动匹配，自动为该对象填充属性值。支持级联属性
		// url : /handler?userName=zack&age=1
		@RequestMapping("/handler")
		public String delete(User user){
			....
		  MVC 的 Handler 方法可以接受哪些 ServletAPI 类型的参数
			• HttpServletRequest
			• HttpServletResponse
			• HttpSession
			• java.security.Principal
			• Locale
			• InputStream
			• OutputStream
			• Reader
			• Writer
		}
		```

### REST 风格:
  * Representational State Transfer。（资源）表现层状态转化.
	* 示例:
	```
	– /order/1 HTTP GET ：得到 id = 1 的 order
	– /order/1 HTTP DELETE：删除 id = 1的 order
	– /order/1 HTTP PUT：更新id = 1的 order
	– /order HTTP POST：新增 order
	```

	* **如何发送 PUT 请求和 DELETE 请求呢?**
	```
	1. 需要配置 HiddenHttpMethodFilter ,在context.xml中
	2. 需要发送 POST 请求
	3. 需要在发送 POST 请求时携带一个 name="_method" 的隐藏域, 值为 DELETE 或 PUT
	```
  * **在 SpringMVC 的目标方法中使用 @PathVariable 注解 得到 id**


### 处理模型数据
  * **传入参数为对象的话，会根据 request 的参数封装成这个对象;**
	* **传入参数为 map 时会自动放入 request 域对象中**
	* ***有 @ModelAttribute 标记的方法, 会在每个目标方法执行之前被 SpringMVC 调用!***
		- ModelAndView : 处理方法返回值类型为 ModelAndView 时, 方法体即可通过该对象添加模型数据: 其既包含视图信息，也包含模型数据信息
			+ 添加模型数据:
				- MoelAndView addObject(String attributeName, Object attributeValue)
				- ModelAndView addAllObject(Map<String, ?> modelMap)
			+ 设置视图:
				- void setView(View view)
				- void setViewName(String view

		- Map 及 Model: **ModelMap 或 java.uti.Map 时，处理方法返回时，Map 中的数据会自动添加到模型中.**
			+ Spring MVC 在内部使用了一个org.springframework.ui.Model 接口存储模型数据
			+ **Spring MVC 在调用方法前会创建一个隐含的模型对象作为模型数据的存储容器.**
			+ 如果方法的入参为 Map 或 Model 类型，Spring MVC 会将隐含模型的引用传递给这些入参。
			+ 在方法体内，开发者可以通过这个入参对象访问到模型中的所有数据，也可以向模型中添加新的属性数据
			```java
			@ModelAttribute("user")
			public User getUser (){
				User user = new User()；
				user.setAge(10);
					return user;
			}

			@RequestMapping("/delete")
			public String handle(Map<String, Object> map){
				map.put("time", new Data());
				//这里可以获取到user的值
				User user = (User) map.get("user");
			}
			```

	* @SessionAttributes: **将模型中的某个属性暂存到HttpSession 中，以便多个请求之间可以共享这个属性**
		+ @SessionAttributes 通过属性名添加；模型属性的对象类型添加
		+ @SessionAttributes(types=User.class) 会将隐含模型中所有类型为 User.class [map中的value]的属性添加到会话中。
		+ @SessionAttributes(value={“user1”, “user2”}
		+ @SessionAttributes(types={User.class, Dept.class})
		+ @SessionAttributes(value={“user1”, “user2”}, types={Dept.class})
		```java
		@Controller
		@SessionAttributes("user")
		@RequestMapping("/hel")
		public class HelloWOrld{
			@ModelAttribute("user")
			public User getUser (){
				User user = new User()；
				user.setAge(10);
				return user;
			}

			@RequestMapping("/handler")
			public String delete( @ModelAttribute("user") User user){
				....
			}

			@RequestMapping("/delete")
			public String handle(Map<String, Object> map){
				map.put("time", new Data());
				//这里可以获取到user的值
				User user = (User) map.get("user");
			}
		}
		```


	* **@ModelAttribute: 可以通过方法上添加，也可以在参数前添加； 方法入参标注该注解后, 入参的对象就会放到数据模型中**
		运行流程:
		---
		```
			有 @ModelAttribute() 修饰的方法，从DB中获取对象，===> put,进入 request 中,也放入 implicitModel 中 ===> request 参数中可以封装成对象的数据覆盖[修改]之前put的数据[ implicitModel 中的]===>将改变后的对象当做参数传入，并放入 request中。
			没有 @ModelAttribute() 修饰的方法===> 会通过反射创建一个对象 ===> 将 request 参数封装成对象当做参数传入，并放入 request中。
				* 1. 执行 @ModelAttribute 注解修饰的方法: 从数据库中取出对象, 把对象放入到了 Map 中. 键为: user
				* 2. SpringMVC 从 Map 中取出 User 对象, 并把表单的请求参数赋给该 User 对象的对应属性.
				* 3. SpringMVC 把上述对象传入目标方法的参数.
		```
		---
		```java
		/**
		* 1. 有 @ModelAttribute 标记的方法, 会在每个目标方法执行之前被 SpringMVC 调用!
		* 2. @ModelAttribute 注解也可以来修饰目标方法 POJO 类型的入参, 其 value 属性值有如下的作用:
		* 1). SpringMVC 会使用 value 属性值在 implicitModel 中查找对应的对象, 若存在则会直接传入到目标方法的入参中.
		* 2). SpringMVC 会一 value 为 key, POJO 类型的对象为 value, 存入到 request 中.
		*/
		@ModelAttribute
		public void getUser(@RequestParam(value="id",required=false) Integer id,
				Map<String, Object> map){
			System.out.println("modelAttribute method");
			if(id != null){
				//模拟从数据库中获取对象
				User user = new User(1, "Tom", "123456", "tom@atguigu.com", 12);
				System.out.println("从数据库中获取一个对象: " + user);

				map.put("user", user);
			}
		}

		/**
		* 运行流程:
		* 1. 执行 @ModelAttribute 注解修饰的方法: 从数据库中取出对象, 把对象放入到了 Map 中. 键为: user
		* 2. SpringMVC 从 Map 中取出 User 对象, 并把表单的请求参数赋给该 User 对象的对应属性.
		* 3. SpringMVC 把上述对象传入目标方法的参数.
		*
		* 注意: 在 @ModelAttribute 修饰的方法中, 放入到 Map 时的键需要和目标方法入参类型的第一个字母小写的字符串一致!
		*
		* SpringMVC 确定目标方法 POJO 类型入参的过程

			前置：
				@ModelAttribute 修饰方法，则put方法会向implicitModel中保存值
		* 1. 确定一个 key:
		* 1). 若目标方法的 POJO 类型的参数木有使用 @ModelAttribute 作为修饰, 则 key 为 POJO 类名第一个字母的小写
		* 2). 若使用了  @ModelAttribute 来修饰, 则 key 为 @ModelAttribute 注解的 value 属性值.
		* 2. 在 implicitModel 中查找 key 对应的对象, 若存在, 则作为入参传入
		* 1). 若在 @ModelAttribute 标记的方法中在 Map 中保存过, 且 key 和 1的 确定的 key 一致, 则会获取到. 【这里会将@ModelAttribute 修饰方法中put进去的值，修改为request参数中的值】
		* 3. 若 implicitModel 中不存在 key 对应的对象, 则检查当前的 Handler 是否使用 @SessionAttributes 注解修饰,
		* 若使用了该注解, 且 @SessionAttributes 注解的 value 属性值中包含了 key, 则会从 HttpSession 中来获取 key 所
		* 对应的 value 值, 若存在则直接传入到目标方法的入参中. 若不存在则将抛出异常.
		* 4. 若 Handler 没有标识 @SessionAttributes 注解或 @SessionAttributes 注解的 value 值中不包含 key, 则
		* 会通过反射来创建 POJO 类型的参数, 传入为目标方法的参数
		* 5. SpringMVC 会把 key 和 POJO 类型的对象保存到 implicitModel 中, 进而会保存到 request 中.
		*
		* 源代码分析的流程
		* 1. 调用 @ModelAttribute 注解修饰的方法. 实际上把 @ModelAttribute 方法中 Map 中的数据放在了 implicitModel 中.
		* 2. 解析请求处理器的目标参数, 实际上该目标参数来自于 WebDataBinder 对象的 target 属性
		* 1). 创建 WebDataBinder 对象:
		* ①. 确定 objectName 属性: 若传入的 attrName 属性值为 "", 则 objectName 为类名第一个字母小写.
		* *注意: attrName. 若目标方法的 POJO 属性使用了 @ModelAttribute 来修饰, 则 attrName 值即为 @ModelAttribute
		* 的 value 属性值
		*
		* ②. 确定 target 属性:
		* 	> 在 implicitModel 中查找 attrName 对应的属性值. 若存在, ok
		* 	> *若不存在: 则验证当前 Handler 是否使用了 @SessionAttributes 进行修饰, 若使用了, 则尝试从 Session 中
		* 获取 attrName 所对应的属性值. 若 session 中没有对应的属性值, 则抛出了异常.
		* 	> 若 Handler 没有使用 @SessionAttributes 进行修饰, 或 @SessionAttributes 中没有使用 value 值指定的 key
		* 和 attrName 相匹配, 则通过反射创建了 POJO 对象
		*
		* 2). SpringMVC 把表单的请求参数赋给了 WebDataBinder 的 target 对应的属性.
		* 3). *SpringMVC 会把 WebDataBinder 的 attrName 和 target 给到 implicitModel.
		* 近而传到 request 域对象中.
		* 4). 把 WebDataBinder 的 target 作为参数传递给目标方法的入参.
		*/
		@RequestMapping("/testModelAttribute")
		public String testModelAttribute(User user){
			System.out.println("修改: " + user);
			return SUCCESS;
		}
		```

### 视图 、视图解析器:
  * **string[视图名] ===> 内部装配为 ModelAndView ===> ViewSolver ===> url等 ===> 视图对象渲染**
	* [示例](https://github.com/Alice52/Learning/blob/master/Spring/SpringMVC/Hello_SpringMVC/src/main/resources/springmvc.xml)
	```java
	/**
		* 若想直接响应SpringMvc渲染的页面，可以使用mvc:view-controller
		* <mvc:view-controller path="springMvc/test" view-name="hujingwei">
		*	所以当访问路径"springMvc/test"时，会直接跳转到hujingwei.jsp这个页面。
		*/
	```
	* **详细解析:**
		```xml
		a) 视图的作用:rent方法渲染模型数据，将模型里的数据以某种形式呈现给客户。
				视图解析器的作用: 将逻辑视图[视图名]，转换为物理视图
		b) Spring MVC 内部将返回String，View ，model类型的方法装配成一个ModelAndView 对象，
			借助视图解析器（ViewResolver implement ViewResolver接口）得到最终的视图对象（View）[jsp,Excel ect].
			视图对象由视图解析器负责实例化。由于视图是无状态的，所以他们不会有线程安全的问题;
		c) 视图分类 ：
			URL ：
				InternalResourceView 【默认试图将JSP或其他资源封装成View】
				JstlView : 支持JSTL国际化标签功能
			文档视图：
				AbstractExcelView : Excel文档视图抽象类，基于POI构造Excel文档。
				AbstractPdfView : Excel文档视图抽象类，基于iText构造PDF文档。
			报表视图、JSON视图等

		d) Spring WEB  [*context.xml] 上下文中配置一种或多种解析策略，并指定他们之间的先后顺序[order属性 ： order越小优先级越高].
		e) 视图解析器分类：
			解析为Bean的名字：
				BeanNameViewResolver : 将视图解析为一个Bean，Bean的Id相当于视图名
			解析为URL :
				InternalResourceViewReslover : 将视图名解析为一个URL文件
					<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
						<property name="prefix" value="/WEB-INF/views/"></property>
						<property name="suffix" value=".jsp"></property>
					</bean>
				JasperReportsViewResolver :
			魔板文件视图等
		```

### Spring 的表单标签:
  * *SpringMVC 的表单标签可以实现将模型数据中的属性和 HTML 表单元素相绑定，以实现表单数据更便捷编辑和表单值的回显*
	* **[GET把参数包含在URL中，POST通过request body传递参数](https://www.cnblogs.com/logsharing/p/8448446.html)**
	```
	GET和POST本质上就是TCP链接，并无差别。但是由于HTTP的规定和浏览器/服务器的限制，导致他们在应用过程中体现出一些不同。<br/>
	GET在浏览器回退时是无害的，而POST会再次提交请求。<br/>
	GET产生的URL地址可以被Bookmark，而POST不可以。<br/>
	GET请求会被浏览器主动cache，而POST不会，除非手动设置。<br/>
	GET请求只能进行url编码，而POST支持多种编码方式。<br/>
	GET请求参数会被完整保留在浏览器历史记录里，而POST中的参数不会被保留。<br/>
	GET请求在URL中传送的参数是有长度限制的，而POST么有。<br/>
	对参数的数据类型，GET只接受ASCII字符，而POST没有限制。<br/>
	GET比POST更不安全，因为参数直接暴露在URL上，所以不能用来传递敏感信息。<br/>
	GET参数通过URL传递，POST放在Request body中。<br/>
	```
	* **SpringMVC 提供了多个表单组件标签:**
		- <form:input/>、<form:select/> 等，用以绑定表单字段的属性值，它们的共有属性如下：
			* path：表单字段，对应 html 元素的 name 属性，支持级联属性
			* htmlEscape：是否对表单值的 HTML 特殊字符进行转换，默认值为 true
			* cssClass：表单组件对应的 CSS 样式类名
			* cssErrorClass：表单组件的数据存在错误时，采取的 CSS 样式
		- **form:input、form:password、form:hidden、form:textarea：对应 HTML 表单的 text、password、hidden、textarea标签**
			- form:radiobutton：单选框组件标签，当表单 bean 对应的属性值和 value 值相等时，单选框被选中
			- form:radiobuttons：单选框组标签，
				* items：可以是一个 List、String[] 或 Map
				* itemValue：指定 radio 的 value 值。可以是集合中 bean 的一个属性值
				* itemLabel：指定 radio 的 label 值
				* delimiter：多个单选框可以通过 delimiter 指定分隔符
			- form:checkbox：复选框组件。用于构造单个复选框
			- form:checkboxs：用于构造多个复选框。使用方式同form:radiobuttons 标签
			- form:select：用于构造下拉框组件。使用方式同form:radiobuttons 标签
			- form:option：下拉框选项组件标签。使用方式同form:radiobuttons 标签
			- form:errors：显示表单组件或数据校验所对应的错误
				* <form:errors path= “ *” /> ：显示表单所有的错误
				* <form:errors path= “ user*” /> ：显示所有以 user 为前缀的属性对应的错误
				* <form:errors path= “ username” /> ：显示特定表单对象属性的错误

### 数据绑定流程:
  * Spring MVC 主框架将 ServletRequest 对象及目标方法的入参实例传递给 **WebDataBinderFactory** 实例，
		以创建 **DataBinder** 实例对象
	* **DataBinder** 调用装配在 Spring MVC 上下文中的 **ConversionService** 组件进行数据类型转换、数据格式化工作。将 Servlet 中的请求信息填充到入参对象中
	* 调用 **Validator** 组件对已经绑定了请求消息的入参对象进行数据**合法性校验**，并最终生成数据绑定结果**BindingData** 对象
	* Spring MVC 抽取 **BindingResult** 中的入参对象和校验错误对象，将它们赋给处理方法的响应入参

### 中台:
	```
	中台就是接入层啊，一般有中台的都是比较大的项目，后台会分为很多模块，比如订单模块，比如会员模块，接入层需要做的就是对数据的封装，权限的过滤，
	以及各种安全什么的，前台需要什么数据，接入层去对应的后台微服务模块获取就行，接入层是没有表的，数据都从后台拿。
	```
### mvc:annotation-driven:
  * **<mvc:annotation-driven /> 会自动注册RequestMappingHandlerMapping、RequestMappingHandlerAdapter 与ExceptionHandlerExceptionResolver 三个bean.**
		还将提供以下支持：<br/>
		 - 支持使用 ConversionService 实例对表单参数进行类型转换<br/>
		 - 支持使用 @NumberFormat annotation、@DateTimeFormat注解完成数据类型的格式化<br/>
		 - 支持使用 @Valid 注解对 JavaBean 实例进行 JSR 303 验证<br/>
		 - 支持使用 @RequestBody 和 @ResponseBody 注解<br/>

### 国际化:fmt
  * 写i18n_en_US.properties文件
	* 再context.xml中配置：
	```xml
	<!-- 配置国际化资源文件 -->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
		<property name="basename" value="i18n"></property>
	</bean>
	```
	* 在jsp页面中添加,并使用：
	```xml
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:message key="i18n.username"></fmt:message>
	<fmt:message key="i18n.password"></fmt:message>
	```
### 注:
	```xml
	<!--
		1. 配置直接转发页面，无需经过Handler处理
		2. 这里可能会出问题：
			需要配置<mvc:annotation-driven></mvc:annotation-driven>
				<mvc:default-servlet-handler/>
		3.  所以要一起出现。
	-->
	<mvc:view-controller path="/success" view-name="success">
	<mvc:annotation-driven></mvc:annotation-driven>
	<mvc:default-servlet-handler/>
	```
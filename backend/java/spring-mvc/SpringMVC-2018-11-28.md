## spring-mvc2
### 自定义视图：需要配置BeanNameViewResolver解析器  [使用view的名字解析视图]
```java
@Component
public class HelloView implements View{

	@Override
	public String getContentType() {
		return "text/html";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.getWriter().print("hello view, time: " + new Date());
	}
}
```
```xml
<!-- 配置视图  BeanNameViewResolver 解析器: 使用视图的名字来解析视图 -->
<!-- 通过 order 属性来定义视图解析器的优先级, order 值越小优先级越高 -->
<bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
	<property name="order" value="100"></property>
</bean>
```

### redirect, forword

### commond 报错，由于Spring认为form 表单一定是用来回显的，即使是一次；所以要在request域对象中添加与之对应的数据模型，并设置form的modelAttribute属性[默认就是commond]
 * Spring 的form 表单 ：支持级联属性
 * demo
 ```html
<!--
	1. WHY 使用 form 标签呢 ?
	可以更快速的开发出表单页面, 而且可以更方便的进行表单值的回显
	2. 注意:
	可以通过 modelAttribute 属性指定绑定的模型属性,
	若没有指定该属性，则默认从 request 域对象中读取 command 的表单 bean
	如果该属性值也不存在，则会发生错误。
-->
<br><br>
<form:form action="${pageContext.request.contextPath }/emp" method="POST"
	modelAttribute="employee">

	<%
		Map<String, String> genders = new HashMap();
		genders.put("1", "Male");
		genders.put("0", "Female");

		request.setAttribute("genders", genders);
	%>
	Gender:
	<br>
	<form:radiobuttons path="gender" items="${genders }" delimiter="<br>"/>
	<br>
	Department: <form:select path="department.id"
		items="${departments }" itemLabel="departmentName" itemValue="id"></form:select>
	<br>
 ```

### REST delete 请求实现:
 * **实现目标方法:**
 ```java
@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
public String delete(@PathVariable("id") Integer id){
	employeeDao.delete(id);
	return "redirect:/emps";
}
```
 * **配置context.xml 文件，处理静态资源:**
 ```xml
<mvc:view-controller path="/success" view-name="success">
<mvc:annotation-driven></mvc:annotation-driven>
<mvc:default-servlet-handler/>
 ```
 * **配置web.xml文件用Spring将POST转换为delete请求:**
 ```xml
<!-- 配置 HiddenHttpMethodFilter: 把 POST 请求转为 DELETE、PUT 请求 -->
<filter>
	<filter-name>HiddenHttpMethodFilter</filter-name>
	<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>

<filter-mapping>
	<filter-name>HiddenHttpMethodFilter</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
 ```

 * **把超链接的get请求转换为POST请求:**
 ```js
<!-- 将超链接的get转换为POST -->
<script type="text/javascript">
	$(function(){
		$(".A").click(function(){
			var href = $(this).attr("href");
			$("form").attr("action", href).submit();
			return false;
		});
	})
</script>

<form action="" method="POST">
	<input type="hidden" name="_method" value="DELETE"/>
</form>

<a class="A" href="emp/${emp.id}">Delete</a>
 ```

### REST update 请求实现 ：lastName不能修改，所以不展示
 * 实现目标方法：
```java
//获取要修改的employee
@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
public String input(@PathVariable("id") Integer id, Map<String, Object> map){
	map.put("employee", employeeDao.get(id));
	map.put("departments", departmentDao.getDepartments());
	return "input";
}

//lastName不能修改，所以不展示
<form:form action="${pageContext.request.contextPath }/emp" method="POST"
	modelAttribute="employee">
	<c:if test="${employee.id == null }">
		LastName: <form:input path="lastName"/>
	</c:if>
	<c:if test="${employee.id != null }">
		<form:hidden path="id"/>
		<input type="hidden" name="_method" value="PUT"/>
	</c:if>
</form:form>
//为了lastName不被修改，且不会变为null
@ModelAttribute
public void getEmployee(@RequestParam(value="id",required=false) Integer id,
		Map<String, Object> map){
	if(id != null){
		map.put("employee", employeeDao.get(id));
	}
}

//将form传来的数据封装成Employee对象
@RequestMapping(value="/emp", method=RequestMethod.PUT)
public String update(Employee employee){
	employeeDao.save(employee);

	return "redirect:/emps";
}
```
 * **配置context.xml 文件，处理静态资源 [可能不需要]:**
```xml
<mvc:view-controller path="/success" view-name="success">
<mvc:annotation-driven></mvc:annotation-driven>
<mvc:default-servlet-handler/>
```

 * **配置web.xml文件用Spring将POST转换为PUT请求:**
 ```xml
<!-- 配置 HiddenHttpMethodFilter: 把 POST 请求转为 DELETE、PUT 请求 -->
<filter>
	<filter-name>HiddenHttpMethodFilter</filter-name>
	<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>

<filter-mapping>
	<filter-name>HiddenHttpMethodFilter</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>


<form action="" method="POST">
	<input type="hidden" name="_method" value="DELETE"/>
</form>
```
 * **jsp:**
 ```jsp
<a href="emp/${emp.id}">Edit</a>

<form:form action="${pageContext.request.contextPath }/emp" method="POST"
	modelAttribute="employee">
	<c:if test="${employee.id == null }">
		LastName: <form:input path="lastName"/>
	</c:if>
	<c:if test="${employee.id != null }">
		<form:hidden path="id"/>
		<input type="hidden" name="_method" value="PUT"/>
	</c:if>
</form:form>
 ```

### 自定义转换器Converter:
 * 数据类型转换、数据类型格式化、数据校验
	+ 自定义一个employeeConverter，并交给SpringIOC管理
  + 配置context.xml 文件
	```xml
	<mvc:annotation-driven conversion-service="conversionService"></mvc:annotation-driven>
	<!-- 配置 ConversionService -->
	<bean id="conversionService"
		class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
		<property name="converters">
			<set>
				<ref bean="employeeConverter"/>
			</set>
		</property>
	</bean>
	```

### <mvc:annotation-driven></mvc:annotation-driven>
 * **会自动注册RequestMappingHandlerMapping、RequestMappingHandlerAdapter 与ExceptionHandlerExceptionResolver 三个bean.**


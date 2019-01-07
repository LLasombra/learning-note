## Issue in my-learning:
 * 1. @component 的使用问题:
	```java
	@Bean
	@ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
	// 这个可以直接使用 @Component 加入到 springboot 容器中

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "spring.mvc", name = "locale")
	// 这个就不可以直接使用 @Component 加入到 springboot 容器中；要使用 @Configuration 类的 @bean 方法加入
	```
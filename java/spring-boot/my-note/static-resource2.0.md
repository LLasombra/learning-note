 ## Spring-Boot 2.0 静态资源处理:
  * **spring-boot 2.0 静态资源处理：这里会使用 MyMVCConfig ，也使用springboot 帮忙配置的 WebMvcConfigurer  [可以是多个]addInterceptors() 添加拦截器，放过静态资源.**
	```java
	@Configuration
	public class MyMVCConfig implements WebMvcConfigurer {
		@Bean
		public WebMvcConfigurer webMvcConfigurer() {

			WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {

				@Override
				public void addViewControllers(ViewControllerRegistry registry) {

					registry.addViewController("/").setViewName("login");
					registry.addViewController("/index.html").setViewName("login");
					// 阻止表单重复提交
					registry.addViewController("/main.html").setViewName("dashboard");
				}

				@Override
				public void addInterceptors(InterceptorRegistry registry) {
					registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
							.excludePathPatterns("/","/index.html","user/login","/static/**","/webjars/**");
				}

				@Override
				public void addResourceHandlers(ResourceHandlerRegistry registry) {
					registry.addResourceHandler("/static/**")
						.addResourceLocations("classpath:/static/","classpath:/public/","classpath:/resource/");
					registry.addResourceHandler("/webjars/**")
						.addResourceLocations("classpath:/META-INF/resources/webjars/");
				}
			};

			return webMvcConfigurer;
		}
	}
	```



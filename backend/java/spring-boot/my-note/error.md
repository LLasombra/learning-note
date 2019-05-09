## Spring-Boot 的 error 机制：

- **处理的相关类:**

```java
errorpagecustomeizer
basicerrorcontroller[处理/error请求]
deafulterrorviewresolver
defaulterrorattributes
```

- **处理流程:**

```java
出现异常 ------>  spring的@ControllerAdvice + @ExceptionHandler 对异常处理(转发/error)------->
BasicErrorController[JSON/html] ------ getErrorAttributes() -----> deafulterrorviewresolver得到view
-------> MyErrorAttibutes[自定义的] -------> DefaultErrorAttributes -------getErrorAttributes() -----> BasicErrorController[JSON/html]
```

- **流程是上面的样子，代码没有什么意思：因为在 thymeleaf 中可以直接取 request 的数据【Spring exceptionHandler 放入的数据】.**

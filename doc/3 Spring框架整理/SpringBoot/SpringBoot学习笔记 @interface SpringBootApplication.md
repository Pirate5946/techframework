- [SpringBoot学习笔记(3) Spring Boot 运行原理,自动配置](https://blog.csdn.net/a67474506/article/details/52013634)


- [JDK和Spring中SPI的实现原理和区别](https://my.oschina.net/kipeng/blog/1789849)

https://blog.csdn.net/zxc123e/article/details/80222967

```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
    
}

```

实际上重要的只有三个Annotation：

- @Configuration（@SpringBootConfiguration点开查看发现里面还是应用了@Configuration）
- @EnableAutoConfiguration
- @ComponentScan

如果在启动类使用这个三个注解，整个SpringBoot应用依然可以与之前的启动类功能一样。    

但每次写这3个比较啰嗦，所以写一个@SpringBootApplication方便点。

### SpringFactoriesLoader
SpringFactoriesLoader属于Spring框架私有的一种扩展方案(类似于Java的SPI方案java.util.ServiceLoader)，   

==其主要功能就是从指定的配置文件META-INF/spring-factories加载配置，==

spring-factories是一个典型的java properties文件，只不过Key和Value都是Java类型的完整类名，比如：
```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration
```

#### SpringFactoriesLoader#loadFactoryNames()
加载配置文件        
classLoader.getResources(FACTORIES_RESOURCE_LOCATION)
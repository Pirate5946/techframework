#### 上下文配置类  webEnvironment =  false
org.springframework.context.annotation.AnnotationConfigApplicationContext

#### 通过 BeanDefinition 加载bean
org.springframework.beans.factory.support.AbstractBeanFactory#getBean(java.lang.String)

#### IOC容器的组成部分
BeanDefinitionMap 、 SingletonObjects 、 BeanProcessor 。。。

#### 单例缓存池， IOC容器的组成部分
org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#singletonObjects
```java
/** Cache of singleton objects: bean name --> bean instance */
private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);
```

### 三级缓存、循环依赖


org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#initializeBean(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)

实例化Bean 后置处理器的前置方法
1. @PostConstruct 修饰的方法，
2. 执行 afterPropertiesSet 方法
3. 执行自定义的 init() 方法

实例化bean

实例化bean  后置处理器的后置方法        
干预Bean的生成，修改Bean属性


---

### 了解SpringBoot吗？那讲一下SpringBoot的启动流程吧
- [SpringBoot启动原理解析](http://mp.weixin.qq.com/s?__biz=MzAxNjk4ODE4OQ==&mid=2247485911&idx=1&sn=c24b460e17eb7133b37b2f921aead2f7&chksm=9bed28a5ac9aa1b3fa3a3323884545a9158b33de69b0813a72ec77786a79923f8388b787de6a&mpshare=1&scene=1&srcid=#rd)


- [SpringBoot学习笔记(3) Spring Boot 运行原理,自动配置](https://blog.csdn.net/a67474506/article/details/52013634)


- [JDK和Spring中SPI的实现原理和区别](https://my.oschina.net/kipeng/blog/1789849)

https://blog.csdn.net/zxc123e/article/details/80222967

### SpringBoot学习笔记 @interface SpringBootApplication

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
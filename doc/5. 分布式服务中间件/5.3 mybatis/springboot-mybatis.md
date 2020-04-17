- [官方文档 - spring-boot-starter/mybatis-spring-boot-autoconfigure](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
- [springboot集成mybatis源码分析-启动加载mybatis过程（二）](https://www.cnblogs.com/nxzblogs/p/10484281.html)

### spring启动时，扫描mapper接口，动态生成代理类，交给 Spring容器 管理
- [spring-boot-2.0.3启动源码篇一 - SpringApplication构造方法](https://www.cnblogs.com/youzhibing/p/9550343.html)

- [springboot集成下，mybatis的mapper代理对象究竟是如何生成的](https://www.cnblogs.com/youzhibing/p/10486307.html)
AutoConfiguredMapperScannerRegistrar继承了ImportBeanDefinitionRegistrar

AutoConfiguredMapperScannerRegistrar继承了ImportBeanDefinitionRegistrar

Spring在创建Service实例：UserServiceImpl的时候，发现依赖mapper（可能还有其他的实例依赖mapper），那么就会去spring容器获取mapper实例，没有则进行创建，然后注入进来（依赖注入）；


- [Spring拓展接口之FactoryBean，我们来看看其源码实现](https://www.cnblogs.com/youzhibing/p/10528821.html)

### @MapperScan 


### @Import


Spring 两大理念： 

IOC: Inversion of Contorl 控制反转

AOP: Aspect Oriented Programming 面向切面编程

---
IOC ：通过描述来生成和获取对象

spring管理bean（需要管理的对象）的容器称为 IOC容器，IOC容器的两个基本功能：
1. 通过描述管理Bean，包括发布和获取Bean
2. 通过描述完成Bean之间的依赖关系

==所有IOC容器都需要实现 BeanFactory==，主要提供获取bean的能力

ApplicationContext 继承了 BeanFactory ，完善了容器的功能

==基于注解的 IOC 容器： AnnotationConfigApplicationContext==

---

@Configuration ： 修饰Java配置文件，Spring的容器会根据它来生成IOC容器，然后装配Bean

@Bean ： 修饰需要装配到IOC容器的Bean，==自定义第三方Bean==，name属性指定Bean名称，默认名称为注解修饰的方法名

@Component ：修饰需要装配到IOC容器的Bean        

##### @ComponentScan ： 批量扫描装配Bean，默认扫描配置类所在的当前包和其子包      
- basePackages 属性：定义扫描的包
- excludeFilters: 排除指定的Bean，不进行扫描
```
excludeFilters = {@Filters(classes = Service.class)}
```
另外 @SpringBootApplication 的 exclude 和 excludeName属性只能过滤内部的自动配置类 ， 例如
```
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
```

##### @Autowired ：根据属性的类型找到对应的Bean进行注入
有多个相同类型的Bean（一个接口的多个实现类）时，可以 通过@Primary 指定优先级， 或者 @Qualifier("bean的名称") 同时指定bean的名称

作为构造函数的参数注入
```
public TestBean (@Autowired @Qualifier("dog") Animal animal){
    this.animal = animal;
}
```

#### Bean 的生命周期
大致分为 Bean的定义、初始化、生存期、销毁

##### Bean的初始化过程
1. 资源定位，扫描@ComponentScan定义的路径
2. 保存定义，将Bean的定义保存到 BeanDefinition
3. 发布定义，将Bean的定义发布到 SpringIOC容器
4. 实例化：通过反射，生成bean的实例
5. 依赖注入：通过依赖关系注入Bean

@ComponentScan 有一个配置项 lazyInit，默认为false，表示不会延迟bean的
初始化

Spring Bean的生命周期对所有Bean生效的方法
```
postProcessBeforeInitialization

postProcessAfterInitialization
```
Spring Bean的生命周期涉及的接口
```
BeanNameAware

BeanFactoryAware

ApplicationContextAware

InitializingBean

DisposableBean
```
Spring Bean的生命周期涉及的注解
```
@PostConstruct

@PreDestroy
```










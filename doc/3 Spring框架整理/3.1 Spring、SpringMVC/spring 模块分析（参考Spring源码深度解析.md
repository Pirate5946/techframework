- [Spring 框架的设计理念与设计模式分析](https://www.ibm.com/developerworks/cn/java/j-lo-spring-principle/)

- [Spring总结—— IOC 和 Bean 的总结](https://www.cnblogs.com/solverpeng/p/5689360.html)

- [Spring总结——AOP、JDBC和事务的总结](https://www.cnblogs.com/solverpeng/p/5724806.html)

- [Spring应用——事务管理](https://www.cnblogs.com/solverpeng/p/5720740.html)
- [Spring接收处理HTTP请求流程浅析](https://blog.csdn.net/LeiXiaoTao_Java/article/details/83542907)

## WEB 模块
#### 根据请求找Controller
- [SpringMVC源码之Controller查找原理](https://www.cnblogs.com/w-y-c-m/p/8416630.html)

#### springmvc 参数绑定
- [SpringMVC源码之参数解析绑定原理](https://www.cnblogs.com/w-y-c-m/p/8443892.html)
- [Spring MVC 会按请求参数名和 POJO 属性名进行自动匹配，自动为该对象填充属性值。支持级联属性。](https://www.cnblogs.com/solverpeng/p/5733310.html)

#### 处理返回值
- [SpringMVC核心——视图渲染（包含视图解析）问题](https://www.cnblogs.com/solverpeng/p/5743609.html)


## CORE 模块
- [Spring 的骨骼架构 - 许令波](https://www.ibm.com/developerworks/cn/java/j-lo-spring-principle/)

### Bean组件
#### Bean的定义、解析、创建
- spring核心概念，类加载、管理、自动注入 

### Context 组件
Context 在 Spring 的 org.springframework.context 包下

他实际上就是给 Spring 提供一个运行时的环境，用以保存各个对象的状态

ApplicationContext 是 Context 的顶级父类，继承了五个接口，这五个接口主要是扩展了 Context 的功能

总体来说 ApplicationContext 必须要完成以下几件事：

- 标识一个应用环境
- 利用 BeanFactory 创建 Bean 对象
- 保存对象关系表
- 能够捕获各种事件

Context 作为 Spring 的 Ioc 容器，基本上整合了 Spring 的大部分功能，或者说是大部分功能的基础。

### Core 组件
Core 组件作为 Spring 的核心组件，他其中包含了很多的关键类，其中一个重要组成部分就是定义了==资源的访问方式==

把所有资源都抽象成一个接口

##### 获取资源 
Resource 接口继承了 InputStreamSource 接口，这个接口中有个 getInputStream 方法，返回的是 InputStream 类。这样所有的资源都被可以通过 InputStream 这个类来获取

##### 加载资源
这个任务是由 ResourceLoader 接口完成，他屏蔽了所有的资源加载者的差异，只需要实现这个接口就可以加载所有的资源，他的默认实现是 DefaultResourceLoader。

##### Context 和 Resource 是如何建立关系的
Context 是把资源的加载、解析和描述工作委托给了 ResourcePatternResolver 类来完成，他相当于一个接头人，他把资源的加载、解析和资源的定义整合在一起便于其他组件使用

### Ioc 容器如何工作、如何创建 BeanFactory 工厂
Ioc 容器实际上就是 Context 组件结合其他两个组件共同构建了一个 Bean 关系网，如何构建这个关系网？

构建的入口就在 ==AbstractApplicationContext== 类的 refresh 方法中

==AbstractRefreshableApplicationContext== 类的refreshBeanFactory 方法 ： 创建和配置 BeanFactory

[图 11. 创建 BeanFactory 时序图](https://www.ibm.com/developerworks/cn/java/j-lo-spring-principle/origin_image010.png)

loadBeanDefinitions 方法 ：把用户定义的数据结构转化为 Ioc 容器中的特定数据结构

[Bean 的解析和登记流程时序图](https://www.ibm.com/developerworks/cn/java/j-lo-spring-principle/origin_image011.png)

创建好 BeanFactory 后，接下去添加一些 Spring 本身需要的一些工具类，     
这个操作在 ==AbstractApplicationContext== 的 prepareBeanFactory 方法完成

AbstractApplicationContext 中接下来的三行代码对 Spring 的功能扩展性起了至关重要的作用。

前两行主要是让你现在可以对已经构建的 BeanFactory 的配置做修改，

后面一行就是让你可以对以后再创建 Bean 的实例对象时添加一些自定义的操作。所以他们都是扩展了 Spring 的功能

其中在 ==invokeBeanFactoryPostProcessors== 方法中主要是获取实现 BeanFactoryPostProcessor 接口的子类。并执行它的 postProcessBeanFactory 方法

后面的几行代码是初始化监听事件和对系统的其他监听者的注册，监听者必须是 ApplicationListener 的子类。

#### 如何创建 Bean 实例并构建 Bean 的关系网



#### 2.4.2 核心类介绍
DefaultListableBeanFactory

-  BeanFacroty ： 定义获取Bean 及 Bean的各种属性
-  SimpleAliasRegister ： 主要使用 map
-  




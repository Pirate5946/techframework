### mybatis-plus 源码分析
- [mybatis plus源码解析(一) ---基于springboot配置加载和SqlSessionFactory的构造](https://juejin.im/post/5aea8155518825671b025e1c)
- [mybatis-plus源码解析(二)----基于@MapperScan注解扫描加载Mapper](https://juejin.im/post/5aebf604f265da0b926572ae)
- [mybatis-plus源码解析(三)----Mapper接口动态代理调用过程](https://juejin.im/post/5aefd2756fb9a07aa76793d7)

---

### [《深入理解mybatis原理》MyBatis的架构设计以及实例分析](https://blog.csdn.net/luanlouis/article/details/40422941)
#### 1. 接口层 --- 和数据库交互的方式
- MyBatis和数据库的交互有两种方式：
    
    a.使用传统的MyBatis提供的API；
    
    b. 使用Mapper接口

#### 2. 数据处理层
1. 参数映射和动态 SQL 语句生成
2. SQL 语句的执行以及封装查询结果集成 List<E>

#### 3. 框架支撑层


---
- [原理分析之二：框架整体设计
](https://www.iteye.com/blog/chenjc-it-1460990)
---
[Mybatis3源码分析（三）：解析mapper的xml配置文件](http://blog.csdn.net/flashflight/article/details/43926091) 
- SqlSessionFactoryBean类中的buildSqlSessionFactory


- mybatis使用XMLMapperBuilder类的实例来解析mapper配置文件

- 系统调用xmlMapperBuilder的parse方法解析mapper。

- configurationElement函数几乎解析了mapper节点下所有子节点      
(mybaits解析了mapper中的所有节点，并将其加入到了Configuration对象中提供给sqlSessionFactory对象随时使用)


[Mybatis3源码分析（一）：从sqlSession说起](http://blog.csdn.net/flashflight/article/details/43039281)    
从mybatis创建sqlSessionFactoryBean的过程可以看出，mybaits默认使用spring的事务管理功能或者由第三方实现，它自身并没有提供事务管理能力。    
其次，它拥有跟其它框架差不多的解析xml过程，都是将xml解析成一个Configuration对象随时取用。    
到这里我们就得到了一个sqlSessionFactoryBean对象（实际是mybatis本身的DefaultSqlSessionFactory对象），我们可以在DAO中注入这个对象并利用openSession的方法获取SqlSession对象从而进行各种数据库操作。

[Mybatis3源码分析（二）：扫描Mapper关联到spring IOC容器](http://blog.csdn.net/flashflight/article/details/43464383)     
这样Mapper接口就被注册到sqlSession中，每当操作sqlSession时就会根据这个mapperInterface去查找对应的mapper.xml构建mapper从而完成数据库操作。    

经过以上过程mybaits找到了所有mapper并将其加载到了spring ioc容器里，本章也就完结了。下一章我们再讲mybaits是怎么解析mapper.xml的。


DAO开发需要的部分   
- DAO接口
- DAO接口实现类
- 实体类
---
Mybatis开发DAO      

1. 
- 通过SqlsessionFactoryBuiler创建==SqlSessionFactory==
- (生命周期为整个应用运行期，使用单例模式开发，与spring整合后，由spring容器管理)   
2. 
- 通过DAO接口实现类的构造方法将sqlsessionFactory注入到实现类  
- （与Mybatis整合后由动态代理类加载配置xml生成sqlsessionFactory）   
3. 
- 通过sqlsessionFactory的opensession方法产生sqlsession操作数据库
- （代替原来的JDBC开发，sqlsession线程不安全，生命周期为方法体内）
---
[myBatis3与spring整合之SqlSessionFactoryBean](http://blog.csdn.net/u010538302/article/details/51822479)  
在基本的MyBatis中，session工厂可以使用SqlSessionFactoryBuilder来创建。     
在MyBatis-Spring中，使用==SqlSessionFactoryBean==来替代。  
SqlSessionFactoryBean实现了spring的FactoryBean接口。   
这就说明由==spring最终创建的bean不是SqlSessionFactoryBean本身，而是工厂类的getObject()返回的方法结果==。  
这种情况下，spring将会在应用启动时为你创建SqlSessionFactory对象，然后将它以SqlSessionFactory为名来存储。 

在一般的MyBatis-spring用法中，你不需要直接使用SqlSessionFactoryBean或其对应的SqlSessionFactory。 
相反，==session工厂将会被注入到MapperFactoryBean或其它扩展了SqlSessionDaoSupport的DAO中==。

#### spring_mybatis.xml
- SqlSessionFactory有一个单独的必须属性，就是JDBC的==DataSource==。这可以是任意的DataSource。其配置应该和其它spring数据库连接是一样的。
- 使用工厂bean的mapperLocations属性。==mapperLocations属性==使用一个资源位置的list。这个属性可以用来指定myBatis的xml映射器文件的位置（不需要在mybatis配置文件手工配置每个mapper节点）

---
[Mybatis通过动态代理生成Mapper接口实现类的源码分析](http://blog.csdn.net/starryninglong/article/details/68961226)

首先熟悉三个概念： 
- SqlSessionFactoryBean 
–为整合应用提供SqlSession对象资源 
- MapperFactoryBean 
–根据指定的Mapper接口生成Bean实例 
- MapperScannerConfigurer 
–根据指定包批量扫描Mapper接口并生成实例

#### mybatis与spring整合的MapperScannerConfigurer的底层原理 

- 在调用mapper接口的service方法处打断点F5进入mapper接口实现类（MapperProxy类）实现了InvocationHandler，说明使用了jdk自带的动态代理     

---
[java动态代理浅析](http://www.cnblogs.com/fangjian0423/p/java-dynamic-proxy.html)   
java动态代理的实现有2种方式 
- jdk自带的动态代理     
使用jdk自带的动态代理需要了解InvocationHandler接口和Proxy类，他们都是在java.lang.reflect包下。
    - InvocationHandler是代理实例的调用处理程序实现的接口。
    - Proxy 提供静态方法用于创建动态代理类和实例。
- 第三方库cglib     
CGLIB是一个功能强大的，高性能、高质量的代码生成库，用于在运行期扩展Java类和实现Java接口。
---
[mybatis如何根据mapper接口生成其实现类](http://www.cnblogs.com/ChenLLang/p/5307590.html)

mybatis里头给sqlSession指定执行哪条sql的时候，有两种方式
- 一种是写mapper的xml的namespace+statementId

- 另外一种方法是指定mapper的接口    
mybatis通过JDK的动态代理方式，在启动加载配置文件时，根据配置mapper的xml去生成。        

    - 读spring-mybatis.xml的sqlSessionFactory节点的mapperLocations属性，然后去解析mapper的xml
    

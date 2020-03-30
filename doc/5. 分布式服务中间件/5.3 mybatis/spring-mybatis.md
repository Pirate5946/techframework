- [B站学习资料](https://www.bilibili.com/video/BV19441197bs?t=80)

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
    

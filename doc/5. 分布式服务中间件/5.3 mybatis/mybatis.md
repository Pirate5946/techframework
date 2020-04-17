[mybatis 官方文档](https://mybatis.org/mybatis-3/zh/getting-started.html)

[B站学习资料](https://www.bilibili.com/video/BV1jE411W7cq?p=1)

![image](https://img-blog.csdn.net/20141028232313593?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHVhbmxvdWlz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 提出问题
### 1. 为什么要用 mybatis 这种持久层框架？
1. 原始的JDBC 访问数据库，没有用到数据库连接池，频繁创建销毁连接影响效率

2. sql、配置文件耦合在代码里，不方便全局修改
    - mybatis 全局单例配置对象 SqlSessionFactory 持有 Configuration

3. 消除了重复的模板代码
```text
1. 获取连接，参数替换
2. 释放连接
3. 处理异常 
```

### 2. 为什么可以通过接口和配置信息完成数据库操作？
JDK动态代理 生成mapper接口的代理类，

##### 解析配置信息，获取数据库连接、sql、其他配置信息

#####  动态代理mapper类          
- mapper接口注册：       
应用初始化阶段，解析指定包， HashMap保存包下所有的mapper
- 根据mapper接口获取 mapper代理工厂类
- 反射调用代理类方法 


### 口述理解
1. 初始化配置信息到配置类  Configuration , 里面包含数据库连接信息，所有的sql语句配置，数据库配置项。。。
```java
SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
SqlSessionFactory factory = builder.build(inputStream);
```

2. 单例创建应用运行期间全局唯一的 SqlSessionFactory, 持有Configuration
```java
new DefaultSqlSessionFactory(config);
```

3. 每个操作sql的线程持有各自的 SqlSession，SqlSession持有Configuration 引用，初始化 Executor
```java
SqlSession sqlSession = factory.openSession();

final Environment environment = configuration.getEnvironment();
final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
final Executor executor = configuration.newExecutor(tx, execType);
return new DefaultSqlSession(configuration, executor, autoCommit);
```

4. 两种执行sql的方式       
- SqlSession.XXX
- Mapper.XXX

内部实现 CachingExecutor.query()

### 简单描述
```text
1、我们用mytabis操作数据库，有一个固定流程：先创建SqlSessionFactory，然后创建SqlSession，然后再创建获取mapper代理对象，最后利用mapper代理对象完成数据库的操作；一次数据库操作完成后需要关闭SqlSession；

2、创建SqlSessionFactory实例的过程中，解析mybatis配置文件和映射文件，将内容都存放到Configuration实例的对应属性中；创建SqlSession的过程中，有创建事务Transaction、执行器Executor，以及DefaultSqlSession；Mapper代理对象的创建，利用的是JDK的动态代理，InvocationHandler是MapperProxy，后续Mapper代理对象方法的执行都会先经过MapperProxy的invoke方法；
```

### [《深入理解mybatis原理》MyBatis的架构设计以及实例分析](https://blog.csdn.net/luanlouis/article/details/40422941)
- [原理分析之二：框架整体设计](https://www.iteye.com/blog/chenjc-it-1460990)

![image](https://img-blog.csdn.net/20141028140852531?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHVhbmxvdWlz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

从MyBatis代码实现的角度来看，MyBatis的主要的核心部件有以下几个：
```
- Configuration        MyBatis所有的配置信息都维持在Configuration对象之中。
- MappedStatement   MappedStatement维护了一条<select|update|delete|insert>节点的封装，
- SqlSession            作为MyBatis工作的主要顶层API，表示和数据库交互的会话，完成必要数据库增删改查功能
- Executor              MyBatis执行器，是MyBatis 调度的核心，负责SQL语句的生成和查询缓存的维护
- StatementHandler   封装了JDBC Statement操作，负责对JDBC statement 的操作，如设置参数、将Statement结果集转换成List集合。
- ParameterHandler   负责对用户传递的参数转换成JDBC Statement 所需要的参数，
- ResultSetHandler    负责将JDBC返回的ResultSet结果集对象转换成List类型的集合；
- TypeHandler          负责java数据类型和jdbc数据类型之间的映射和转换
- SqlSource            负责根据用户传递的parameterObject，动态地生成SQL语句，将信息封装到BoundSql对象中，并返回
- BoundSql             表示动态生成的SQL语句以及相应的参数信息

```

#### 1. 接口层 --- 和数据库交互的方式
MyBatis和数据库的交互有两种方式：
- a.使用传统的MyBatis提供的API；
    

- b. 使用Mapper接口     
MyBatis 将配置文件中的每一个<mapper> 节点抽象为一个 Mapper 接口        
MyBatis 会根据相应的接口声明的方法信息，通过动态代理机制生成一个Mapper 实例，      
我们使用Mapper 接口的某一个方法时，MyBatis 会根据这个方法的方法名和参数类型，确定Statement Id，       
底层还是通过SqlSession.select("statementId",parameterObject);或者SqlSession.update("statementId",parameterObject); 等等来实现对数据库的操作

MyBatis 引用Mapper 接口这种调用方式，纯粹是为了满足面向接口编程的需要。（其实还有一个原因是在于，面向接口的编程，使得用户在接口上可以使用注解来配置SQL语句，这样就可以脱离XML配置文件，实现“0配置”）。


#### 2. 数据处理层
1. 参数映射和动态 SQL 语句生成


2. SQL 语句的执行以及封装查询结果集成 List<E>

#### 3. 框架支撑层

##### 3.1 事务管理机制
- [《深入理解mybatis原理》 MyBatis事务管理机制](https://blog.csdn.net/u010349169/article/details/37992171)
      
##### 3.2 连接池管理机制
      
##### 3.3 缓存机制
     
##### 3.4 SQL语句的配置方式
     

#### 4. 引导层
引导层是MyBatis初始化的过程，就是创建 Configuration对象的过程。

MyBatis的初始化可以有两种方式：
- 基于XML配置文件：基于XML配置文件的方式是将MyBatis的所有配置信息放在XML文件中，MyBatis通过加载并XML配置文件，将配置文信息组装成内部的Configuration对象
- 基于Java API：这种方式不使用XML配置文件，需要MyBatis使用者在Java代码中，手动创建Configuration对象，然后将配置参数set 进入Configuration对象中

#### 从MyBatis一次select 查询语句来分析MyBatis的架构设计

##### 1. 开启一个数据库访问会话---创建SqlSession对象：
```java
SqlSession sqlSession = factory.openSession();
```
MyBatis封装了对数据库的访问，把对数据库的会话和事务控制放到了SqlSession对象中。

##### 2. 为SqlSession传递一个配置的Sql语句 的Statement Id和参数，然后返回结果：
```java
List<Employee> result = sqlSession.selectList("com.louis.mybatis.dao.EmployeesMapper.selectByMinSalary",params);
```
MyBatis在初始化的时候，会将MyBatis的配置信息全部加载到内存中，使用org.apache.ibatis.session.Configuration实例来维护。   
可以使用sqlSession.getConfiguration()方法来获取。MyBatis的配置文件中配置信息的组织格式和内存中对象的组织格式几乎完全对应的。

SqlSession根据Statement ID, 在mybatis配置对象Configuration中获取到对应的MappedStatement对象，然后调用mybatis执行器来执行具体的操作。

上述的Executor.query()方法几经转折，最后会创建一个StatementHandler对象，然后将必要的参数传递给StatementHandler，使用StatementHandler来完成对数据库的查询，最终返回List结果集。

##### 3. MyBatis执行器Executor根据SqlSession传递的参数执行query()方法

Executor的功能和作用是：
```
(1、根据传递的参数，完成SQL语句的动态解析，生成BoundSql对象，供StatementHandler使用；

(2、为查询创建缓存，以提高性能（具体它的缓存机制不是本文的重点，我会单独拿出来跟大家探讨，感兴趣的读者可以关注我的其他博文）；

(3、创建JDBC的Statement连接对象，传递给StatementHandler对象，返回List查询结果。
```

##### 4. StatementHandler对象负责设置Statement对象中的查询参数、处理JDBC返回的resultSet，将resultSet加工为List 集合返回：

StatementHandler对象主要完成两个工作：
```
(1. 对于JDBC的PreparedStatement类型的对象，创建的过程中，我们使用的是SQL语句字符串会包含 若干个? 占位符，我们其后再对占位符进行设值。
StatementHandler通过parameterize(statement)方法对Statement进行设值；       

(2.StatementHandler通过List<E> query(Statement statement, ResultHandler resultHandler)方法来完成执行Statement，和将Statement对象返回的resultSet封装成List；
```

##### 5. StatementHandler 的parameterize(statement) 方法的实现：
StatementHandler 的parameterize(Statement) 方法调用了 ParameterHandler的setParameters(statement) 方法，

ParameterHandler的setParameters(Statement)方法负责 根据我们输入的参数，对statement对象的 ? 占位符处进行赋值。

##### 6. StatementHandler 的List<E> query(Statement statement, ResultHandler resultHandler)方法的实现：
调用了ResultSetHandler的handleResultSets(Statement) 方法。     
ResultSetHandler的handleResultSets(Statement) 方法会将Statement语句执行后生成的resultSet 结果集转换成List<E> 结果集



---

###  加载sql语句      
- select
- insert
- update
- delete

mappers 加载 mapper文件有4种方式
```
<!-- 使用相对于类路径的资源引用 -->
<mappers>
  <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
</mappers>

<!-- 使用完全限定资源定位符（URL） -->
<mappers>
  <mapper url="file:///var/mappers/BlogMapper.xml"/>
</mappers>

<!-- 使用映射器接口实现类的完全限定类名 -->
<mappers>
  <mapper class="org.mybatis.builder.BlogMapper"/>
</mappers>

<!-- 将包内的映射器接口实现全部注册为映射器 -->
<mappers>
  <package name="org.mybatis.builder"/>
</mappers>
```
获取sql语句的步骤
```
org.mybatis.spring.SqlSessionFactoryBean#buildSqlSessionFactory
》  org.apache.ibatis.builder.xml.XMLConfigBuilder#parse
》  org.apache.ibatis.builder.xml.XMLConfigBuilder#mapperElement
》  org.apache.ibatis.builder.xml.XMLMapperBuilder.buildStatementFromContext(java.util.List<org.apache.ibatis.parsing.XNode>)
》  org.apache.ibatis.builder.MapperBuilderAssistant.addMappedStatement(java.lang.String, org.apache.ibatis.mapping.SqlSource, org.apache.ibatis.mapping.StatementType, org.apache.ibatis.mapping.SqlCommandType, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Class<?>, java.lang.String, java.lang.Class<?>, org.apache.ibatis.mapping.ResultSetType, boolean, boolean, boolean, org.apache.ibatis.executor.keygen.KeyGenerator, java.lang.String, java.lang.String, java.lang.String, org.apache.ibatis.scripting.LanguageDriver, java.lang.String)
》  ### org.apache.ibatis.session.Configuration.addMappedStatement
```


### 获取连接，执行sql
- Connection
- PrepareStatement
- ResultSet

Mybatis有三种执行器，默认是 SIMPLE
```java
org.apache.ibatis.session.ExecutorType

SIMPLE, REUSE, BATCH 
```
默认开启一级缓存，保存在 PerpetualCache 内部持有 HashMap
```java
protected boolean cacheEnabled = true;

一级缓存的 key 规则        
org.apache.ibatis.cache.CacheKey.toString

id:offset:limit:sql
```
 查询语句执行过程
```
org.apache.ibatis.session.defaults.DefaultSqlSessionFactory.openSession()
》org.apache.ibatis.session.Configuration.newExecutor(org.apache.ibatis.transaction.Transaction)
》org.apache.ibatis.session.defaults.DefaultSqlSession.selectOne(java.lang.String)
》org.apache.ibatis.executor.BaseExecutor.queryFromDatabase
》org.apache.ibatis.executor.statement.PreparedStatementHandler.queryope
oo
fff
open

```
sql中参数替换的原理 #{...} 



---

### [《深入理解mybatis原理》 Mybatis初始化机制详解](https://blog.csdn.net/u010349169/article/details/37744073)

MyBatis初始化的过程，就是创建 Configuration对象的过程。

MyBatis的初始化可以有两种方式：
- 基于XML配置文件：基于XML配置文件的方式是将MyBatis的所有配置信息放在XML文件中，MyBatis通过加载并XML配置文件，将配置文信息组装成内部的Configuration对象
- 基于Java API：这种方式不使用XML配置文件，需要MyBatis使用者在Java代码中，手动创建Configuration对象，然后将配置参数set 进入Configuration对象中

==org.apache.ibatis.session.SqlSessionFactoryBuilder#build(org.apache.ibatis.session.Configuration) 返回 SqlSessionFactory==

## 获取 Configuration 对象， 与 mybatis配置文件 结构一致

###  连接数据库源
```
org.mybatis.spring.SqlSessionFactoryBean#buildSqlSessionFactory
》  org.apache.ibatis.builder.xml.XMLConfigBuilder#parse
》  org.apache.ibatis.builder.xml.XMLConfigBuilder#parseConfiguration
》  org.apache.ibatis.builder.xml.XMLConfigBuilder#environmentsElement
》  #### org.apache.ibatis.session.Configuration#setEnvironment   建造者模式，内部类生成外部类
```
```
- Driver
- Url
- Username
- Password
```

### [《深入理解mybatis原理》 Mybatis数据源与连接池](https://blog.csdn.net/luanlouis/article/details/37671851)
1. 在mybatis的XML配置文件中，使用<dataSource>元素来配置数据源：    
MyBatis在初始化时，解析此文件，根据<dataSource>的type属性来创建相应类型的的数据源DataSource，即：

2. MyBatis是通过工厂模式来创建数据源DataSource对象的，MyBatis定义了抽象的工厂接口:org.apache.ibatis.datasource.DataSourceFactory,通过其getDataSource()方法返回数据源DataSource：

3. MyBatis创建了DataSource实例后，会将其放到Configuration对象内的Environment对象中， 供以后使用。

#### DataSource什么时候创建Connection对象 ?
当我们需要创建SqlSession对象并需要执行SQL语句时，这时候MyBatis才会去调用dataSource对象来创建java.sql.Connection对象。     
也就是说，java.sql.Connection对象的创建一直延迟到执行SQL语句的时候。

#### 为什么要使用连接池？
创建一个java.sql.Connection对象的耗时较大，高并发场景不适合重复创建

在创建了Connection对象，并操作完数据库后，可以不释放掉资源，而是将它放到内存中，当下次需要操作数据库时，可以直接从内存中取出Connection对象，不需要再创建了，这样就极大地节省了创建Connection对象的资源消耗

由于内存也是有限和宝贵的，这又对我们对内存中的Connection对象怎么有效地维护提出了很高的要求。我们将在内存中存放Connection对象的容器称之为 连接池（Connection Pool）

    
### [《深入理解mybatis原理》 MyBatis事务管理机制](https://blog.csdn.net/u010349169/article/details/37992171)

#### 1. 概述
对数据库的事务而言，应该具有以下几点：创建（create）、提交（commit）、回滚（rollback）、关闭（close）。对应地，MyBatis将事务抽象成了Transaction接口：其接口定义如下：

MyBatis的事务管理分为两种形式：

一、使用JDBC的事务管理机制：即利用java.sql.Connection对象完成对事务的提交（commit()）、回滚（rollback()）、关闭（close()）等

二、使用MANAGED的事务管理机制：这种机制MyBatis自身不会去实现事务管理，而是让程序的容器如（JBOSS，Weblogic）来实现对事务的管理

#### 2. 事务的配置、创建和使用
##### 1. 我们在使用MyBatis时，一般会在MyBatisXML配置文件中定义类似如下的信息：
   
<environment>节点定义了连接某个数据库的信息，其子节点<transactionManager> 的type 会决定我们用什么类型的事务管理机制。
   
##### 2.事务工厂的创建       
MyBatis事务的创建是交给TransactionFactory 事务工厂来创建的，     
如果type = "JDBC",则MyBatis会创建一个JdbcTransactionFactory.class 实例；如果type="MANAGED"，则MyBatis会创建一个MangedTransactionFactory.class实例。

##### 3. 事务工厂TransactionFactory
事务工厂Transaction定义了创建Transaction的两个方法：   
- 一个是通过指定的Connection对象创建Transaction，
- 另外是通过数据源DataSource来创建Transaction。     

与JDBC 和MANAGED两种Transaction相对应，TransactionFactory有两个对应的实现的子类

##### 4. 事务Transaction的创建
```java

TransactionFactory factory = (TransactionFactory) resolveClass(type).getDeclaredConstructor().newInstance();

```

##### 5. JdbcTransaction
JdbcTransaction直接使用JDBC的提交和回滚事务管理机制 。       
它依赖与从dataSource中取得的连接connection 来管理transaction 的作用域，connection对象的获取被延迟到调用getConnection()方法。     
如果autocommit设置为on，开启状态的话，它会忽略commit和rollback。

直观地讲，就是JdbcTransaction是使用的java.sql.Connection 上的commit和rollback功能，      
JdbcTransaction只是相当于对java.sql.Connection事务处理进行了一次包装（wrapper），       
Transaction的事务管理都是通过java.sql.Connection实现的

##### 6. ManagedTransaction
ManagedTransaction让容器来管理事务Transaction的整个生命周期，           
意思就是说，使用ManagedTransaction的commit和rollback功能不会对事务有任何的影响，        
它什么都不会做，它将事务管理的权利移交给了容器来实现。



### 《深入理解mybatis原理》 MyBatis缓存机制的设计与实现
MyBatis将数据缓存设计成两级结构，分为一级缓存、二级缓存：

       一级缓存是Session会话级别的缓存，位于表示一次数据库会话的SqlSession对象之中，又被称之为本地缓存。一级缓存是MyBatis内部实现的一个特性，用户不能配置，默认情况下自动支持的缓存，用户没有定制它的权利（不过这也不是绝对的，可以通过开发插件对它进行修改）；

       二级缓存是Application应用级别的缓存，它的是生命周期很长，跟Application的声明周期一样，也就是说它的作用范围是整个Application应用。

![image](https://img-blog.csdn.net/20141122222537708)


### [《深入理解mybatis原理》 MyBatis的一级缓存实现详解 及使用注意事项](https://blog.csdn.net/u010349169/article/details/41280959)
1、什么是一级缓存？为什么使用一级缓存？
```
每当我们使用MyBatis开启一次和数据库的会话，MyBatis会创建出一个SqlSession对象表示一次数据库会话。

在对数据库的一次会话中，我们有可能会反复地执行完全相同的查询语句，如果不采取一些措施的话，每一次查询都会查询一次数据库,而我们在极短的时间内做了完全相同的查询，那么它们的结果极有可能完全相同，由于查询一次数据库的代价很大，这有可能造成很大的资源浪费。

为了解决这一问题，减少资源的浪费，MyBatis会在表示会话的SqlSession对象中建立一个简单的缓存，将每次查询到的结果结果缓存起来，当下次查询的时候，如果判断先前有个完全一样的查询，会直接从缓存中直接将结果取出，返回给用户，不需要再进行一次数据库查询了。

如下图所示，MyBatis会在一次会话的表示----一个SqlSession对象中创建一个本地缓存(local cache)，对于每一次查询，都会尝试根据查询的条件去本地缓存中查找是否在缓存中，如果在缓存中，就直接从缓存中取出，然后返回给用户；否则，从数据库读取数据，将查询结果存入缓存并返回给用户。
```
2、MyBatis的一级缓存是怎样组织的？（即SqlSession对象中的缓存是怎样组织的？）
```
 由于MyBatis使用SqlSession对象表示一次数据库的会话，
那么，对于会话级别的一级缓存也应该是在SqlSession中控制的。

SqlSession 只是一个MyBatis对外的接口，SqlSession 将它的工作交给了Executor执行器这个角色来完成，负责完成对数据库的各种操作。  
当创建了一个SqlSession对象时，MyBatis会为这个SqlSession对象创建一个新的Executor执行器，       
而缓存信息就被维护在这个Executor执行器中，       
MyBatis将缓存和对缓存相关的操作封装成了Cache接口中

```
3、一级缓存的生命周期有多长？
```text
a. MyBatis在开启一个数据库会话时，会 创建一个新的SqlSession对象，SqlSession对象中会有一个新的Executor对象，Executor对象中持有一个新的PerpetualCache对象；当会话结束时，SqlSession对象及其内部的Executor对象还有PerpetualCache对象也一并释放掉。

b. 如果SqlSession调用了close()方法，会释放掉一级缓存PerpetualCache对象，一级缓存将不可用；

c. 如果SqlSession调用了clearCache()，会清空PerpetualCache对象中的数据，但是该对象仍可使用；

d.SqlSession中执行了任何一个update操作(update()、delete()、insert()) ，都会清空PerpetualCache对象的数据，但是该对象可以继续使用；

```
4、Cache接口的设计以及CacheKey的定义
```text
怎样判断某两次查询是完全相同的查询

1. 传入的 statementId

2. 查询时要求的结果集中的结果范围 （结果的范围通过rowBounds.offset和rowBounds.limit表示）；

3. 这次查询所产生的最终要传递给JDBC java.sql.Preparedstatement的Sql语句字符串（boundSql.getSql() ）

4. 传递给java.sql.Statement要设置的参数值


```
5、一级缓存的性能分析以及应该注意的事项
```text
1.MyBatis对会话（Session）级别的一级缓存设计的比较简单，就简单地使用了HashMap来维护，并没有对HashMap的容量和大小进行限制。

对于数据变化频率很大，并且需要高时效准确性的数据要求，我们使用SqlSession查询的时候，要控制好SqlSession的生存时间，SqlSession的生存时间越长，它其中缓存的数据有可能就越旧，从而造成和真实数据库的误差       


2.  一级缓存是一个粗粒度的缓存，没有更新缓存和缓存过期的概念

对于只执行、并且频繁执行大范围的select操作的SqlSession对象，SqlSession对象的生存时间不应过长。


```      

### [《深入理解mybatis原理》 MyBatis的二级缓存的设计原理](https://blog.csdn.net/u010349169/article/details/41408341)

#### 1.MyBatis的缓存机制整体设计以及二级缓存的工作模式
```
当开一个会话时，一个SqlSession对象会使用一个Executor对象来完成会话操作，       
MyBatis的二级缓存机制的关键就是对这个Executor对象做文章。        
如果用户配置了"cacheEnabled=true"，那么MyBatis在为SqlSession对象创建Executor对象时，会对Executor对象加上一个装饰者：CachingExecutor，        
这时SqlSession使用CachingExecutor对象来完成操作请求。     

CachingExecutor对于查询请求，会先判断该查询请求在Application级别的二级缓存中是否有缓存结果，     
如果有查询结果，则直接返回缓存结果；如果缓存中没有，再交给真正的Executor对象来完成查询操作，      
之后CachingExecutor会将真正Executor返回的查询结果放置到缓存中，然后在返回给用户    


```

#### 2 . MyBatis二级缓存的划分
```text
MyBatis并不是简单地对整个Application就只有一个Cache缓存对象，它将缓存划分的更细，即是Mapper级别的，即每一个Mapper都可以拥有一个Cache对象，具体如下：

a.为每一个Mapper分配一个Cache缓存对象（使用<cache>节点配置）；

b.多个Mapper共用一个Cache缓存对象（使用<cache-ref>节点配置）；


```

#### 3. 使用二级缓存，必须要具备的条件
```text
想使某条Select查询支持二级缓存，你需要保证：

   1.  MyBatis支持二级缓存的总开关：全局配置变量参数   cacheEnabled=true

   2. 该select语句所在的Mapper，配置了<cache> 或<cached-ref>节点，并且有效

   3. 该select语句的参数 useCache=true

```

#### 4. 一级缓存和二级缓存的使用顺序
```
如果你的MyBatis使用了二级缓存，并且你的Mapper和select语句也配置使用了二级缓存，那么在执行select查询的时候，MyBatis会先从二级缓存中取输入，其次才是一级缓存，即MyBatis查询数据的顺序是：

   二级缓存 ———> 一级缓存——> 数据库
```

#### 5. 二级缓存实现的选择
使用MyBatis的二级缓存有三个选择:
```text
1.MyBatis自身提供的缓存实现；

2. 用户自定义的Cache接口实现；

3.跟第三方内存缓存库的集成；
```

#### 6.  MyBatis自身提供的二级缓存的实现
```text
MyBatis主要提供了以下几个刷新和置换策略：

     LRU：（Least Recently Used）,最近最少使用算法，即如果缓存中容量已经满了，会将缓存中最近做少被使用的缓存记录清除掉，然后添加新的记录；

     FIFO：（First in first out）,先进先出算法，如果缓存中的容量已经满了，那么会将最先进入缓存中的数据清除掉；

     Scheduled：指定时间间隔清空算法，该算法会以指定的某一个时间间隔将Cache缓存中的数据清空

```

### [如何细粒度地控制你的MyBatis二级缓存(mybatis-enhanced-cache插件实现)](https://blog.csdn.net/u010349169/article/details/41800511)
对于某些表执行了更新(update、delete、insert)操作后，如何去清空跟这些表有关联的查询语句所造成的缓存；

最理想的解决方案就是：

          对于某些表执行了更新(update、delete、insert)操作后，如何去清空跟这些表有关联的查询语句所造成的缓存
#### 当前MyBatis二级缓存的工作机制：
     



---

[Mybatis3源码分析（一）：从sqlSession说起](http://blog.csdn.net/flashflight/article/details/43039281)    
从mybatis创建sqlSessionFactoryBean的过程可以看出，mybaits默认使用spring的事务管理功能或者由第三方实现，它自身并没有提供事务管理能力。    
其次，它拥有跟其它框架差不多的解析xml过程，都是将xml解析成一个Configuration对象随时取用。    
到这里我们就得到了一个sqlSessionFactoryBean对象（实际是mybatis本身的DefaultSqlSessionFactory对象），我们可以在DAO中注入这个对象并利用openSession的方法获取SqlSession对象从而进行各种数据库操作。

[Mybatis3源码分析（二）：扫描Mapper关联到spring IOC容器](http://blog.csdn.net/flashflight/article/details/43464383)     
这样Mapper接口就被注册到sqlSession中，每当操作sqlSession时就会根据这个mapperInterface去查找对应的mapper.xml构建mapper从而完成数据库操作。    

经过以上过程mybaits找到了所有mapper并将其加载到了spring ioc容器里，本章也就完结了。下一章我们再讲mybaits是怎么解析mapper.xml的。

[Mybatis3源码分析（三）：解析mapper的xml配置文件](http://blog.csdn.net/flashflight/article/details/43926091) 
- SqlSessionFactoryBean类中的buildSqlSessionFactory


- mybatis使用XMLMapperBuilder类的实例来解析mapper配置文件

- 系统调用xmlMapperBuilder的parse方法解析mapper。

- configurationElement函数几乎解析了mapper节点下所有子节点      
(mybaits解析了mapper中的所有节点，并将其加入到了Configuration对象中提供给sqlSessionFactory对象随时使用)


---

### 重点对象的生命周期
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
    

---
## 涉及到的设计模式

### 单例模式
SqlSessionFactory

### 建造者模式 Builder
Configuration 的一些属性的初始化
- Environment.Builder


- Statement.Builder


### 代理模式 Proxy
将真实地 Connection 对象 包装成 PooledConnection ，调用close方法时，没有关闭连接，而是放入连接池

[]: https://img-blog.csdn.net/20141028140852531?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHVhbmxvdWlz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast

### 装饰模式
    CachingExecutor是Executor的装饰者，以增强Executor的功能，使其具有缓存查询的功能


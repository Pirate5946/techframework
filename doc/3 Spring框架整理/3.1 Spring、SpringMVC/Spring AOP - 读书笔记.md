### 基于 @AspectJ 的AOP

### 《Spring4.x 企业应用开发实战》

#### 8.2.3 使用注解
getAnnotation(注解名.class)

#### 8.3 @AspectJ 描述切面、增强
需要 ASM 模块，轻量级的字节码处理框架

Spring容器会寻找 @AspectJ 注释的类，动态代理生成目标方法的代理类

#### 8.4.5 引介增强 @DeclareParents
为目标类添加新的接口实现，获得其他类的功能

#### 8.5.2 切点表达式 execution() 、 args() 、 within()、target() ...
掌握常用的 execution()

1. 指定方法 public 、 方法名前缀、方法名后缀
2. 指定类 指定接口名 、 接口及其实现类
3. 指定 包名、包及其子包、包名前后缀
4. 指定方法入参

#### 8.6.1 复合切点
匹配 com.smart 包中所有 greetTo()方法
```java
@After("within(com.smart.*) && execution(* greetTo(..)))")
```

#### 8.6.2 引用其他切点
方法名 就是引用名
```java
@Pointcut("@annotation(com.bs4.gdslink.annotation.IfsApiLogger)")
private void ifsApiLogger() {

}

@Around("ifsApiLogger()")
public Object ifsApiLogger(ProceedingJoinPoint joinPoint) throws Throwable{
    
}
```

#### 绑定返回值、绑定抛出的异常。。。

#### 8.8
4种定义切面的方式
1. 基于 @AspectJ 注解
2. 基于 <aop:aspect>
3. 基于<aop:advisor>
4. 基于 Advisor 类的配置

底层都是基于 CGLib 和JDK动态代理

#### 8.9 类加载期编辑字节码， LTW
ASM ： 轻量级的字节码处理框架

### 10.2.1 Spring DAO的异常体系
Spring的异常体系 建立在运行时异常的基础上，去除了大量的检查型异常（try catch）

使用者只需要捕捉感兴趣的异常，其他异常有框架自行处理

### 10.3.1 数据访问模板
JDBC数据访问操作
1. 准备资源
2. 启动事务
3. 在事务中执行具体的操作
4. 提交/回滚事务
5. 关闭资源、处理异常

Spring将数据访问流程固定到模板类中，保证模板类的线程安全，将固定和变化的部分分开

变化的部分通过回调接口开发（数据访问、返回数据）

JdbcTemplate、

DaoSupport 实现了 InitializingBean接口，在afterPropertiesSet()方法中 检查模板对象和数据源是否正确设置

### 10.4.1 配置一个数据源

### 11.2 ThreadLocal基础知识

#### 11.2.4 与 Thread同步机制比较
同步机制：以时间换空间，访问串行化，对象共享

ThreadLocal：空间换时间，访问并行化，对象独享

### 11.4 编程式事务 、 11.5 声明式事务
Spring的声明式事务是通过 AOP实现的，通过声明信息，Spring负责将 事务管理增强逻辑 动态织入到业务方法的相应连接点中

获取线程绑定资源、开始事务、提交/回滚事务、异常转换

默认情况下，Spring的声明式事务只在遇到 运行期异常（系统异常） 自动回滚

### 12.3 事务传播机制
除了事务的传播行为，对于事务的其他特性，Spring是借助底层资源的功能来完成的

## 13.2 基本的数据操作
在应用层中创建主键，分布式ID生成

让数据库只负责数据存储和查询，业务逻辑应在应用层中进行

### 13.2.3 批量修改数据

### 13.2.4 处理返回数据
RowMapper的操作方式是 先获取数据，再处理数据，采用批量化策略

RowCallbackHandle 是一边获取数据，一边处理，采用流化策略

### 13.2.6 调用存储过程

## 13.3 BLOB/CLOB 类型数据的操作

### 13.4.2 如何规划主键方案

### 14.4.1 BaseDao设计

### 14.4.2 查询接口设计
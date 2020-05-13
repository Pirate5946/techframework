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
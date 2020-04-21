- [Spring事务原理深入解析（AOP,Threadlocal, 隔离级别,传播级别）](https://blog.csdn.net/weixin_44366439/article/details/90381619)
### [spring 事务传播属性](https://blog.csdn.net/weixin_44366439/article/details/89030080)
默认属性 REQUIRED
```text
Propagation propagation() default Propagation.REQUIRED;
```
对于REQUIRED传播级别，即使父事务中没有抛出异常，但是子事务中已经设置了回滚标志，那么父事务依然会回滚
```text

```

### spring 事务的实现原理、嵌套事务的实现原理
AOP

如果是嵌入事务，则创建一个SAVEPOINT


### spring 事务不生效的场景
在一个Service内部，事务方法之间的嵌套调用，普通方法和事务方法之间的嵌套调用，都不会开启新的事务.
```text
1.如果不是Innodb存储引擎，MyISAM不支持事务。
2.没有指定rollbackFor参数。
3. 没有指定transactionManager参数，默认的transactionManager并不是我期望的，以及一个事务中涉及到了多个数据库。
4. 如果AOP使用了JDK动态代理，对象内部方法互相调用不会被Spring的AOP拦截，@Transactional注解无效。
5. 如果AOP使用了CGLIB代理，事务方法或者类不是public，无法被外部包访问到，或者是final无法继承，@transactional注解无效
```
1. spring采用动态代理机制来实现事务控制，而动态代理最终都是要调用原始对象的，而原始对象在去调用方法时，是不会再触发代理了！

2. Spring的事务管理是通过AOP实现的，其AOP的实现对于非final类是通过cglib这种方式，即生成当前类的一个子类作为代理类，然后在调用其下的方法时，会判断这个方法有没有@Transactional注解，如果有的话，则通过动态代理实现事务管理(拦截方法调用，执行事务等切面)。当b()中调用a()时，发现b()上并没有@Transactional注解，所以整个AOP代理过程(事务管理)不会发生。

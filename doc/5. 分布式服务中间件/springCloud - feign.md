####  [SpringCloud底层服务之间是怎么相互调用的？](https://blog.csdn.net/luckyzsion/article/details/88303134)
Feign Client会在底层根据你的注解，跟你指定的服务建立连接、构造请求、发起请求、获取响应、解析响应，等等
- [方志朋 - 深入理解Feign之源码解析](https://blog.csdn.net/forezp/article/details/73480304)
- [参考文章 - 如果要自己实现基于现有的okhttp或者apache httpclient封装一套易用的api，需要怎么做](https://blog.csdn.net/Houson_c/article/details/84933840)
```
1. 不同接口的请求格式是千变万化的，可能另一个接口需要上传文件等等，如何能减少这种方法的定义，就是怎么把代码变得更抽象的更通用？api如何更易用？

2. 当需要替换掉client或者增加功能时，需要大改特改怎么实现好的扩展性？

问题1，面对不同的接口做到通用和简单明了的接口，答案是使用声明式的注解配置请求的各个组成。

问题2，要能根据不同的接口注解配置动态组装出不同的增强功能的代理对象来实现对接口请求，达到发送http请求的目的，答案是使用动态代理.
```
- [参考文章 - feign-底层http请求组件剖析](https://blog.csdn.net/JThink_/article/details/73274822)
    - [自定义的spring-boot的feign httpclient starter，为feign替换原生的URLConnection使用httpclient提供简易的配置并集成spring-boot的auto configuration - 自己创建了带连接池的httpclient对象](https://github.com/JThink/spring-boot-starter-feign-httpclient)
##### 带着问题找答案？？？
- 序列化、反序列化发生的时机
- 服务提供方怎么监听请求，解析参数，返回响应体？
### [feign构造http请求，解析response过程解析](https://blog.csdn.net/luanlouis/article/details/82821294)
- feign接口、熔断实现细节 
    - [Spring Cloud-Feign设计原理](https://blog.csdn.net/luanlouis/article/details/82821294)
        - 提供Http调用服务
        - 构造Http请求URL
        - 填写Http请求头信息
        - 填写消息报文信息
        - ==提取报文信息，转换成对应的Java bean==
    - http请求怎么构造？ 怎么发送？怎么解析响应报文？
    - ![image](https://ws2.sinaimg.cn/large/006Xmmmgly1g327dkpcvqj30o70uf0ud.jpg)


#### 1. 基于面向接口的动态代理方式生成实现类

#### 2. 根据Contract协议规则，解析接口类的注解信息，解析成内部表现：

#### 3. 基于 RequestBean，动态生成Request

#### 4. 使用Encoder 将Bean转换成 Http报文正文（消息解析和转码逻辑）

#### 5. 拦截器负责对请求和返回进行装饰处理

#### 6. 日志记录

#### 7. 基于重试器发送HTTP请求
Feign 内置了一个重试器，当HTTP请求出现IO异常时，Feign会有一个最大尝试次数发送请求，以下是Feign核心代码逻辑 SynchronousMethodHandler.java

Retryer 默认重试5次

#### 8. 发送Http请求
Feign 默认底层通过JDK 的 java.net.HttpURLConnection 实现了feign.Client接口类,

在每次发送请求的时候，都会创建新的HttpURLConnection 链接，这也就是为什么默认情况下Feign的性能很差的原因。

可以通过拓展该接口，使用Apache HttpClient或者OkHttp3等基于连接池的高性能Http客户端


#### Feign 的性能怎么样？
Feign 整体框架非常小巧，在处理请求转换和消息解析的过程中，基本上没什么时间消耗。

真正影响性能的，是处理Http请求的环节。

如上所述，由于默认情况下，Feign采用的是JDK的HttpURLConnection,所以整体性能并不高

---
#### 【梳理feign请求调用的过程】       
- feign组装HTTP请求 ：  
SynchronousMethodHandler.executeAndDecode 方法内部        response = client.execute(request, options);

```
使用IDEA,在服务提供方返回实体时，在实体内某个字段的getter方法打断点，查看调用栈

获取服务提供方的返回值：  ServletInvocableHandlerMethod.invokeAndHandle() 方法内部  
		Object returnValue = invokeForRequest(webRequest, mavContainer, providedArgs);

```

- IDEA 全局搜索 client.execute  ，查看请求其他服务的例子
```
1. LccServiceImpl       
2. EtermCommandUtil
3. JinTongApiHttpClient
3. JinTongSign
```
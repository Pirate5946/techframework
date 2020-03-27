其实，所有的请求都是从客户端出发，发送到特定的URL。   
当请求到达web容器，比如tomcat，它就会在web.xml中找出已经配置好的来处理特定的URL的Servlet或过滤器

Spring MVC是建立在Servlet之上的，
### [SpringMVC源码之Controller查找原理](https://www.cnblogs.com/w-y-c-m/p/8416630.html)
- 一个RequestMapping注解对应一个RequestMappingInfo对象

##### 1. 怎么根据请求找到方法
窄化请求映射可以认为是方法级别的@RequestMapping继承类级别的@RequestMapping。

DispatcherServlet.java
```
DispatcherServlet.doDispatch(){
    processedRequest = checkMultipart(request);
    // todo 这里匹配 请求
    mappedHandler = getHandler(processedRequest);  
    HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler()); 

    // 反射调用匹配到的方法
    mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

}
```
### [SpringMVC源码之参数解析绑定原理](https://www.cnblogs.com/w-y-c-m/p/8443892.html)
##### 2. 找到方法后，匹配入参
参数解析绑定总结
```
1. SpringMVC初始化时，RequestMappingHandlerAdapter类会把一些默认的参数解析器添加到argumentResolvers中。当SpringMVC接收到请求后首先根据url查找对应的HandlerMethod。

2. 遍历HandlerMethod的MethodParameter数组

3. 根据MethodParameter的类型来查找确认使用哪个HandlerMethodArgumentResolver，遍历所有的argumentResolvers的supportsParameter(MethodParameter parameter)方法。。如果返回true，则表示查找成功，当前MethodParameter，使用该HandlerMethodArgumentResolver。这里确认大多都是根据参数的注解已经参数的Type来确认。

4. 解析参数，从request中解析出MethodParameter对应的参数，这里解析出来的结果都是String类型。

5. 转换参数，把对应String转换成具体方法所需要的类型，这里就包括了基本类型、对象、List、Set、Map。
```
具体注入的方法是setPropertyValues，这个方法略复杂。    
它的职责简单总结起来就是根据属性名调用对应的set...方法。  
[比如注入User对象的name属性时，通过反射获取setName方法](https://www.cnblogs.com/w-y-c-m/p/8443892.html)

##### 3. 处理返回值  ModelAndView


#### [SpringMVC小结](https://www.cnblogs.com/larryzeal/p/5336042.html)
- @ResponseBody使Controller【直接返回数据】，而不是直接指向具体的视图；同时通过MessageConverter和produces="text/plain;charset=UTF-8"可以返回各种格式的数据（XML，json，RSS，TEXT，字节流等)
- @ResponseBody就是对应了HTTP协议里的响应体！！！
- ResponseEntity可以认为是ResponseHeader+ResponseBody。

---

### [Spring MVC处理HTTP请求的过程](https://blog.csdn.net/LeiXiaoTao_Java/article/details/83542907)
1. 监听客户端发来的请求、
2. DispatcherServlet根据配置文件或注解匹配到处理请求的 Controller（对应servlet）
3. 解析参数，绑定到方法的形参
4. Controller执行完逻辑后，将一个逻辑视图返回给 DispatcherServlet
5. DispatcherServlet询问视图解析器，直到确定好一个实际的视图来呈现输出为止。
6. 呈现的输出作为HTTP响应返回给客户端

---

ApplicationFilterChain.java
```
public void doFilter(ServletRequest request, ServletResponse response)
        throws IOException, ServletException {}
        
private void internalDoFilter(ServletRequest request,ServletResponse response)
        throws IOException, ServletException {}        

filter.doFilter(request, response, this);        
```

HttpServlet.java        
1. 判断方法 类型  GET/POST/HEAD/PUT/...
2. 
```
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getMethod();
    long lastModified;
    if (method.equals("GET")) {
        lastModified = this.getLastModified(req);
        if (lastModified == -1L) {
            this.doGet(req, resp);
        } else {
            long ifModifiedSince = req.getDateHeader("If-Modified-Since");
            if (ifModifiedSince < lastModified) {
                this.maybeSetLastModified(resp, lastModified);
                this.doGet(req, resp);
            } else {
                resp.setStatus(304);
            }
        }
    } else if (method.equals("HEAD")) {
        lastModified = this.getLastModified(req);
        this.maybeSetLastModified(resp, lastModified);
        this.doHead(req, resp);
    } else if (method.equals("POST")) {
        this.doPost(req, resp);
    } else if (method.equals("PUT")) {
        this.doPut(req, resp);
    } else if (method.equals("DELETE")) {
        this.doDelete(req, resp);
    } else if (method.equals("OPTIONS")) {
        this.doOptions(req, resp);
    } else if (method.equals("TRACE")) {
        this.doTrace(req, resp);
    } else {
        String errMsg = lStrings.getString("http.method_not_implemented");
        Object[] errArgs = new Object[]{method};
        errMsg = MessageFormat.format(errMsg, errArgs);
        resp.sendError(501, errMsg);
    }
}
```

包装 http请求
```
public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        this.service(request, response);
    } else {
        throw new ServletException("non-HTTP request or response");
    }
}
```

以 POST请求为例

FrameworkServlet.java
```
protected final void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	processRequest(request, response);
}


```

DispatcherServlet.java
```
protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {}

protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {}
```


---

#### http请求流程 -  get请求
```
ApplicationFilterChain.doFilter(ServletRequest, ServletResponse) line: 166	
ApplicationFilterChain.internalDoFilter(ServletRequest, ServletResponse) line: 231	
 
//ApplicationFilterChain.internalDoFilter中执行的是DispatcherServlet.service(ServletRequest, ServletResponse)方法，
//但是DispatcherServlet中没有此方法，所以调用父类HttpServlet.service(ServletRequest, ServletResponse)的方法
DispatcherServlet(HttpServlet).service(ServletRequest, ServletResponse)
DispatcherServlet(FrameworkServlet).service(HttpServletRequest, HttpServletResponse)
DispatcherServlet(HttpServlet).service(HttpServletRequest, HttpServletResponse) line: 628
DispatcherServlet(FrameworkServlet).doGet(HttpServletRequest, HttpServletResponse) line: 866	
DispatcherServlet(FrameworkServlet).processRequest(HttpServletRequest, HttpServletResponse) line: 959	
DispatcherServlet.doService(HttpServletRequest, HttpServletResponse) line: 889	
DispatcherServlet.doDispatch(HttpServletRequest, HttpServletResponse) line: 949	
RequestMappingHandlerAdapter(AbstractHandlerMethodAdapter).handle(HttpServletRequest, HttpServletResponse, Object) line: 87	
RequestMappingHandlerAdapter.handleInternal(HttpServletRequest, HttpServletResponse, HandlerMethod) line: 779	
RequestMappingHandlerAdapter.invokeHandlerMethod(HttpServletRequest, HttpServletResponse, HandlerMethod) line: 852	
ServletInvocableHandlerMethod.invokeAndHandle(ServletWebRequest, ModelAndViewContainer, Object...) line: 102	
	//解析传入参数、调用方法、返回结果
	ServletInvocableHandlerMethod(InvocableHandlerMethod).invokeForRequest(NativeWebRequest, ModelAndViewContainer, Object...) line: 131	
		//解析传入参数
		ServletInvocableHandlerMethod(InvocableHandlerMethod).getMethodArgumentValues(NativeWebRequest, ModelAndViewContainer, Object...) line: 150	
			HandlerMethodArgumentResolverComposite.resolveArgument(MethodParameter, ModelAndViewContainer, NativeWebRequest, WebDataBinderFactory) line: 120	
				RequestParamMethodArgumentResolver(AbstractNamedValueMethodArgumentResolver).resolveArgument(MethodParameter, ModelAndViewContainer, NativeWebRequest, WebDataBinderFactory) line: 97	
 
 
		//调用处理方法，返回结果
		ServletInvocableHandlerMethod(InvocableHandlerMethod).doInvoke(Object...) line: 207	
	//处理返回值
	HandlerMethodReturnValueHandlerComposite.handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest) line: 78	
		RequestResponseBodyMethodProcessor.handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest) line: 175	
			//调用数据解析器
			RequestResponseBodyMethodProcessor(AbstractMessageConverterMethodProcessor).writeWithMessageConverters(T, MethodParameter, ServletServerHttpRequest, ServletServerHttpResponse) line: 184	
				//调用fastjson序列化结果参数
				FastJsonHttpMessageConverter.write(Object, Type, MediaType, HttpOutputMessage) line: 184	
//视图解析
RequestMappingHandlerAdapter.getModelAndView(ModelAndViewContainer, ModelFactory, NativeWebRequest) line: 1000	

```

#### POST请求
```
ApplicationFilterChain.internalDoFilter(ServletRequest, ServletResponse)
 
DispatcherServlet(HttpServlet).service(ServletRequest, ServletResponse)	
 
DispatcherServlet(HttpServlet).service(HttpServletRequest, HttpServletResponse)
 
 
org.springframework.web.servlet.FrameworkServlet.doPost(HttpServletRequest, HttpServletResponse)
org.springframework.web.servlet.FrameworkServlet.doService(HttpServletRequest, HttpServletResponse)
org.springframework.web.servlet.DispatcherServlet.doService(HttpServletRequest, HttpServletResponse)
DispatcherServlet.doDispatch(HttpServletRequest, HttpServletResponse)	
 
RequestMappingHandlerAdapter(AbstractHandlerMethodAdapter).handle(HttpServletRequest, HttpServletResponse, Object)
 
RequestMappingHandlerAdapter.handleInternal(HttpServletRequest, HttpServletResponse, HandlerMethod)	
 
//调用处理器方法，执行请求，处理结果并且返回视图解析器
RequestMappingHandlerAdapter.invokeHandlerMethod(HttpServletRequest, HttpServletResponse, HandlerMethod)	
 
//调用请求并且处理结果
ServletInvocableHandlerMethod.invokeAndHandle(ServletWebRequest, ModelAndViewContainer, Object...）	
 
//1、调用请求
ServletInvocableHandlerMethod(InvocableHandlerMethod).invokeForRequest(NativeWebRequest, ModelAndViewContainer, Object...)
//1.1、解析传入参数
ServletInvocableHandlerMethod(InvocableHandlerMethod).getMethodArgumentValues(NativeWebRequest, ModelAndViewContainer, Object...)	
//1.1.1
HandlerMethodArgumentResolverComposite.resolveArgument(MethodParameter, ModelAndViewContainer, NativeWebRequest, WebDataBinderFactory) line: 121	
//1.1.2
RequestResponseBodyMethodProcessor.resolveArgument(MethodParameter, ModelAndViewContainer, NativeWebRequest, WebDataBinderFactory) line: 129	
 
 
//1.2、执行方法，返回结果
ServletInvocableHandlerMethod(InvocableHandlerMethod).doInvoke(Object...)
 
//处理返回结果
HandlerMethodReturnValueHandlerComposite.handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)	
RequestResponseBodyMethodProcessor.handleReturnValue(Object, MethodParameter, ModelAndViewContainer, NativeWebRequest)	
RequestResponseBodyMethodProcessor(AbstractMessageConverterMethodProcessor).writeWithMessageConverters(T, MethodParameter, ServletServerHttpRequest, ServletServerHttpResponse)	
//调用实际序列化方法
FastJsonHttpMessageConverter.write(Object, Type, MediaType, HttpOutputMessage)	
org.springframework.http.converter.AbstractHttpMessageConverter.write(T, MediaType, HttpOutputMessage)
com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter.writeInternal(Object, HttpOutputMessage)

```
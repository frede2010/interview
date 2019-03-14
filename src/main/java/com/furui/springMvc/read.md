<h2>SpringMVC执行原理</h2>
　　DispatcherServlet表示前置控制器，是整个SpringMVC的控制中心。用户发出请求，DispatcherServlet接收请求并拦截请求。

　　假设请求链接是：http://localhost:9999/SpringMVC/input-product  ，如上url拆分成三部分：  
* http://localhost:9999 服务器域名  
* SpringMVC部署在服务器(http://localhost:9999)上的web站点  
* input-product表示控制器  
通过分析，如上url表示为：请求位于服务器localhost:9999上的SpringMVC站点的input-product控制器  
> 1.DispatcherServlet表示前置控制器，是整个SpringMVC的控制中心。用户发出请求，DispatcherServlet接收请求并拦截请求  
2.HandlerMapping为处理器映射。DispatcherServlet调用HandlerMapping,HandlerMapping根据请求url查找Handler  
3.HandlerExecution表示具体的Handler,其主要作用是根据url查找控制器，如上url被查找控制器为：input-product  
4.HandlerExecution将解析后的信息传递给DispatcherServlet,如解析控制器映射等  
5.HandlerAdapter表示处理器适配器，其按照特定的规则去执行Handler  
6.Handler让具体的Controller执行  
7.Controller将具体的执行信息返回给HandlerAdapter,如ModelAndView  
8.HandlerAdapter将视图逻辑名或模型传递给DispatcherServlet  
9.DispatcherServlet调用视图解析器(ViewResolver)来解析HandlerAdapter传递的逻辑视图名  
10.视图解析器将解析的逻辑视图名传给DispatcherServlet  
11.DispatcherServlet根据视图解析器解析的视图结果，调用具体的视图  
12.最终视图呈现给用户。

`DispatcherServlet 就是所谓的SpringMVC前端控制器，作为整个SpringMVC的控制中心`  
`HandlerMapping 主要用来解析请求url，解析出控制器，从而映射控制器`  
`HandlerAdapter 主要是调度Controller来处理业务逻辑等`  
`ViewResolver 接口主要作用是解析DispatcherServlet传递的逻辑视图名，并将解析结果传回给DispatcherServlet`


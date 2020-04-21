spring和springMvc：

1. spring是一个一站式的轻量级的java开发框架，核心是控制反转（IOC）和面向切面（AOP），针对于开发的WEB层(springMvc)、业务层(Ioc)、持久层(jdbcTemplate)等都提供了多种配置解决方案；

2. springMvc是spring基础之上的一个MVC框架，主要处理web开发的路径映射和视图渲染，属于spring框架中WEB层开发的一部分；

springMvc和springBoot：

1. springMvc属于一个企业WEB开发的MVC框架，涵盖面包括前端视图开发、文件配置、后台接口逻辑开发等，XML、config等配置相对比较繁琐复杂；

2. springBoot框架相对于springMvc框架来说，更专注于开发微服务后台接口，不开发前端视图，同时遵循默认优于配置，简化了插件配置流程，不需要配置xml，相对springmvc，大大简化了配置流程；

springBoot和springCloud：

1. spring boot使用了默认大于配置的理念，集成了快速开发的spring多个插件，同时自动过滤不需要配置的多余的插件，简化了项目的开发配置流程，一定程度上取消xml配置，是一套快速配置开发的脚手架，能快速开发单个微服务；

2. spring cloud大部分的功能插件都是基于springBoot去实现的，springCloud关注于全局的微服务整合和管理，将多个springBoot单体微服务进行整合以及管理；  springCloud依赖于springBoot开发，而springBoot可以独立开发；

总结：

1. Spring 框架就像一个家族，有众多衍生产品例如 boot、security、jpa等等。但他们的基础都是Spring的ioc、aop等. ioc 提供了依赖注入的容器， aop解决了面向横切面编程，然后在此两者的基础上实现了其他延伸产品的高级功能；

2. springMvc是基于Servlet 的一个MVC框架主要解决WEB开发的问题，因为Spring的配置非常复杂，各种XML、JavaConfig、servlet处理起来比较繁琐；

3. 为了简化开发者的使用，从而创造性地推出了springBoot框架，默认优于配置，简化了springMvc的配置流程；
但区别于springMvc的是，springBoot专注于微服务方面的接口开发，和前端解耦，虽然springBoot也可以做成springMvc前后台一起开发，但是这就有点不符合springBoot框架的初衷了；

4. 对于springCloud框架来说，它和springBoot一样，注重的是微服务的开发，但是springCloud更关注的是全局微服务的整合和管理，相当于管理多个springBoot框架的单体微服务；

* **三大核心注解** *

springboot最大的特点是无需配置XML文件，能自动扫描包路径装载并注入对象，并能做到自动配置需要的jar核心依赖。

默认情况下springboot它之所以能自动帮我们做这些事情，就是因为文章提到的核心注解。

当我们项目创建完成后，项目启动类XxxApplication.java会默认帮我们配置一个@SpringBootApplication注解：

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
打开@SpringBootApplication注解：

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
可以看到@SpringBootApplication注解下其中包含了@Configuration、@EnableAutoConfiguration和@ComponentScan三个主要核心注解：

1. @Configuration
org.springframework.context.annotation.Configuration
用来代替 applicationContext.xml 配置文件，所有这个配置文件里面能做到的事情都可以通过这个注解所在类来进行注册。

@SpringBootConfiguration

这个注解就是@Configuration注解的变体，只是用来修饰是springboot配置而已，或者可利于springboot后续的扩展，源码：

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface SpringBootConfiguration {
}
2.@EnableAutoConfiguration
org.springframework.boot.autoconfigure.EnableAutoConfiguration
开启自动配置，该注解会使springboot根据项目中依赖的jar包自动配置项目的配置项。如：我们添加了spring-boot-starter-web的依赖，项目中也就会引入spring mvc的依赖，springboot就会自动配置许多web需要的核心的依赖。

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
3.@ComponentScan
org.springframework.context.annotation.ComponentScan
开启组件扫描，该注解会自动扫描包路径下面的所有@Controller、@Service、@Repository、@Component的类，不配置包路径的话，在springboot中默认扫描@SpringBootApplication所在类的同级目录以及子目录下的相关注解。


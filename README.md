# ☕️SpringCloud Netflix learning

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.1.3-brightgreen.svg)
![build passing](https://img.shields.io/badge/build-passing-brightgreen.svg)

### 一、项目结构
+ basic（公用）：定义公用接口、公用类；

+ register(注册中心)1001：使用 SpringCloud Eureka 作为注册中心。
  + http://localhost:1001/  
  ![](/basic/pic/eureka1001.png)
  
+ provider-1(生产者)2001
+ provider-2(生产者)2002  

+ customer-1(消费者)3001
  + http://localhost:3001/api/app/swagger-ui.html  
  ![](/basic/pic/swagger3001.png)

+ config(配置中心)4001：使用 SpringCloud Config 作为配置中心。

+ zuul(服务网关)5001
  + http://localhost:5001/actuator/routes  
  ![](/basic/pic/ZuulRoutes.png)
  + 反向代理消费者
    + http://localhost:5001/customer-1/api/app/swagger-ui.html
    + http://localhost:5001/c1/api/app/demo/getDemo/1

+ oauth2(授权)6001：待定  
  ![](/basic/pic/oauth2.1.png)   
  ![](/basic/pic/oauth2.2.png)  

### 二、Spring Cloud Netflix
#### ✔️Spring Cloud Config（配置中心）

#### ✔️Spring Cloud Netflix Eureka（注册中心）
+ 当服务通过Eureka注册时，Eureka将在30s内等待3次健康检查，然后才能通过Eureka获取该服务。
+ Client客户端选择：
  + Discover
  + Rest
  + Feigh

#### Spring Cloud Netflix Ribbon（客户端负载均衡）

####  ✔️Spring Cloud Netflix Hystrix（熔断器）
+ 4种客户端弹性模式
  + 客户端负载均衡模式
  + 断路器模式 
  + 后备模式
  + 舱壁模式
  
+ 2种隔离模式
  + thread（默认）
    + 不会将父线程的上下文传递到Hystrix管理的线程；
    + 通过自定义HystrixConcurrencystrategy实现上下文传递；
  + semaphore 
    + 不使用单独的线程；

####  ✔️Spring Cloud Netflix Zuul（服务网关）
+ 3种映射路由方式：
  + 通过服务发现自动映射路由；
  + 使用服务发现手动映射路由；
  + 使用静态URL手动映射路由。

+ 3种过滤器：
  + 前置过滤器：看门人
  + 路由过滤器
  + 后置过滤器：记录返回信息
  
+ 作用：
  + （核心）反向代理
  + 横切关注点：安全性、日志记录、服务追踪

#### Spring Cloud Security + OAuth2（安全认证）

#### Spring Cloud Stream + Kafka（消息驱动）

#### Spring Cloud Sleuth + Zipkin（服务链路追踪）

### 三、Spring 
#### Spring Bean 生命周期
![bean](/basic/pic/springBean.png)
1. 实例化Bean对象
2. 设置对象属性（依赖注入）
3. 处理Aware接口
   + BeanNameAware接口：setBeanName()
   + BeanFactoryAware接口：setBeanFactory()
   + ApplicationContextAware接口：setApplicationContext()
4. BeanPostProcessor
   + 前置处理器：postProcessBeforeInitialization接口
   + 后置处理器：postProcessAfterInitialization接口
5. InitializingBean接口、init-method声明
   + afterPropertiesSet()
6. 使用Bean
7. DisposableBean接口、destroy-method声明
   + destory()

#### Spring Bean 作用域
+ singleton：默认，单例。
+ prototype：每次返回的都是一个新的实例。
+ request：每次HTTP请求都会创建一个新的Bean，适用于WebApplicationContext环境。
+ session：不同Session使用不同的实例。
+ global-session：同session作用域不同的是，所有的Session共享一个Bean实例。

#### Bean 条件化
+ Profile Bean：使用@Profile注解指定某个bean属于哪一个profile。在应用部署到相应的环境中时，只要确保相应的profile处于激活状态就可以执行相关的bean。
```
@Bean
@Profile("dev") //开发环境使用这个bean，通过嵌入式数据库获得DataSource
public DataSource embeddedDataSource(){
  return ...
}
```
```
spring.profiles.default=dev
spring.profiles.active=dev
```

+ 条件化的Bean
```
@Bean
//条件化的创建bean
@Conditional(MagicExistsCondition.class)
publc MagicBean magicBean(){
  return new MagicBean();
}
```
```
public class MagicExistsCondition implements Condition{
  @Override
  public boolean matches(ConditionContext context,AnnotatedTypeMetadata metadata){
    Environment env = context.getEnvironment();
    //假设此时需要的属性为“magic”
    return env.containsProperty("magic");
  }
}
```

#### Spring 事务
+ 5种事务隔离级别

|Isolation|隔离级别|不可避免|
|----|----|----|
|DEFAULT|默认|使用数据库默认级别|
|READ_UNCOMMITTED|读未提交|脏读、不可重复读、幻读|
|READ_COMMITTED|读已提交|不可重复读、幻读|
|REPEATABLE_READ|可重复读|幻读|
|SERIALIZABLE|串行化||

+ 7种事务传播行为

|Propagation|传播行为|
|----|----|
|REQUIRED|如果当前无事务则开启一个事务，否则加入当前事务。|
|SUPPORTS|如果当前有事务则加入当前事务。|
|MANDATORY|如果当前无事务则抛出异常，否则加入当前事务。|
|REQUIRES_NEW|如果当前无事务则开启一个事务，否则挂起当前事务并开启新事务。|
|NOT_SUPPORTED|如果当前有事务，则挂起当前事务以无事务状态执行方法。|
|NEVER|如果当前有事务，则抛出异常。|
|NESTED|创建一个嵌套事务，如果当前无事务则创建一个事务。|

+ 2种开启事务的方法
  + 声明式：注解@transactional、@EnableTransactionManagement
  + 编程式：Xml配Aop、tx标签

+ @transactional 失效情况：
  + 方法不是public；
  + 新建了调用对象；
  + unchecked、捕获异常；

#### JSON协议
+ 轻量级，传递数据没有太大的文本开销；
+ 易于人们的阅读和消费；
+ 是JavaScript使用的默认序列化协议；


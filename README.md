# Learn-SpringCloud
☕️SpringCloud Test

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.1.3-brightgreen.svg)
![build passing](https://img.shields.io/badge/build-passing-brightgreen.svg)

#### 项目结构
+ basic（公用）：定义公用接口、公用类；
+ customer-1(消费者)
  + http://localhost:3001/api/app/swagger-ui.html
  ![](/basic/pic/swagger3001.png)
+ provider-1(生产者)
  + http://localhost:2001
+ provider-2(生产者)
  + http://localhost:2002
+ register(注册中心)：使用eureka作为注册中心。
  + http://localhost:1001/
  ![](/basic/pic/eureka1001.png)

#### Spring Cloud
+ Spring Cloud Config：配置管理中心
+ ✔️Spring Cloud Rureka：注册中心
+ Spring Cloud Hystrix：熔断器
+ Spring Cloud Zuul：服务网关
+ Spring Cloud Security + OAuth2：安全认证
+ Spring Cloud Stream + Kafka ：消息驱动
+ Spring Cloud Sleuth + Zipkin：服务链路追踪

#### Spring 
##### Spring Bean 生命周期
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

##### Spring Bean 作用域
+ singleton：默认，单例。
+ prototype：每次返回的都是一个新的实例。
+ request：每次HTTP请求都会创建一个新的Bean，适用于WebApplicationContext环境。
+ session：不同Session使用不同的实例。
+ global-session：同session作用域不同的是，所有的Session共享一个Bean实例。

##### Bean 条件化
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

##### Spring 事务
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


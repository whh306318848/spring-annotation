package com.atguigu.config;

import com.atguigu.aop.LogAspects;
import com.atguigu.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: wuhaohua
 * @date: Created in 2021/1/13 15:43
 * @description: AOP配置类
 * AOP【动态代理】：指在程序运行期间，动态的将某段代码切入到指定方法指定位置进行运行的变成方式
 *
 * 使用AOP步骤：
 * 1、在pom文件中导入AOP模块，String AOP模块做了一些简化，只需要配置几个注解就可以运行起来
 * 2、定义业务逻辑类MathCalculator，在业务逻辑运行的时候将日志进行打印（方法运行之前、方法运行结束、方法出现异常等情况）
 * 3、定义一个日志切面类LogAspects，切面类里面的方法需要动态感知MathCalculator.div运行到哪里进行执行
 *      通知方法：
 *          前置通知（@Before）：相当于logStart，在目标方法运行之前运行
 *          后置通知（@After）：相当于logEnd，在目标方法运行结束之后运行（无论方法正常结束还是异常结束都调用）
 *          返回通知（@AfterReturning）：相当于logReturn，在目标方法正常返回之后运行
 *          异常通知（@AfterThrowing）：相当于logException，在目标方法运行出现异常以后运行
 *          环绕通知（@Around）：其是一个动态代理，可以手动推进目标方法运行（joinPoint.proceed()），是最底层的通知
 * 4、给切面类的目标方法标注何时何地运行（标志通知注解）
 * 5、将切面类和业务逻辑类（目标方法所在类）都加入到容器中
 * 6、必须告诉Sring哪个类是切面类，给切面类上加上@Aspect注解
 * 7、给配置类上加@EnableAspectJAutoProxy注解，开启基于注解的AOP模式
 *      在Spring中有很多的@EnableXXX注解，其作用都是开启XXX
 *
 * 使用Spring AOP最重要三步总结：
 *  1、将业务逻辑组件和切面类都加入到容器中，并告诉Spring哪个是切面类（@Aspect注解）
 *  2、在切面类上的每一个通知方法上标注通知注解，告诉Spring何时何地运行（切入点表达式）
 *  3、开启基于注解的AOP模式（@EnableAspectJAutoProxy注解）
 *
 * AOP原理：【看给容器中注册了什么组件，这个组件什么时候工作，以及这个组件的功能是什么】
 *      @EnableAspectJAutoProxy：
 *          1、@EnableAspectJAutoProxy是什么？
 *              使用@Import(AspectJAutoProxyRegistrar.class)注解给容器中导入AspectJAutoProxyRegistrar
 *              利用AspectJAutoProxyRegistrar自定义给容器中注册一个名字是internalAutoProxyCreator，类型是AnnotationAwareAspectJAutoProxyCreator（自动代理的创建器）的组件
 *          2、AnnotationAwareAspectJAutoProxyCreator：
 *              -> AspectJAwareAdvisorAutoProxyCreator
 *                  -> AbstractAdvisorAutoProxyCreator
 *                      -> AbstractAutoProxyCreator
 *                          implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                          关注后置处理器（在bean初始化完成前后做事情）、自动装配BeanFactory
 *              通过分析上述继承结构可知，AspectJAutoProxyCreator具有BeanPostProcessor的特点（其实现了SmartInstantiationAwareBeanPostProcessor接口），同时也有Aware接口的特点（其实现了BeanFactoryAware接口），相当于AnnotationAwareAspectJAutoProxyCreator既是一个后置处理器，也是一个有Aware接口的实现类
 *
 *          AbstractAutoProxyCreator.setBeanFactory()
 *          AbstractAutoProxyCreator.有后置处理器的逻辑；
 *
 *          AbstractAdvisorAutoProxyCreator.setBeanFactory()->initBeanFactory
 *
 *          AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 *
 *          流程：
 *              1) 传入配置类，创建IOC容器
 *              2) 注册配置类，调用refresh()刷新容器（把容器中的所有bean创建出来，所有功能都准备好）
 *              3) registerBeanPostProcessors(beanFactory)；注册bean的后置处理器来方便拦截bean的创建
 *                  a) 先获取IOC容器中已经定义了的需要创建对象的所有BeanPostProcessor
 *                  b) 给容器中增加其他的BeanPostProcessor
 *                  c) 优先注册实现了PriorityOrdered接口的BeanPostProcessor
 *                  d) 再给容器中注册实现了Ordered接口的BeanPostProcessor
 *                  e) 再注册没实现优先级接口的BeanPostProcessor
 *                  f) 注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中
 *                      创建internalAutoProxyCreator的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】
 *                      1. 创建Bean的实例
 *                      2. populateBean()：给bean的各种属性赋值
 *                      3. initializeBean()：初始化bean
 *                          a. invokeAwareMethods()：处理Aware接口的方法回调
 *                          b. applyBeanPostProcessorsBeforeInitialization()：应用后置处理器的postProcessorsBeforeInitialization()
 *                          c. invokeInitMethods()：执行自定义的初始化方法
 *                          d. applyBeanPostProcessorsAfterInitialization()：执行后置处理器的postProcessorsAfterInitialization()
 *                      4. BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功，并调用initBeanFactory()得到aspectJAdvisorFactory
 *                  g) 把BeanPostProcessor注册到BeanFactory中：beanFactory.addBeanPostProcessor(postProcessor);
 * =========================以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程================================
 * 根据AnnotationAwareAspectJAutoProxyCreator源码集成树形结构可知，AnnotationAwareAspectJAutoProxyCreator是InstantiationAwareBeanPostProcessor类型的后置处理器
 *              4) finishBeanFactoryInitialization(beanFactory);完成beanFactory的初始化：创建剩下的单实例Bean
 *                  a) 遍历获取容器中所有的Bean，依次调用getBean(beanName)创建对象
 *                      函数调用栈：getBean(beanName)->doGetBean(beanName, null, null, false)->getSingleton()
 *                  b) 创建bean；
 *                      【AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截，InstantiationAwareBeanPostProcessor，会调用postProcessBeforeInitialization()】
 *                      1. 先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的，直接使用，否则，再创建；只要创建好的bean都会被缓存起来
 *                      2. createBean()：创建bean
 *                          AnnotationAwareAspectJAutoProxyCreator会在任何Bean创建之前先尝试返回Bean的实例
 *                          【BeanPostProcessor是在Bean实例创建完成初始化前后调用的】
 *                          【InstantiationAwareBeanPostProcessor是在创建Bean实例之前先尝试用后置处理器返回对象的】
 *                          a. resolveBeforeInstantiation(beanName, mbdToUse);解析BeforeInstantiation
 *                              希望后置处理器在此能返回一个代理对象，如果能返回代理对象就使用，如果不能就继续下一步
 *                              Ⅰ. 后置处理器先尝试返回对象
 *                                  bean = applyBeanPostProcessorsBeforeInstantiation();
 *                                      拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor就执行后置处理器的postProcessorBeforeInstantiation()方法
 *                                  if (bean != null) {
 *                                      bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                                  }
 *                              Ⅱ.
 *                              Ⅲ.
 *                              Ⅳ.
 *                          b. doCreateBean(beanName, mbdToUse，args);真正的去创建一个bean实例；和3) f) 流程一样
 *
 * AnnotationAwareAspectJAutoProxyCreator【是InstantiationAwareBeanPostProcessor类型的后置处理器】的作用：
 * 1、每一个bean创建之前，调用postProcessorBeforeInstantiation()方法
 *      关心MathCalculator和LogAspects的创建
 *      1) 判断当前bean是否在advisedBean中（advisedBean保存了所有需要增强的bean，即需要切面切入的bean）
 *      2) 判断当前bean是否是基础类型的bean（即是否实现了Advice、Pointcut、Advisor、AopInfrastructureBean接口），或者是否是切面（标注了@Aspect注解）
 *      3) 判断是否需要跳过
 *          a) 获取候选的增强器（即切面里面的通知方法）【List<Advisor> candidateAdvisors】
 *              每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor
 *              判断每一个增强器是否是AspectJPointcutAdvisor类型的，如果是则返回true
 *          b）永远返回false
 * 2、创建对象
 * 3、每一个bean创建之后，调用postProcessAfterInstantiation()方法
 *      1) postProcessAfterInstantiation()方法中，执行return wrapIfNecessary(bean, beanName, cacheKey);在需要的情况下，对bean进行包装，判断是否包装的依据：
 *          与postProcessorBeforeInstantiation()方法中类似，判断是否寄出类型的bean，是否需要跳过
 *          a) 获取当前bean的所有增强器（通知方法） Object[] specificInterceptors
 *              1. 找到候选的所有增强器（即通知方法），找哪些通知方法是需要切入当前bean方法的
 *              2. 获取到能在当前bean使用的增强器
 *              3. 对增强器进行排序
 *          b) 保存当前bean在advisedBeans中
 *          c) 如果当前bean需要增强，调用createProxy()创建当前bean的代理对象
 *              1. 获取所有增强器（即通知方法）
 *              2. 保存到ProxyFactory中
 *              3. 创建代理对象，Spring自动决定
 *                  JdkDynamicAopProxy(config);jdk动态代理，如果bean的类实现了接口，jdk能创建动态代理，就创建jdk动态代理
 *                  ObjenesisCglibAopProxy(config);cglib动态代理，如果bean的类没有实现接口，jdk也没法创建动态代理，就创建cglib动态代理
 *          d) 给容器中返回当前组件使用cglib增强了的代理对象
 *          e) 以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
 * 4、目标方法的执行
 *      容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象等等）
 *      1) CglibAopProxy的intercept方法，拦截目标方法的执行
 *      2) 根据ProxyFactory对象获取将要执行的目标方法的拦截器链
 *          List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *          a) List<Object> interceptorList保存所有拦截器5
 *              一个默认的ExposeInvocationInterceptor 和 四个增强器
 *          b) 遍历所有的增强器，将其转为Interceptor：registry.getInterceptors(advisor)
 *          c) 将增强器转为List<MethodInterceptor>
 *              如果是MethodInterceptor，直接加入到集合中
 *              如果不是MethodInterceptor，使用AdvisorAdapter将增强器转为MethodInterceptor
 *              转换完成，返回MethodInterceptor数组
 *      3) 如果没有拦截器链，则直接执行目标方法
 *          拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 *      4) 如果有拦截器链，把需要执行的目标对象、目标方法、拦截器链、参数等信息传入，创建一个CglibMethodInvocation对象，并调用其proceed()方法，该方法有返回值Object retVal = mi.proceed();
 *      5) 拦截器链的触发过程
 *          a) 如果没有拦截器执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（指定到了最后一个拦截器）执行目标方法
 *          b) 链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再来执行（嵌套）
 *              拦截器链的机制，保证了通知方法与目标方法的执行顺序
 *
 * 5、总结：
 *      1) @EnableAspectJAutoProxy 开启AOP功能
 *      2) @EnableAspectJAutoProxy 会给容器中注册一个组件AnnotationAwareAspectJAutoProxyCreator
 *      3) AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 *      4) 容器的创建流程
 *          a) registerBeanPostProcessors()注册后置处理器，创建AnnotationAwareAspectJAutoProxyCreator对象
 *          b) finishBeanFactoryInitialization()初始化剩下的单实例bean
 *              1. 创建业务逻辑组件和切面组件
 *              2. AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 *              3. 组件创建完之后，判断组件是否需要增强，如果是，就把切面的通知方法包装成增强器（Advisor），然后给业务逻辑组件创建一个代理对象（cglib）
 *      5) 执行目标方法：
 *          a) 代理对象执行目标方法
 *          b) CglibAopProxy.intercept()方法进行拦截
 *              1. 得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
 *              2. 利用拦截器的链式机制，依次进入每一个拦截器进行执行
 *              3. 执行效果：
 *                  正常执行：前置通知->目标方法->后置通知->返回通知
 *                  出现异常：前置通知->目标方法->后置通知->异常通知
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    // 将业务逻辑类加入容器中
    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    // 将切面类加入容器中
    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}

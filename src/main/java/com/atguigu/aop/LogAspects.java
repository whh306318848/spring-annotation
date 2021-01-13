package com.atguigu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;

/**
 * @author: wuhaohua
 * @date: Created in 2021/1/13 15:55
 * @description: 切面类
 * @Aspect注解：告诉Spring当前类是一个切面类
 */
@Aspect
public class LogAspects {

    // 抽取公共的切入点表达式
    // 1、如果是本类引用，则@Pointcut的值不写任何内容
    // 2、其他的切面引用，则@Pointcut要写execution(切入点表达式，即指定在哪个方法切入)
    @Pointcut("execution(public * com.atguigu.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    // @Before注解是指在目标方法之前切入，其值是切入点表达式（指定在哪个方法切入）
    @Before("execution(public * com.atguigu.aop.MathCalculator.*(..))")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "运行...@Before：参数列表是：{" + Arrays.asList(joinPoint.getArgs()) + "}");
    }

    // @After注解是指在目标方法运行结束之后切入
    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "结束...@After");
    }

    // @AfterReturning注解是指在目标方法正常返回之后切入
    // JoinPoint一定要写在参数表的第一位
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "正常返回...@AfterReturning：运行结果：{" + result + "}");
    }

    // @AfterThrowing注解是指在目标方法出现异常之后切入
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println(joinPoint.getSignature().getName() + "异常...@AfterThrowing：异常信息：{" + exception + "}");
    }
}

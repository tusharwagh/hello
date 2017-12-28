package hello;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Configuration
public class ApplicationAspect {

    @Around("execution(* hello.domain.*.*(..)) && @annotation(com.lms.common.annotation.Loggable)")
    public Object beforeMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("aspect work "+joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        System.out.println("aspect work done "+joinPoint.getSignature().getName());
        return result;
    }

}

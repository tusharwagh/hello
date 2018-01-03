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

    @Around("execution(* hello.domain.*.*(..)) && @annotation(com.lms.common.annotation.TrackTime)")
    public Object beforeMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger LOGGER = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType().getName());
        LOGGER.info("aspect work "+joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        LOGGER.info("aspect work done "+joinPoint.getSignature().getName());
        return result;
    }

}

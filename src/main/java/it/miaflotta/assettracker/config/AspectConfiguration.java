package it.miaflotta.assettracker.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectConfiguration {
    @Around("@annotation(it.miaflotta.assettracker.annotations.MethodExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info(joinPoint.getSignature().getDeclaringTypeName() + ":" + joinPoint.getSignature().getName() + " [START]");
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info(joinPoint.getSignature().getDeclaringTypeName() + ":" + joinPoint.getSignature().getName() + " [END] in " + executionTime + "ms");
        return proceed;
    }
}


package org.remipassmoilesel.springplayground.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Conditional(value = ConditionalAspect.EnvironmentCondition.class)
@Aspect
public class ConditionalAspect {

    private Logger logger = LoggerFactory.getLogger(ConditionalAspect.class);

    public static class EnvironmentCondition implements Condition {
        @Override
        public boolean matches(ConditionContext c, AnnotatedTypeMetadata m) {
            String redirectAddress = System.getenv("ENABLE_ASPECT");
            return redirectAddress != null && !redirectAddress.isEmpty();
        }
    }

    public ConditionalAspect() {
        logger.info("ConditionalAspect created");
    }

    @Around("execution(* org.remipassmoilesel.springplayground.aspect.ExampleService.getList(..))")
    public Object changeItem(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("changeItem: " + Arrays.asList(joinPoint.getArgs()).toString());
        String newItem = joinPoint.getArgs()[0] + "-modified";

        return joinPoint.proceed(new Object[]{newItem});
    }

}

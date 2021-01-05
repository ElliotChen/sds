package tw.elliot.log.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import tw.elliot.log.utils.ThreadUtils;

@Component
@Aspect
@Slf4j
public class StopAspect {


	@Pointcut("within(tw.elliot.log.service..*)")
	public void inService() {}

	@Around("inService()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		String name = pjp.getSignature().getName();
		log.info("Aspect for [{}]", name);
		// start stopwatch
		ThreadUtils.startWatch(name);
		ThreadUtils.startProfiler(name);
		Object retVal = pjp.proceed();
		// stop stopwatch
		ThreadUtils.stopWatch();
		return retVal;
	}
}

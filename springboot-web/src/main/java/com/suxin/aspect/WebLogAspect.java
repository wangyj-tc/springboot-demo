package com.suxin.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.suxin.annotation.WebLog;

@Aspect
@Component
public class WebLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	@Pointcut("@annotation(com.suxin.annotation.WebLog)")
	public void logPointCut() {
	}

	@Before("logPointCut()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 拦截的实体类
		Object target = joinPoint.getTarget();
		// 拦截的方法名称
		String methodName = joinPoint.getSignature().getName();
		// 拦截的方法参数
		Object[] argsa = joinPoint.getArgs();
		// 拦截的放参数类型
		Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
		Method method = target.getClass().getMethod(methodName, parameterTypes);
		WebLog webLogParam = method.getAnnotation(WebLog.class);
		String remark = webLogParam.remark();
		// 接收到请求，记录请求内容
		// 记录下请求内容
		logger.info("HTTP METHOD : {},请求方法 : {},请求参数 : {},备注方法名：{}", methodName,
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
				Arrays.toString(joinPoint.getArgs()), remark);
	}

	@AfterReturning(returning = "ret", pointcut = "logPointCut()") // returning的值和doAfterReturning的参数名一致
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容(返回值太复杂时，打印的是物理存储空间的地址)
		logger.info("返回值 : {}", ret);
	}

	@Around("logPointCut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object ob = pjp.proceed();// ob 为方法的返回值
		logger.info("耗时 : " + (System.currentTimeMillis() - startTime));
		return ob;
	}
}

package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDAOPackage() {
	}

	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {
	}

	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		System.out.println("=========> in @Before : calling method : " + joinPoint.getSignature().toShortString());
		for (Object arg : joinPoint.getArgs())
			System.out.println("=========> in @Before : argument : " + arg);
	}

	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println(
				"=========> in @AfterReturning : calling method : " + joinPoint.getSignature().toShortString());
		System.out.println("=========> in @AfterReturning : result : " + result);
	}
}

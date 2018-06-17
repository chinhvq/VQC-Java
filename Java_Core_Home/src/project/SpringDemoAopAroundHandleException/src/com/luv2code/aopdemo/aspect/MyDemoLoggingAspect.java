package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	@Before("com.luv2code.aopdemo.aspect.LoveAOPExpression.forDAOPackageNOTGetterORSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {
		System.out.println("\n===================>>> Executing @Before advice on AddAcount");

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		System.out.println("Method calling - " + methodSignature);

		Object[] args = joinPoint.getArgs();
		for (Object account : args) {
			System.out.println(account);
			if (account instanceof Account) {
				System.out.println("Account Name -\t" + ((Account) account).getName());
				System.out.println("Account Level -\t" + ((Account) account).getLevel());
			}
		}
	}

	@AfterReturning(pointcut = "com.luv2code.aopdemo.aspect.LoveAOPExpression.findAccount()", returning = "accounts")
	public void afterFindAccount(JoinPoint joinPoint, List<Account> accounts) {
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n===================>>> Executing @AfterReturning on method - " + method);
		accounts.get(0).setName("God");
		accounts.forEach(account -> account.setName(account.getName().toUpperCase()));
	}

	@AfterThrowing(pointcut = "com.luv2code.aopdemo.aspect.LoveAOPExpression.findAccount()", throwing = "exception")
	public void AfterThrowingFindAccount(JoinPoint joinPoint, Throwable exception) {
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n===================>>> Executing @AfterThrowing on method - " + method);
		System.out.println("The exception is " + exception);
	}

	@After("com.luv2code.aopdemo.aspect.LoveAOPExpression.findAccount()")
	public void AfterFinallyfindAccount(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n===================>>> Executing @After Finally on method - " + method);
	}

	@Around("com.luv2code.aopdemo.aspect.LoveAOPExpression.getFortune()")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String method = proceedingJoinPoint.getSignature().toShortString();
		System.out.println("\n===================>>> Executing @Around on method - " + method);
		long begin = System.currentTimeMillis();
		Object result = null;
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			System.out.println("Exception - " + e.getMessage());
			result = "Major accident! Your private AOP hellicopter is on the way";
		}
		long end = System.currentTimeMillis();
		System.out.println("Duration is - " + (end - begin) / 1000.0 + " seconds");
		return result;
	}
}

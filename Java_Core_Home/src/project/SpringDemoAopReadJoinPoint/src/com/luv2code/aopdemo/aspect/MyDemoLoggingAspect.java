package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
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
}

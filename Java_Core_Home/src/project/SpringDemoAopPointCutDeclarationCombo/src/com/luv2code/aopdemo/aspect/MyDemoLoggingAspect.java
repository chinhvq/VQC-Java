package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	private void forDAOPackage() {
	}

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	private void forDAOPackageGetterMethod() {
	}

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	private void forDAOPackageSetterMethod() {
	}

	@Pointcut("forDAOPackage() && !(forDAOPackageGetterMethod() || forDAOPackageSetterMethod())")
	private void forDAOPackageNOTGetterORSetterMethod() {
	}

	@Before("forDAOPackageNOTGetterORSetterMethod()")
	public void beforeAddAccountAdvice() {
		System.out.println("\n===================>>> Executing @Before advice on AddAcount");
	}

	@Before("forDAOPackageNOTGetterORSetterMethod()")
	public void performAPIAnalytics() {
		System.out.println("\n===================>>> Perform API analytics");
	}

}

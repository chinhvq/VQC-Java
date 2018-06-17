package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoveAOPExpression {

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	public void forDAOPackage() {
	}

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	public void getter() {
	}

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	public void setter() {
	}

	@Pointcut("forDAOPackage() && !(getter() || setter())")
	public void forDAOPackageNOTGetterORSetter() {
	}
}

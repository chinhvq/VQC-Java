package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterFinallyDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfigure.class);
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		try {
			boolean tripWire = false;
			List<Account> accounts = accountDAO.findAccount(tripWire);
			System.out.println("\nAccount list");
			accounts.forEach(account -> System.out.println("\t" + account));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		context.close();
	}
}

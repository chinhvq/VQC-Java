package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoClass {
	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfigure.class);
		// get the bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		// call the business method
		accountDAO.addAccount();
		accountDAO.addAccount(new Account());
		accountDAO.addAccount(new Account(), true);
		MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		membershipDAO.addAccount();
		membershipDAO.addSomething();
		// close the context
		context.close();
	}
}

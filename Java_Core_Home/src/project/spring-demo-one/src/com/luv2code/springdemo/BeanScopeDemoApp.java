package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		Coach theCoach = context.getBean("myCoach", Coach.class);
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		System.out.println(theCoach == alphaCoach);
		System.out.println("Default memory location of theCoach = " + theCoach);
		System.out.println("Default memory location of alphaCoach = " + alphaCoach);
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getRandomFortune());
		System.out.println(alphaCoach.getRandomFortune());
		context.close();
	}

}

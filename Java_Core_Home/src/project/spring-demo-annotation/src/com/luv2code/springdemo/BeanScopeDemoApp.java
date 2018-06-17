package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("Running from XML Configuration");
		Coach theCoach = context.getBean("tennisCoachFiedInjection", Coach.class);
		Coach alphaCoach = context.getBean("tennisCoachFiedInjection", Coach.class);
		System.out.println(theCoach == alphaCoach);
		System.out.println("Default memory location of theCoach = " + theCoach);
		System.out.println("Default memory location of alphaCoach = " + alphaCoach);
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getRandomFortune());
		System.out.println(alphaCoach.getRandomFortune());
		
		theCoach = context.getBean("tennisCoach", Coach.class);
		alphaCoach = context.getBean("tennisCoach", Coach.class);
		System.out.println(theCoach == alphaCoach);
		System.out.println("Default memory location of theCoach = " + theCoach);
		System.out.println("Default memory location of alphaCoach = " + alphaCoach);
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getRandomFortune());
		System.out.println(alphaCoach.getRandomFortune());
		context.close();
	}

}

package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoClass {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// Coach myCoach = context.getBean("coach", Coach.class);
		Coach myCoach = context.getBean("tennisCoach", Coach.class);
		System.out.println("Constructor Injection");
		System.out.println(myCoach.getDailyWorkout());
		System.out.println(myCoach.getDailyFortune());
		System.out.println(myCoach.getRandomFortune());

		System.out.println("=======================");
		System.out.println("Setter Injection");
		myCoach = context.getBean("tennisCoachSetter", Coach.class);
		System.out.println(myCoach.getDailyWorkout());
		System.out.println(myCoach.getRandomFortune());

		System.out.println("=======================");
		System.out.println("Method Injection");
		myCoach = context.getBean("tennisCoachMethodInjection", Coach.class);
		System.out.println(myCoach.getDailyWorkout());
		System.out.println(myCoach.getRandomFortune());

		System.out.println("=======================");
		System.out.println("Field Injection");
		myCoach = context.getBean("tennisCoachFiedInjection", Coach.class);
		System.out.println(myCoach.getDailyWorkout());
		System.out.println(myCoach.getRandomFortune());

		context.close();
	}

}

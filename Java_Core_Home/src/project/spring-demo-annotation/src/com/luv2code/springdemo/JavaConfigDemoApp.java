package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigDemoApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
		System.out.println("Running from Configuration Class");
		System.out.println("==========================");
		Coach theCoach = context.getBean("tennisCoachFiedInjection", Coach.class);
		Coach alphaCoach = context.getBean("tennisCoachFiedInjection", Coach.class);
		System.out.println(theCoach == alphaCoach);
		System.out.println("Default memory location of theCoach = " + theCoach);
		System.out.println("Default memory location of alphaCoach = " + alphaCoach);
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getRandomFortune());
		System.out.println(alphaCoach.getRandomFortune());

		System.out.println("==========================");
		theCoach = context.getBean("tennisCoach", Coach.class);
		alphaCoach = context.getBean("tennisCoach", Coach.class);
		System.out.println(theCoach == alphaCoach);
		System.out.println("Default memory location of theCoach = " + theCoach);
		System.out.println("Default memory location of alphaCoach = " + alphaCoach);
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getRandomFortune());
		System.out.println(alphaCoach.getRandomFortune());

		System.out.println("==========================");
		theCoach = context.getBean("swimCoach", Coach.class);
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getRandomFortune());

		System.out.println("==========================");
		SwimCoach swimCoach = context.getBean("swimCoach", SwimCoach.class);
		System.out.println(swimCoach.getEmail() + " : " + swimCoach.getTeam());
		context.close();
	}

}

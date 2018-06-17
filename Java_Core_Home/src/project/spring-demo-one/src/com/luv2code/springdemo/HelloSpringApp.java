package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {
		// load the Spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// retrieve Bean from Spring Container
		Coach coach = context.getBean("myCoach", Coach.class);
		// // call method on the bean
		System.out.println(coach.getDailyWorkout());
		System.out.println(coach.getDailyFortune());

		// demo DI setter method
		System.out.println("=========================");
		CricketCoach cricketCoach = context.getBean("myCricketCoach", CricketCoach.class);
		System.out.println(cricketCoach.getDailyWorkout());
		System.out.println(cricketCoach.getRandomFortune());
		System.out.println(cricketCoach.getDailyFortune());
		System.out.println(cricketCoach.getEmailAddress());
		System.out.println(cricketCoach.getTeam());

		// close the context
		context.close();
	}

}

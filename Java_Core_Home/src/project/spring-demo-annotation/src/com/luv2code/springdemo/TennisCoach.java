package com.luv2code.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("coach")
@Component
public class TennisCoach implements Coach {

	private FortuneService fortuneService;

	@Autowired
	public TennisCoach(@Qualifier("happyFortuneService") FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	@Override
	public String getRandomFortune() {
		return fortuneService.getFortuneArray();
	}

	@PostConstruct
	private void doMyStartupStuff() {
		System.out.println("Tennis Coach : Start up ");
	}

	@PreDestroy
	private void doMyCleanupStuff() {
		System.out.println("Tennis Coach : Clean up ");
	}

}

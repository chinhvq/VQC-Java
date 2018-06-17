package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("coach")
@Component
public class TennisCoachMethodInjection implements Coach {
	private FortuneService fortuneService;

	@Autowired
	@Qualifier("askMeNow")
	public void methodInjection(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice your forth-hand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	@Override
	public String getRandomFortune() {
		return fortuneService.getFortuneArray();
	}

}

package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component("coach")
@Component
@Scope("prototype")
public class TennisCoachFiedInjection implements Coach {
	@Autowired
	@Qualifier("askMeNow")
	private FortuneService fortuneService;
	
	@Override
	public String getDailyWorkout() {
		return "Practice your backhand and forth-hand volley";
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

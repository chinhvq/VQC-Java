package com.luv2code.springdemo;

public class BaseballCoach implements Coach {

	// define private field for dependency
	private FortuneService fortuneService;

	// define a constructor for dependency injection
	public BaseballCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	public BaseballCoach() {
	}

	@Override
	public String getDailyWorkout() {
		return "Spend 30 mins on batting practice";
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

package com.luv2code.springdemo;

public class MyApp {

	public static void main(String[] args) {
		Coach baseballCoach = new BaseballCoach();
		System.out.println(baseballCoach.getDailyWorkout());
		
		Coach trackCoach = new TrackCoach();
		System.out.println(trackCoach.getDailyWorkout());
	}

}

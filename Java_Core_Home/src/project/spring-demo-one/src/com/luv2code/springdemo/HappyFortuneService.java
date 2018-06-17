package com.luv2code.springdemo;

public class HappyFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		return "Today is your lucky day";
	}

	@Override
	public String getFortuneArray() {
		String[] fortune = new String[3];
		fortune[0] = "Today is your lucky day";
		fortune[1] = "Yesterday was your lucky day";
		fortune[2] = "Tomorow will be your lucky day";
		int ranFortune = (int) Math.floor(Math.random() * 3);
		System.out.println(ranFortune);
		return fortune[ranFortune];
	}

}

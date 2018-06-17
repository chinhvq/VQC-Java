package com.luv2code.springdemo;

import java.util.Random;

public class SadFortuneService implements FortuneService {
	private String[] fortune = new String[3];
	
	@Override
	public String getFortune() {
		return "Today is a rainning day";
	}

	@Override
	public String getFortuneArray() {
		fortune[0] = "Today The cat eat the dog";
		fortune[1] = "Yesterday The river dried over";
		fortune[2] = "Tomorow I am not so sure about the storm";
		int ranFortune = new Random().nextInt(fortune.length);
		return fortune[ranFortune];
	}

}

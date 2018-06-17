package com.luv2code.springdemo;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class HappyFortuneService implements FortuneService {
	private String[] fortune = new String[3];
	@Override
	public String getFortune() {
		return "Today is your lucky day";
	}

	@Override
	public String getFortuneArray() {
		fortune[0] = "Today is your lucky day";
		fortune[1] = "Yesterday was your lucky day";
		fortune[2] = "Tomorow will be your lucky day";
		int ranFortune = new Random().nextInt(fortune.length);
		return fortune[ranFortune];
	}

}

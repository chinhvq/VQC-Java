package com.luv2code.springdemo;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component("askMeNow")
public class AskFortuneService implements FortuneService {
	private String[] fortune = new String[3];

	@Override
	public String getFortune() {
		return "Today is your lucky day - Good Luck";
	}

	@Override
	public String getFortuneArray() {
		fortune[0] = "Beware of the wolf in the sheep 's clothing - Good Luck";
		fortune[1] = "Diligence is the mother of good luck - Good Luck";
		fortune[2] = "The journey is the reward - Good Luck";
		int ranFortune = new Random().nextInt(fortune.length);
		return fortune[ranFortune];
	}

}

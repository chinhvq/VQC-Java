package com.luv2code.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {
	public String getFortune(boolean tripWire) {
		if (tripWire) {
			throw new RuntimeException("Major Accident! Highway is closed");
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Expect heavy trafic this morning";
	}
}

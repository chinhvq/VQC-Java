package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {
	
	public void addAccount() {
		System.out.println(getClass() + " DOING MY DB WORK: ADDING AN ACCOUNT");
	}
	
	public void addAccount(Account account) {
		System.out.println(getClass() + " WITH account param - DOING MY DB WORK: ADDING AN ACCOUNT");
	}
	
	public void addAccount(Account account, boolean isVip) {
		System.out.println(getClass() + " WITH VIP param - DOING MY DB WORK: ADDING AN ACCOUNT");
	}
}

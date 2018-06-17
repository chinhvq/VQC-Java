package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {

	private String name;
	private String serviceCode;

	public String getName() {
		System.out.println("In getName");
		return name;
	}

	public void setName(String name) {
		System.out.println("In setName");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println("In getService");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println("In setService");
		this.serviceCode = serviceCode;
	}

	public void addAccount() {
		System.out.println(getClass() + " DOING MY DB WORK: ADDING AN ACCOUNT");
	}

	public void addAccount(Account account) {
		System.out.println(getClass() + " WITH account param - DOING MY DB WORK: ADDING AN ACCOUNT");
	}

	public void addAccount(Account account, boolean isVip) {
		System.out.println(getClass() + " WITH VIP param - DOING MY DB WORK: ADDING AN ACCOUNT");
	}

	public List<Account> findAccount() {
		List<Account> accounts = new ArrayList<>();
		accounts.add(new Account("John", "Silver"));
		accounts.add(new Account("Madhu", "Plantium"));
		accounts.add(new Account("Luca", "Gold"));
		return accounts;
	}
}

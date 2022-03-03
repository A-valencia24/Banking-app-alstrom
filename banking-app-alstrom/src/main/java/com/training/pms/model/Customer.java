package com.training.pms.model;

import java.util.Objects;

public class Customer {
	
	private int balance;
	private int userId;
	private String username;
	private String password;
	private String account;
	
	public Customer() { //customer constructor
		
	}
	
	public Customer(int balance, int userId, String userName, String passWord, String account){
		
		this.balance = balance;
		this.userId = userId;
		this.username = userName;
		this.password = passWord;
		this.account = account;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassWord() {
		return password;
	}

	public void setPassWord(String passWord) {
		this.password = passWord;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, balance, password, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(account, other.account) && balance == other.balance
				&& Objects.equals(password, other.password) && userId == other.userId
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Customer [balance=" + balance + ", userId=" + userId + ", userName=" + username + ", passWord="
				+ password + ", account=" + account + "]";
	}
	
	
}

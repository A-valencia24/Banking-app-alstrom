package com.training.pms.dao;

public interface CustomerDAO {
	
	public boolean viewBalance(int balance, int userId);
	public boolean deposite(int balance, int userId);
	public boolean withdraw(int balance, int userId); 
	public boolean transfer(int balance, int userId);
	public boolean customerExists(int userId);
	

}

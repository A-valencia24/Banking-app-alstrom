package com.training.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Scanner;

import com.training.pms.utility.DBConnection;

public class CustomerDAOImpl implements CustomerDAO {
	Scanner scan = new Scanner(System.in);
	Connection connection = DBConnection.getConnection();
	
	
	@Override
	public boolean transfer(int balance, int userId) {
		System.out.println("Enter the account number to debit the amount :");
		int sender = scan.nextInt();

		System.out.println("Enter the account number to credit the amount :");
		int reciever = scan.nextInt();
		
		System.out.println("Enter the amout to be  transferred :");
		int amount = scan.nextInt();
		Connection connection = DBConnection.getConnection();
		
	try {
		CallableStatement stat = connection.prepareCall("call transfer(?,?,?)");
		System.out.println("sending : " + sender + "to reciever : " + reciever + "a total of : " + amount);
		stat.setInt(1, sender);
		stat.setInt(2, reciever);
		stat.setInt(3, amount);
		stat.execute();
		
		stat.close();
		connection.close();
	} catch (SQLException e ) {
		e.printStackTrace();
		return false;
	}
	System.out.println("transfer complete");
		return true;
	}
	
	@Override
	public boolean withdraw(int balance, int userId) {
		System.out.println("Enter the amount you want to withdraw");
		CallableStatement stat; 
		
		try {
			stat = connection.prepareCall("call withdraw (?,?)"); 
			stat.setInt(1, userId);
			stat.setInt(2, balance);
			
			stat.execute();
			connection.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			}
			return true;
	}
	
	@Override
	public boolean deposite(int balance, int userId )  {
		System.out.println("Enter the amount you want to deposite ");
		CallableStatement stat; 
		try {
			stat = connection.prepareCall("call deposite (?,?)"); 
			stat.setInt(1, userId);
			stat.setInt(2, balance);
			
			stat.execute();
			connection.close();
			
		}
		catch(SQLException e) {
		e.printStackTrace();
		}
		return true;
	
	}
	
	
	
	public boolean customerExists(int userId) {
		boolean userExists = false;
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("select * from accounts where Id = ? ");
			stat.setInt(1, userId);
			ResultSet res = stat.executeQuery();
			userExists = res.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userExists;
	}

	@Override
	public boolean viewBalance(int balance, int userId) {
		PreparedStatement stat; 
		try {
			stat = connection.prepareStatement("select balance from accounts where Id = ?");
			stat.setInt(1, userId);
			stat.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	
	
}

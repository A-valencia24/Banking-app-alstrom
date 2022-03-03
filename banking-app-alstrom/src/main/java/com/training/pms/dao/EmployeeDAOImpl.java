package com.training.pms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.training.pms.model.Customer;
import com.training.pms.model.Employee;
import com.training.pms.model.Product;
import com.training.pms.utility.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	Connection connection = DBConnection.getConnection(); 

	public boolean addEmployee(Employee employee) {
		System.out.println("Adding Employee" + employee);
		PreparedStatement statement = null;
		int rows = 0;
		try {
			statement = connection.prepareStatement("insert into employeeLogin values (?,?,?)");
			statement.setInt(1, employee.getemployeeId());
			statement.setString(2, employee.getUserName());
			statement.setString(3, employee.getPassword());
			rows = statement.executeUpdate();
			System.out.println(rows + " inserted successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
	}

	
	public boolean deleteUser(int userId) {
		
		System.out.println("##Deleting user with user id  :" + userId);
		PreparedStatement statement = null;
		int rows = 0;
		try {
			statement = connection.prepareStatement("delete from accounts where userid = ?");
			statement.setInt(1, userId);
			rows = statement.executeUpdate();
			System.out.println(rows + " deleted successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
		
	}
	@Override
	public List<Customer> searchUsername(String username){
		System.out.println("Searching user with user name" + username);
		List<Customer> customers = new ArrayList<Customer>();
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("select * from accounts where name = ? ");
			stat.setString(1, username);
			ResultSet res = stat.executeQuery();
			while (res.next()) {
				Customer customer = new Customer();
				customer.setUserId(1);
				customer.setUserName(res.getString(2));
				customer.setPassWord(res.getString(3));
				customer.setBalance(res.getInt(4));
				customer.setAccount(res.getString(5));
				customers.add(customer);
			}
			
			res.close();
			stat.close();
			connection.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
		
	}
	@Override
	public boolean approveAccount(String userName, int userId, String account) {
		boolean status = false;
		Connection connection = DBConnection.getConnection();
		PreparedStatement stat; 
		CallableStatement leash;
		try {
			stat = connection.prepareStatement("select * from accounts where id = ?");
			stat.setInt(1, userId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public boolean rejectAccount(String userName, int userId, String account) {
		PreparedStatement stat = null;
		int rows = 0;
		
		try {
			stat = connection.prepareStatement("delete from accounts where id = ? ");
			stat.setInt(1, userId);

			rows = stat.executeUpdate();
			
			stat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
	}
	
	@Override
	public boolean customerExists(String userName) {
		boolean customerExists = false;
		PreparedStatement stat;
		
		try {
			stat = connection.prepareStatement("select * from accounts where name = ? ");
			stat.setString(1, userName);
			ResultSet res = stat.executeQuery();
			customerExists = res.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return customerExists;
	}
	
	@Override
	public void printUsers(String userName) {
		connection = DBConnection.getConnection();
		
		try {
			Statement stat = connection.createStatement();
			ResultSet res = stat.executeQuery("select * from customers");

			
			ResultSetMetaData data = res.getMetaData();
			int columnCount = data.getColumnCount();

		
			for (int i = 1; i <= columnCount; i++) {
				System.out.print(data.getColumnName(i) + "    ");
			}
			System.out.println();

		
			while (res.next()) {

				for (int i = 1; i <= columnCount; i++) {
					System.out.print(res.getString(i) + "    ");
				}
				System.out.println();
			}
			
			res.close();
			stat.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		
	
		}
		
	}
	@Override
	public List<Customer> viewAccount(int balance, String userName, int userId) {
		connection = DBConnection.getConnection();
		List<Customer> customers = new ArrayList<Customer>();
		
		try {
			Statement stat = connection.createStatement();
			ResultSet res = stat.executeQuery("select * from accounts where id = ?");
				while (res.next()) {
					Customer customer = new Customer();
					customer.setUserId(1);
					customer.setUserName(res.getString(2));
					customer.setBalance(res.getInt(3));
					customer.setAccount(res.getString(4));
					
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
		}
		return customers;
	}
		
	
	@Override
	public Employee accountDetails(String userName, String passWord) {
		connection = DBConnection.getConnection();
		PreparedStatement stat = null;
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = new Employee();
		try {
			stat = connection.prepareStatement("select * from employeeLogin where username = ? and password ");
			stat.setString(1, userName);
			stat.setString(2, passWord);
			
			ResultSet res = stat.executeQuery();
			
			while(res.next()) {
				employee = new Employee();
				employee.setUserId(res.getInt(1));
				employee.setUserName(res.getString(2));
				employee.setPassword(res.getString(3));
				employee.setAccount(res.getString(4));
				employees.add(employee);
				
				System.out.println("USER id from employye is : " + employee.getUserId());
				
				
			}
			res.close();
			stat.close();
			connection.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
		
	}
	
	
}

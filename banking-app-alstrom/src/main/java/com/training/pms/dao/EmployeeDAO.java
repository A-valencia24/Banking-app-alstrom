package com.training.pms.dao;

import java.util.List;

import com.training.pms.model.Customer;
import com.training.pms.model.Employee;
import com.training.pms.model.Product;

public interface EmployeeDAO {
	public boolean deleteUser(int userId);
	public List<Customer> searchUsername(String username);
	public boolean approveAccount(String userName, int userId, String account);
	public boolean rejectAccount(String userName, int userId, String account);
	public List<Customer> viewAccount(int balance, String userName, int userId);
	public boolean customerExists(String userName);
	public void printUsers(String userName);
	public Employee accountDetails(String userName, String passWord);
	public boolean addEmployee(Employee employee);
	
}

package com.training.pms.model;

import java.util.Objects;

public class Employee {

	private int employeeId;
	private String employeeName;
	private String password;
	private String account;
	private int userId;

	public Employee() { // constructor

	}

	public Employee(int employeeId, String employeeName, String password, String account) {

		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.password = password;
		this.account = account;

	}

	public Employee(int employeeId, String employeeName, String password) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.password = password;
	}

	public int getemployeeId() {
		return employeeId;
	}

	public void setemployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getUserName() {
		return employeeName;
	}

	public void setUserName(String userName) {
		this.employeeName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, employeeId, employeeName, password, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(account, other.account) && employeeId == other.employeeId
				&& Objects.equals(employeeName, other.employeeName) && Objects.equals(password, other.password)
				&& userId == other.userId;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", password=" + password
				+ ", account=" + account + ", userId=" + userId + "]";
	}
	
	

}

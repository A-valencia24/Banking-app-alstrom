package com.training.pms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.training.pms.dao.CustomerDAO;
import com.training.pms.dao.CustomerDAOImpl;
import com.training.pms.dao.EmployeeDAO;
import com.training.pms.dao.EmployeeDAOImpl;
import com.training.pms.dao.LoginDAO;
import com.training.pms.dao.LoginDAOImpl;
import com.training.pms.dao.ProductDAO;
import com.training.pms.dao.ProductDAOImpl;
import com.training.pms.model.Customer;
import com.training.pms.model.Employee;
import com.training.pms.model.Login;
import com.training.pms.model.Product;

public class BankingApp {
	Scanner scan = new Scanner(System.in);
	int choice = 0;
	LoginDAO loginDAO = new LoginDAOImpl();
	CustomerDAO customerDAO = new CustomerDAOImpl();
	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	Employee employee = new Employee();
	Customer customer = new Customer();
	Login login = new Login();

	boolean result;
	boolean valid, notValid;

	public void startBankingApp() {
		// local variables
		String username, password, account, receiver, sender, loginId, employeeName = null;
		int balance, amount = 0, employeeId, userId = 0;
		List<Customer> customers = new ArrayList<Customer>();

		while (true) {

			System.out.println("=========================================================");
			System.out.println("BANKING  -  APP  M E N U  ");
			System.out.println("1.Create User Account");
			System.out.println("2. Login");
			System.out.println("3. EX I T");

			System.out.println("please enter your choice : ");
			choice = scan.nextInt();
			System.out.println("=========================================================");

			switch (choice) {
			case 1:
				System.out.println("Enter username :");
				username = scan.next();
				System.out.println("Enter password :");
				password = scan.next();
				Login login = new Login(-1, username, password);
				loginDAO.register(login);

				break;
			case 2:
				System.out.println("Enter username :");
				username = scan.next();
				System.out.println("Enter password :");
				password = scan.next();
				result = loginDAO.validate(username, password);
				if (result) {
					System.out.println("Welcome :" + username);
				} else {
					System.out.println("Sorry username or password incorrect , Try again");
					continue;
				}
				break;
			default:
				System.out.println("Invalid choice ");
			}
			while (true) {
				System.out.println("=========================================================");
				System.out.println("BANKING COSTUMER MENU  ");
				System.out.println("1.view balance");
				System.out.println("2. deposite money");
				System.out.println("3. withdraw money");
				System.out.println("4. transfer money to another account");

				System.out.println("please enter your choice :(1-4) or any other for admin related : ");
				choice = scan.nextInt();
				System.out.println("=========================================================");
				switch (choice) {
				case 1:
					System.out.println("view balance");
					// View Balance Section

					System.out.println("\nCurrent customer balance : " + customer.getBalance() + "\n");

					break;

				case 2:
					System.out.println("deposite money");
					notValid = true;
					while (notValid) {
						System.out.println("Enter the amount to be deposited :");
						amount = scan.nextInt();

						if (amount < 0)
							System.out.println("Amount to deposit cannot not be negative.");
						else
							notValid = false;
					}

					boolean valid = customerDAO.deposite(userId, amount);

					System.out.println("Account was debited : " + amount);

					break;
				case 3:
					System.out.println("withdraw money");
					break;
				case 4:
					System.out.println("transfer money to another account");
					break;
				}

				while (true) {
					System.out.println("=========================================================");
					System.out.println(" B A N K I N G   -  APP  M E N U  ");
					System.out.println("1. Add Employee");
					System.out.println("2. Delete user");
					System.out.println("3. view user account");
					System.out.println("4.approve account");
					System.out.println("5.reject account");
					System.out.println("6. view transaction log");
					System.out.println("7. print all users");

					System.out.println("please enter your choice : ");
					choice = scan.nextInt();
					System.out.println("=========================================================");

					switch (choice) {
					case 1:
						// add product section
						System.out.println("welcome to add employee selection");
						// take input from user to add a product
						System.out.println("please enter employee Id");
						employeeId = scan.nextInt();

						System.out.println("please enter employee Name");
						employeeName = scan.next();
						System.out.println("please enter password");
						password = scan.next();

						employee = new Employee(employeeId, employeeName, password);
						// call DAO layer to save the product
						result = employeeDAO.addEmployee(employee);
						if (result)
							System.out.println("Congratulations, your employee : " + employee.getUserName()
									+ " saved successfully");
						else
							System.out.println("sorry your employee cannot be saved");

						break;

					case 9:
						System.out.println("thanks for using my program");
						System.exit(0);
					default:
						System.out.println("Invalid choice, please enter (1-6) or 9 to exit");
						break;
					}
				}
			}
		}
	}

}

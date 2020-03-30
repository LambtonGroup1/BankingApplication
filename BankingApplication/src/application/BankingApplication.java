package application;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import javax.security.auth.login.LoginContext;

import Utils.Utils;
import data.ApplicationData;
import models.Customer;
import models.CustomerAccount;
import models.LoginDetails;
import models.Transaction;

public class BankingApplication {

	public static void main(String[] args) {

		// initialize the arraylists used for the data storage

		ApplicationData appData = new ApplicationData();

		// initialize scanner object. This single object will be used for all
		// the modules.
		Scanner sc = new Scanner(System.in);
		Console console = System.console();

		// temp data 1 for testing
		Customer tempCust = new Customer();

		tempCust.setCustomerAccountNumber(1234);
		tempCust.setCustomerName("Satish");
		tempCust.setCustomerEmail("satishsilveri.5@gmail.com");
		tempCust.setCustomerPhoneNumber(1234566789);
		tempCust.setCustomerPincode("m3a3b2");
		tempCust.setCustomerAddress("North York");

		ApplicationData.customers.add(tempCust);

		LoginDetails tempDetails = new LoginDetails();
		tempDetails.setAccountNumber(1234);
		tempDetails.setPassword("Bank@1234");
		tempDetails.setTemp(false);

		CustomerAccount customerAccount = new CustomerAccount();

		customerAccount.setAccountNumber(1234);

		ApplicationData.customerAccountData.add(customerAccount);

		ApplicationData.customerLoginDetails.add(tempDetails);
		// end temp data 1

		// temp data 2 for testing
		Customer tempCust1 = new Customer();

		tempCust1.setCustomerAccountNumber(5678);
		tempCust1.setCustomerName("Satish1");
		tempCust1.setCustomerEmail("satishsilveri.5@gmail.com");
		tempCust1.setCustomerPhoneNumber(1234566789);
		tempCust1.setCustomerPincode("m3a3b2");
		tempCust1.setCustomerAddress("North York");

		ApplicationData.customers.add(tempCust1);

		LoginDetails tempDetails1 = new LoginDetails();
		tempDetails1.setAccountNumber(5678);
		tempDetails1.setPassword("Bank@5678");
		tempDetails1.setTemp(false);

		CustomerAccount customerAccount1 = new CustomerAccount();

		customerAccount1.setAccountNumber(5678);

		ApplicationData.customerAccountData.add(customerAccount1);

		ApplicationData.customerLoginDetails.add(tempDetails1);
		// end temp data 2

		String ch = null;

		do {
			System.out.println("******BANKING APPLICATION******");
			System.out.println("Login As:");
			System.out.println("1. Admin");
			System.out.println("2. Customer");
			System.out.println("Please enter your choice : ");
			String choice = sc.nextLine();

			switch (choice) {

			case "1":

				System.out.println("Username : ");
				String userName = sc.nextLine();

				// char[] pass = console.readPassword("Enter password");
				// String password = pass.toString();
				System.out.println("Password: ");
				String password = sc.nextLine();

				if (Utils.checkLogin("admin", userName, password, sc)) {
					String adminContinue = null;
					do {
						System.out.println("Admin Menu:");
						System.out.println("1. Add Customer");
						System.out.println("2. Delete Customer");
						System.out.println("3. Update Customer");
						System.out.println("Please enter your choice: ");
						String adminChoice = sc.nextLine();

						switch (adminChoice) {
						case "1":
							module.Admin.addCustomer(sc);

							break;
						case "2":
							System.out.println("Please enter the customer account number: ");
							int accountNumberForDeletion = sc.nextInt();
							sc.nextLine();
							module.Admin.deleteCustomer(accountNumberForDeletion);
							break;
						case "3":
							System.out.println("Please enter the customer account number: ");
							int accountNumberForUpdation = sc.nextInt();
							sc.nextLine();
							module.Admin.updateCustomer(accountNumberForUpdation, sc);
							break;
						default:
							System.out.println("Please enter valid input.");
							break;
						}
						System.out.println("Do you want to continue(y/n) : ");
						adminContinue = sc.nextLine();
					} while (adminContinue.equalsIgnoreCase("y"));
				} else {
					System.out.println("Please enter valid credentials.");
				}

				break;
			case "2":
				String customerUserName = null;
				boolean validUser = false;
				int passwordTryCount = 1;
				while (passwordTryCount < 4) {

					System.out.println("Account Number: ");
					customerUserName = sc.nextLine();

					// char[] custPass = console.readPassword("Enter password");
					// String customerPassword = custPass.toString();
					System.out.println("Password: ");
					String customerPassword = sc.nextLine();

					boolean flag = Utils.checkLogin("customer", customerUserName, customerPassword, sc);
					if (flag) {
						validUser = true;
						break;
					}
					System.out.println("Invalid Credentials. Please try again. Number of retries remaining : "
							+ (3 - passwordTryCount));
					passwordTryCount++;
				}

				if (validUser) {
					// if user is valid, proceed. Here user name is equal to
					// account number.
					Customer sessionObj = Utils.loadCustomerData(customerUserName);
					if (sessionObj != null) {
						Utils.customerSessionObj = sessionObj;
					}
					String customerContinue = null;
					do {
						System.out.println("Customer Menu:");
						System.out.println("1. Check Balance");
						System.out.println("2. Transfer Funds");
						System.out.println("3. View Previous Transactions");
						System.out.println("Please enter your choice: ");
						String customerChoice = sc.nextLine();

						switch (customerChoice) {
						case "1":
							System.out.println("Your account balance is : " + module.Customer
									.getCustomerBalance(Utils.customerSessionObj.getCustomerAccountNumber()));
							break;
						case "2":
							module.Customer.transferFunds(sc);
							break;
						case "3":
							ArrayList<Transaction> transactions = module.Customer
									.getTransactionDetails(Utils.customerSessionObj.getCustomerAccountNumber());

							int numTransactions = 0;

							do {
								System.out.println(
										"Please enter the number of transactions you want to see (Total transactions : "
												+ transactions.size() + ") : ");
								numTransactions = Integer.parseInt(sc.nextLine());

							} while (numTransactions > transactions.size());

							for (int i = 0; i < numTransactions; i++) {

								System.out.println("----------------------------------");
								System.out.println("Transaction ID : " + transactions.get(i).getTransactionId());
								System.out.println("From : " + transactions.get(i).getFromCustomerId());
								System.out.println("To : "+transactions.get(i).getToCustomerNumber());
								System.out.println("Amount : " + transactions.get(i).getTransactionAmount());
								System.out.println("Transaction Type : " + transactions.get(i).getTransactionType());
								System.out.println("Previous Balance : " + transactions.get(i).getPreviousBalance());
								System.out.println("Current Balance : " + transactions.get(i).getCurrentBalance());
								System.out.println("Transaction Date : " + transactions.get(i).getTransactionDate());
								System.out.println("----------------------------------");
								System.out.println("\n\n");
							}

							break;
						default:
							System.out.println("Please enter valid choice.");
							break;
						}
						System.out.println("Do you want to continue(y/n) : ");
						customerContinue = sc.nextLine();
					} while (customerContinue.equalsIgnoreCase("y"));
					System.out.println("You have been logged out of the account automatically.");
				} else {
					System.out.println(
							"Max retries reached. Your account has been locked. Please contact the Administration.");
				}

				break;

			}
			System.out.println("Do you want to continue to main menu (y/n):");
			ch = sc.nextLine();
		} while (ch.equalsIgnoreCase("y"));
		System.out.println("You have been logged out of the system.");
	}

}

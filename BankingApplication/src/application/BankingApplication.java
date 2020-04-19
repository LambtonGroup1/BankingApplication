package application;

import java.util.ArrayList;
import java.util.Scanner;

import Utils.Utils;
import data.ApplicationData;
import models.Customer;
import models.CustomerAccount;
import models.LoginDetails;
import models.Transaction;
import java.io.Console;

public class BankingApplication {

	public static void main(String[] args) {

		// initialize the arraylists used for the data storage

		try {
			@SuppressWarnings("unused")
			ApplicationData appData = new ApplicationData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// initialize scanner object. This single object will be used for all
		// the modules.
		Scanner sc = new Scanner(System.in);

		//add temp data
		Utils.addTempCustomerData();

		String ch = null;

		do {
			System.out.println("******BANKING APPLICATION******");
			System.out.println("Login As:");
			System.out.println("1. Admin");
			System.out.println("2. Customer");
			System.out.println("Please enter your choice : ");
			String choice = sc.nextLine();
			Console console = System.console();
			switch (choice) {

			case "1":
				String userName = null;

				do {
					System.out.println("Username : ");
					userName = sc.nextLine();
				} while (userName.length() == 0);

				String password=null;


				do {
					char[] pass = console.readPassword("Enter password :");
					password = String.valueOf(pass);
				} while (password.length() == 0);

				if (Utils.checkLogin("admin", userName, password, sc)) {
					String adminContinue = null;
					do {
						System.out.println("Admin Menu:");
						System.out.println("1. Add Customer");
						System.out.println("2. Delete Customer");
						System.out.println("3. Update Customer");
						System.out.println("4. Logout");
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
						case "4":
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

					do {
						System.out.println("Username : ");
						customerUserName = sc.nextLine();
					} while (customerUserName.length() == 0);

					// char[] pass = console.readPassword("Enter password");
					// String password = pass.toString();
					String customerPassword = null;
					do {
						char[] pass = console.readPassword("Enter password :");
						customerPassword = String.valueOf(pass);
					} while (customerPassword.length() == 0);

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
						System.out.println("4. Logout");
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
								System.out.println("To : " + transactions.get(i).getToCustomerNumber());
								System.out.println("Amount : " + transactions.get(i).getTransactionAmount());
								System.out.println("Transaction Type : " + transactions.get(i).getTransactionType());
								System.out.println("Previous Balance : " + transactions.get(i).getPreviousBalance());
								System.out.println("Current Balance : " + transactions.get(i).getCurrentBalance());
								System.out.println("Transaction Date : " + transactions.get(i).getTransactionDate());
								System.out.println("----------------------------------");
								System.out.println("\n\n");
							}

							break;

						case "4":
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

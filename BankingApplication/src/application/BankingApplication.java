package application;

import java.io.Console;
import java.util.Scanner;
import Utils.Utils;
import data.ApplicationData;

public class BankingApplication {

	public static void main(String[] args) {

		// initialize the arraylists used for the data storage

		ApplicationData appData = new ApplicationData();

		// initialize scanner object. This single object will be used for all
		// the modules.
		Scanner sc = new Scanner(System.in);
		Console console = System.console();

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

				
				//char[] pass = console.readPassword("Enter password");
				//String password = pass.toString();
				System.out.println("Password: ");
				String password=sc.nextLine();

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

				System.out.println("Account Number: ");
				String customerUserName = sc.nextLine();

				
				//char[] custPass = console.readPassword("Enter password");
				//String customerPassword = custPass.toString();
				System.out.println("Password: ");
				String customerPassword=sc.nextLine();

				boolean validUser = false;
				for (int i = 0; i < 3; i++) {
					boolean flag=Utils.checkLogin("customer", customerUserName, customerPassword, sc);
					System.out.println(flag);
					if (flag) {
						validUser = true;
						break;
					}
				}

				if (validUser) {
					//if user is valid, proceed.
					Utils.customerSessionObj = Utils.loadCustomerData(customerUserName);
					String customerContinue = null;
					do {
						System.out.println("Customer Menu:");
						System.out.println("1. Check Balance");
						System.out.println("2. Transfer Funds");
						System.out.println("3. View Previous Transactions");
						String customerChoice = sc.nextLine();

						switch (customerChoice) {
						case "1":
							System.out.println("Please enter your account number: ");
							int accountNumberForBalance = sc.nextInt();
							sc.nextLine();
							module.Customer.getCustomerBalance(accountNumberForBalance);
							break;
						case "2":
							module.Customer.transferFunds(sc);
							break;
						case "3":
							System.out.println("Please enter your account number: ");
							int accountNumberForTransaction = sc.nextInt();
							sc.nextLine();

							module.Customer.getTransactionDetails(accountNumberForTransaction);
							break;
						default:
							System.out.println("Please enter valid choice.");
							break;
						}
						System.out.println("Do you want to continue(y/n) : ");
						customerContinue = sc.nextLine();
					} while (customerContinue.equalsIgnoreCase("y"));
				} else {
					System.out.println(
							"Max retries reached. Your account has been locked. Please contact the Administration.");
				}

				break;

			}
			System.out.println("Do you want to continue to main menu (y/n):");
			ch = sc.nextLine();
		} while (ch.equalsIgnoreCase("y"));

	}

}

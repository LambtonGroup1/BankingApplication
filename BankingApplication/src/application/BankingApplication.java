package application;

import java.io.Console;
import java.util.Scanner;

import Utils.Utils;
import data.ApplicationData;
import models.CustomerAccount;
import models.LoginDetails;

public class BankingApplication {

	public static void main(String[] args) {

		// initialize the arraylists used for the data storage

		ApplicationData appData = new ApplicationData();

		// initialize scanner object. This single object will be used for all the modules.
		Scanner sc = new Scanner(System.in);

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

				System.out.println("Password : ");

				String password = sc.nextLine();

				if (Utils.checkLogin("admin", userName, password,sc)) {

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
					}
				} else {
					System.out.println("Please enter valid credentials.");
				}

				break;
			case "2":

				System.out.println("Account Number: ");
				String customerUserName = sc.nextLine();

				Console cnsl2 = System.console();

				char[] temp_pwd = cnsl2.readPassword("Password: ");

				String customerPassword = temp_pwd.toString();

				boolean validUser = false;
				for (int i = 0; i < 3; i++) {
					if (Utils.checkLogin("customer", customerUserName, customerPassword,sc)) {
						validUser = true;
						break;
					}
				}

				if (validUser) {

					Utils.customerSessionObj = Utils.loadCustomerData(customerUserName);
					

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
					}
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

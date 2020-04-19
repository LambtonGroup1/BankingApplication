package Utils;

import java.security.SecureRandom;
import java.io.Console;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import config.Configurations;
import data.ApplicationData;
import models.Customer;
import models.CustomerAccount;
import models.LoginDetails;

public class Utils {

	public static models.Customer customerSessionObj = null;

	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";
	private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";

	private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
	// optional, make it more random
	private static final String PASSWORD_ALLOW_BASE_SHUFFLE = shuffleString(PASSWORD_ALLOW_BASE);
	private static final String PASSWORD_ALLOW = PASSWORD_ALLOW_BASE_SHUFFLE;

	private static SecureRandom random = new SecureRandom();

	public static int getUniqueTransactionId() {

		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// this will convert any number sequence into 6 character.
		return Integer.parseInt(String.format("%06d", number));

	}

	public static int getUniqueAccountNumber() {

		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = new Random();
		int number = rnd.nextInt(9999);

		// this will convert any number sequence into 4 character.
		return Integer.parseInt(String.format("%04d", number));

	}

	public static String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static boolean checkLogin(String userType, String userName, String password, Scanner sc) {

		// TODO Auto-generated method stub

		if (userType.equalsIgnoreCase("admin")) {

			if (userName.length() > 0 && password.length() > 0) {

				if (userName.equalsIgnoreCase(Configurations.adminUserName1)) {

					if (password.equalsIgnoreCase(Configurations.adminPassword1)) {
						return true;
					} else {
						return false;
					}
				}
				if (userName.equalsIgnoreCase(Configurations.adminUserName2)) {
					if (password.equalsIgnoreCase(Configurations.adminPassword2)) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				System.out.println("Username or Password cannot be empty!");
				return false;
			}

		} else if (userType.equalsIgnoreCase("customer")) {

			// here user name is account number
			int accountNumber = Integer.parseInt(userName);
			
			for (LoginDetails logindetails : ApplicationData.customerLoginDetails) {
				if (logindetails.getAccountNumber() == accountNumber) {
				
					if (logindetails.isTemp()) {
						
						changeTempPassword(logindetails, sc);
						return true;
					} else {
						System.out.println("In check password");
						String decryptedPassword = ApplicationData.td.decrypt(logindetails.getPassword());
						if (decryptedPassword.equals(password)) {
							return true;
						}
					}
				}
			}

		}
		return false;
	}

	private static void changeTempPassword(LoginDetails loginDetails, Scanner sc) {
		String newPassword = null;
		String confirmPassword = null;
		
		Console console = System.console();

		System.out.println("In Change Password Console : ");

		do {
			String currentPassword =null;
			
			do {
				char[] pass = console.readPassword("Enter Current Password :");
				currentPassword = String.valueOf(pass);
			} while (currentPassword.length() == 0);
			
			System.out.println("Current Password :"+currentPassword);
			do {
				char[] pass = console.readPassword("Enter New Password :");
				newPassword = String.valueOf(pass);
			} while (newPassword.length() == 0);
			
			System.out.println("New Password :"+newPassword);

			do {
				char[] pass = console.readPassword("Enter Confirm Password :");
				confirmPassword = String.valueOf(pass);
			} while (confirmPassword.length() == 0);
			
			System.out.println("Confirm Password:"+confirmPassword);

			if (currentPassword.equals(loginDetails.getPassword())) {
				if (newPassword.equals(confirmPassword)) {

					String encryptedPassword = ApplicationData.td.encrypt(newPassword);
					ApplicationData.customerLoginDetails.get(ApplicationData.customerLoginDetails.indexOf(loginDetails))
							.setPassword(encryptedPassword);
					ApplicationData.customerLoginDetails.get(ApplicationData.customerLoginDetails.indexOf(loginDetails))
							.setTemp(false);
					System.out.println("Password changed successfully!");
					break;
				} else {
					System.out.println("Paswords do not match!");
				}
			}else{
				System.out.println("Current Password does not match!");
			}
		} while (newPassword.equals(confirmPassword));

	}

	public static models.Customer loadCustomerData(String accountNumber) {
		// TODO Auto-generated method stub

		for (models.Customer custAcc : ApplicationData.customers) {

			if (custAcc.getCustomerAccountNumber() == Integer.parseInt(accountNumber)) {
				return custAcc;
			}

		}
		return null;
	}

	public static String generateRandomPassword(int length) {
		if (length < 1)
			throw new IllegalArgumentException();

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {

			int rndCharAt = random.nextInt(PASSWORD_ALLOW.length());
			char rndChar = PASSWORD_ALLOW.charAt(rndCharAt);

			sb.append(rndChar);

		}

		return sb.toString();

	}

	// shuffle
	public static String shuffleString(String string) {
		List<String> letters = Arrays.asList(string.split(""));
		Collections.shuffle(letters);
		return letters.stream().collect(Collectors.joining());
	}
	
	public static void addTempCustomerData() {
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
				tempDetails.setPassword("$57*90");
				tempDetails.setTemp(true);

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
				tempDetails1.setPassword("$57*90");
				tempDetails1.setTemp(true);

				CustomerAccount customerAccount1 = new CustomerAccount();

				customerAccount1.setAccountNumber(5678);

				ApplicationData.customerAccountData.add(customerAccount1);

				ApplicationData.customerLoginDetails.add(tempDetails1);
				// end temp data 2
	}

}

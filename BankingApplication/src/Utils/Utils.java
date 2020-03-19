package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import config.Configurations;
import data.ApplicationData;
import module.Customer;

public class Utils {
	
	public static models.Customer customerSessionObj=null;

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

	public static boolean checkLogin(String userType, String userName, String password) {
		// TODO Auto-generated method stub


		if (userName.length() > 0 && password.length() > 0) {

			if (userType.equalsIgnoreCase("admin")) {

				if (userName.equalsIgnoreCase(Configurations.adminUserName1)) {

					if (password.equalsIgnoreCase(Configurations.adminPassword1)) {
						return true;
					} else{
						return false;
					}
				}
				if (userName.equalsIgnoreCase(Configurations.adminUserName2)) {
					if (password.equalsIgnoreCase(Configurations.adminPassword2)) {
						return true;
					}else{
						return false;
					}
				}
			} else {
				System.out.println("Username or Password cannot be empty!");
				return false;
			}

		} else if (userType.equalsIgnoreCase("customer")) {

		}
		return false;
	}

	public static models.Customer loadCustomerData(String accountNumber) {
		// TODO Auto-generated method stub
		
		for(models.Customer custAcc: ApplicationData.customers){
			
			if(custAcc.getCustomerAccountNumber()==Integer.parseInt(accountNumber)){
				return custAcc;
			}
			
		}
		return null;
	}

}

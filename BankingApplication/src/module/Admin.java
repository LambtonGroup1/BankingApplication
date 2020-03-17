package module;

import java.util.Scanner;
import data.ApplicationData;
import models.Customer;

public class Admin {

	public static boolean addCustomer(Scanner sc) {
		Customer customer = new Customer();
		String infoChoice = null;
		do {

			customer.setCustomerAccountNumber(Utils.Utils.getUniqueAccountNumber());

			System.out.println("Please Enter the Customer Name");
			customer.setCustomerName(sc.nextLine());

			System.out.println("Please Enter the Customer Email");

			customer.setCustomerEmail(sc.nextLine());

			System.out.println("Please Enter the Customer Address :");

			customer.setCustomerAddress(sc.nextLine());

			System.out.println("Please Enter the Customer Phone Number :");

			customer.setCustomerPhoneNumber(Integer.parseInt(sc.nextLine()));

			System.out.println("Please enter the customer pincode: ");
			customer.setCustomerPincode(sc.nextLine());

			// add remaining info of customers

			System.out.println("Do you want to confirm the below info (y/n):");
			System.out.println("Customer Name:" + customer.getCustomerName());

			infoChoice = sc.nextLine();
		} while (infoChoice.equalsIgnoreCase("n"));

		ApplicationData.customers.add(customer);
		return true;
	}

	public static boolean deleteCustomer(int customerAccountNumber) {

		// if returns false, then could not find customerAccount

		for (Customer customer : ApplicationData.customers) {

			if (customer.getCustomerAccountNumber() == customerAccountNumber) {
				ApplicationData.customers.remove(ApplicationData.customers.indexOf(customer));
				return true;
			}

		}

		return false;
	}

	public static void updateCustomer(int customerAccountNumber,Scanner sc){
		
		Customer customer=null;
		
		for (Customer cust : ApplicationData.customers) {

			if (customer.getCustomerAccountNumber() == customerAccountNumber) {
				customer=cust;
			}

		}
			 
		  System.out.println("Please Enter the Customer Name ["+customer.getCustomerName()+"] :");
		  
		  String customerName = sc.nextLine();
				  
		  if(customerName.length()>0){
			  customer.setCustomerName(customerName);
		  }
			  
	  }
}

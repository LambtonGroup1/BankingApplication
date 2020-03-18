package module;

import java.util.Scanner;
import data.ApplicationData;
import models.Customer;

public class Admin {

	
	public static boolean addCustomer(Scanner sc) {
		Customer customer = new Customer();
		String infoChoice;
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

			System.out.println("Do you want to confirm the below info (y/n):");
			System.out.println("Customer Name:" + customer.getCustomerName());
			System.out.println("Customer Email:" + customer.getCustomerEmail());
			System.out.println("Customer Address:" + customer.getCustomerAddress());
			System.out.println("Customer Address:" + customer.getCustomerPhoneNumber());
			System.out.println("Customer Address:" + customer.getCustomerPincode());
			infoChoice = sc.nextLine();
		} while (infoChoice.equalsIgnoreCase("n"));
        //ERROR
		ApplicationData.customers.add(customer);
		System.out.println("Customer has been created succesfully");
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
	

	public static void updateCustomer(int customerAccountNumber, Scanner sc) {

		Customer customer = null;

		for (Customer cust : ApplicationData.customers) {

			if (customer.getCustomerAccountNumber() == customerAccountNumber) {
				customer = cust;
			}

		}

		System.out.println("Please Enter the Customer Name [" + customer.getCustomerName() + "] :");
		String customerName = sc.nextLine();
		if (customerName.length() > 0) {
			customer.setCustomerName(customerName);
		}
		System.out.println("Please Enter the Customer Email [" + customer.getCustomerName() + "] :");
		String CustomerEmail = sc.nextLine();
		if (CustomerEmail.length() > 0) {
			customer.setCustomerEmail(CustomerEmail);
		}
		System.out.println("Please Enter the Customer Address [" + customer.getCustomerAddress() + "] :");
		String CustomerAddress=sc.nextLine();
		if (CustomerAddress.length() > 0) {
			customer.setCustomerAddress(CustomerAddress);
		}
		System.out.println("Please Enter the Customer Phone Number [" + customer.getCustomerPhoneNumber() + "] :");
		String phonenumber=sc.nextLine();
		int CustomerPhoneNumber=Integer.parseInt(phonenumber);
		if (phonenumber.length() > 0) {
			customer.setCustomerPhoneNumber(CustomerPhoneNumber);
		}
		System.out.println("Please enter the customer pincode [" + customer.getCustomerPincode() + "] :");
		String CustomerPincode=sc.nextLine();
		if (CustomerPincode.length() > 0) {
			customer.setCustomerPincode(CustomerPincode);
		}
	}
	
	public static void main(String[] args)
	{
		
		int operation;
		String newcustomer;
		int Accnum;
		System.out.println("Please select the operation you wish to perform\n1. Add the Cutomer\n2. Delete the Customer\n3. Update the Customer");
		Scanner sc=new Scanner(System.in);
		operation=sc.nextInt();
		switch(operation)
	       {
	       		case 1: 
	       			do {
	       				addCustomer(sc);
	       				System.out.println("Do you wish to insert another Customer y/n?");
	       				newcustomer=sc.nextLine();
	       			}while(newcustomer.equalsIgnoreCase("y"));
	       			break;
	       			
	       		case 2: 
	       			do {
	       				System.out.println("Please provide the Account number of the Customer to be deleted");
	       				Accnum=sc.nextInt();
	       				deleteCustomer(Accnum);
	       				System.out.println("Do you wish to delete any other Customer y/n?");
	       				newcustomer=sc.nextLine();
	       			}while(newcustomer.equalsIgnoreCase("y"));
	       			break;
	       			
	       		case 3: 
	       			do {
	       				System.out.println("Please provide the Account number of the Customer to be updated");
	       				Accnum=sc.nextInt();
	       				updateCustomer(Accnum,sc);
	       				System.out.println("Do you wish to update any other Customer y/n?");
	       				newcustomer=sc.nextLine();
	       			}while(newcustomer.equalsIgnoreCase("y"));
	       			break;
          } 
	}
}

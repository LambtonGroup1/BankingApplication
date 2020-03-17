package data;

import java.util.ArrayList;

import models.Customer;
import models.CustomerAccount;

public class ApplicationData {
	
	public static ArrayList<Customer> customers;
	
	public static ArrayList<CustomerAccount> customerAccountData;
	
	
	
	
	public ApplicationData(){
		customerAccountData=new ArrayList<CustomerAccount>();
		
		customers=new ArrayList<Customer>();
	}
	
	
	

}

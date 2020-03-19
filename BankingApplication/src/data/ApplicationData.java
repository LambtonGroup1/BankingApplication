package data;

import java.util.ArrayList;

import models.Customer;
import models.CustomerAccount;
import models.LoginDetails;

public class ApplicationData {
	
	public static ArrayList<Customer> customers;
	
	public static ArrayList<CustomerAccount> customerAccountData;
	
	public static ArrayList<LoginDetails> customerLoginDetails;
	
	
	
	
	public ApplicationData(){
		customerAccountData=new ArrayList<CustomerAccount>();
		
		customers=new ArrayList<Customer>();
		
		customerLoginDetails=new ArrayList<LoginDetails>();
	}
	
	
	

}

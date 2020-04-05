package data;

import java.util.ArrayList;

import Utils.TrippleDes;
import models.Customer;
import models.CustomerAccount;
import models.LoginDetails;

public class ApplicationData {
	
	public static ArrayList<Customer> customers;
	
	public static ArrayList<CustomerAccount> customerAccountData;
	
	public static ArrayList<LoginDetails> customerLoginDetails;
	
	
	//initialize encryption and decryption class
	public static TrippleDes td;
	
	
	
	public ApplicationData() throws Exception{
		customerAccountData=new ArrayList<CustomerAccount>();
		
		customers=new ArrayList<Customer>();
		
		customerLoginDetails=new ArrayList<LoginDetails>();
		
		td = new TrippleDes();
	}
	
	
	

}

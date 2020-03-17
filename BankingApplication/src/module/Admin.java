package module;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import models.Customer;

public class Admin {

	public static void main(String[] args) {
		
		
	     String operationstr=JOptionPane.showInputDialog("Please select the operation you wish to perform\n1. Add the Cutomer\n2. Update the Customer\n3. Delete the Customer");
	     
	  
	  switch(operationstr)
      {
    	  case "1": 
    	  CustomerDetails();
    	  break;
      } 
	}
	
	public static void CustomerDetails()
	  {
		 String CustomerName;
		 String CustomerEmail;
		 String CustomerAddress;
		 int CustomerPhoneNumber;
		 String CustomerPincode;
		 String CustomerPhoneNumberStr;
		 String[] buttons = { "Yes", "No","Cancel" };
		 ArrayList<Customer> customerinfo = new ArrayList<>();
		  Customer cc=new Customer();
		  CustomerName=JOptionPane.showInputDialog("Please Enter the Custonmer Name");
		  cc.setCustomerName(CustomerName);
		  CustomerEmail=JOptionPane.showInputDialog("Please Enter the Custonmer Email");
		  cc.setCustomerEmail(CustomerEmail);
		  CustomerAddress=JOptionPane.showInputDialog("Please Enter the Custonmer Address");
		  cc.setCustomerAddress(CustomerAddress);
		  CustomerPhoneNumberStr=JOptionPane.showInputDialog("Please Enter the Custonmer Phone Number");
		  CustomerPhoneNumber=Integer.parseInt(CustomerPhoneNumberStr);
		  cc.setCustomerPhoneNumber(CustomerPhoneNumber);
		  CustomerPincode=JOptionPane.showInputDialog("Please Enter the Custonmer Pincode");
		  cc.setCustomerPincode(CustomerPincode);
		  int rc = JOptionPane.showOptionDialog(null, "Customer Name is : "+CustomerName+"\nCustomer Email is : "+CustomerEmail+"\nCustomer Address is : "+CustomerAddress+"\nCustomer Phone Number is : "+CustomerPhoneNumber+"\nCustomer Pincode is : "+CustomerPincode+"\n\nDo you confirm that the information is correct?", "Confirm",
		        JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);
		  if(rc==0)
		  {
		   customerinfo.add(cc);
		   System.out.println(customerinfo);
		  }
		  else if(rc==1)
		  {
			  CustomerDetails(); 
		  }
		  else
		  {
			  JOptionPane.showMessageDialog(null, "Cancelled");
		  }
	  }		  
}

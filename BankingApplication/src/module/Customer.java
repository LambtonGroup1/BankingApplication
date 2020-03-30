package module;

import java.util.ArrayList;
import java.util.Scanner;

import data.ApplicationData;
import models.CustomerAccount;
import models.Transaction;

public class Customer {

	public static float getCustomerBalance(int accountNumber) {

		// if returns 0, then could not find the customer in the database

		for (CustomerAccount custAcc : ApplicationData.customerAccountData) {
		
			if (custAcc.getAccountNumber() == accountNumber) {
				return custAcc.getCurrentBalance();
			}

		}

		return (float) 0;
	}

	public static boolean transferFunds(Scanner sc) {
		
		//if returns false, information given was invalid
		
		int toCustomerAccountNumber; float amount;
		
		System.out.println("Please enter the beneficiary account number: ");
		toCustomerAccountNumber=sc.nextInt();
		sc.nextLine();
		
		System.out.println("Please enter the amount: ");
		amount = sc.nextFloat();
		sc.nextLine();
		

		int fromCustomerAccountNumber=Utils.Utils.customerSessionObj.getCustomerAccountNumber();
				
		CustomerAccount toAcc = null;

		CustomerAccount fromAcc = null;

		for (CustomerAccount custAcc : ApplicationData.customerAccountData) {

			if (toAcc == null) {
				if (custAcc.getAccountNumber() == toCustomerAccountNumber) {
					toAcc = custAcc;
				}
			}

			if (fromAcc == null) {
				if (custAcc.getAccountNumber() == fromCustomerAccountNumber) {
					fromAcc = custAcc;
				}
			}

			if (toAcc != null && fromAcc != null) {
				break;
			}

		}

		if (fromAcc.getAccountNumber() == fromCustomerAccountNumber) {
			
			float fromCurrentBalance=fromAcc.getCurrentBalance();

			if ( fromCurrentBalance>= amount) {

				float toUpdatedBalance = toAcc.getCurrentBalance() + amount;
				
				// add transaction details in receiver
				Transaction toAccTransaction = new Transaction(fromCustomerAccountNumber, toCustomerAccountNumber, amount,
						"credit", toAcc.getCurrentBalance(), toUpdatedBalance);

				// credit the amount to receiver
				ApplicationData.customerAccountData.get(ApplicationData.customerAccountData.indexOf(toAcc))
						.setCurrentBalance(toUpdatedBalance);
				
				ApplicationData.customerAccountData.get(ApplicationData.customerAccountData.indexOf(toAcc))
				.getPreviousTransactions().add(toAccTransaction);

				// debit the amount from sender
				float fromUpdatedBalance = fromAcc.getCurrentBalance() - amount;
				// add transaction details in sender.
				Transaction fromAccTransaction = new Transaction(fromCustomerAccountNumber, toCustomerAccountNumber, amount,
						"debit", fromCurrentBalance,fromUpdatedBalance);
				
				ApplicationData.customerAccountData.get(ApplicationData.customerAccountData.indexOf(fromAcc))
						.setCurrentBalance(fromUpdatedBalance);

				ApplicationData.customerAccountData.get(ApplicationData.customerAccountData.indexOf(fromAcc))
						.getPreviousTransactions().add(fromAccTransaction);

				System.out.println("Transaction successful!");
				System.out.println("Your current balance is : "+fromUpdatedBalance);
				return true;
			}else{
				System.out.println("You do not have sufficient funds!");
				return false;
			}
		}

		return false;
	}

	public static ArrayList<Transaction> getTransactionDetails(int accountNumber){
		
		//if returns null, then accountNumber must be invalid
		
		for(CustomerAccount custAcc:ApplicationData.customerAccountData){
			
			if(custAcc.getAccountNumber()== accountNumber){
				return custAcc.getPreviousTransactions();
			}
			
		}
		
		return  null;
		
		
	}

}

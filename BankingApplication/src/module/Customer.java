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

		if (fromAcc.getCustomerId() == fromCustomerAccountNumber) {

			if (fromAcc.getCurrentBalance() >= amount) {

				float toUpdatedBalance = toAcc.getCurrentBalance() + amount;

				// credit the amount to receiver
				ApplicationData.customerAccountData.get(ApplicationData.customerAccountData.indexOf(toAcc))
						.setCurrentBalance(toUpdatedBalance);

				// debit the amount from sender
				float fromUpdatedBalance = fromAcc.getCurrentBalance() - amount;
				ApplicationData.customerAccountData.get(ApplicationData.customerAccountData.indexOf(fromAcc))
						.setCurrentBalance(fromUpdatedBalance);

				// add transaction details in receiver
				Transaction transaction = new Transaction(fromCustomerAccountNumber, toCustomerAccountNumber, amount,
						"credit", toAcc.getCurrentBalance(), toUpdatedBalance);

				ApplicationData.customerAccountData.get(ApplicationData.customerAccountData.indexOf(toAcc))
						.getPreviousTransactions().add(transaction);

				// add transaction details in sender.
				transaction.setTransactionType("debit");
				transaction.setCurrentBalance(fromUpdatedBalance);
				transaction.setPreviousBalance(fromAcc.getCurrentBalance());

				ApplicationData.customerAccountData.get(ApplicationData.customerAccountData.indexOf(fromAcc))
						.getPreviousTransactions().add(transaction);

				return true;
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

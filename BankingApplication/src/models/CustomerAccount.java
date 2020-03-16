package models;

import java.util.ArrayList;

public class CustomerAccount {
	
	private int customerId;
	
	private int currentBalance=1000;
	
	private ArrayList<Transaction> previousTransactions;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}

	public ArrayList<Transaction> getPreviousTransactions() {
		return previousTransactions;
	}

	public void setPreviousTransactions(ArrayList<Transaction> previousTransactions) {
		this.previousTransactions = previousTransactions;
	}
	

}

package models;

import java.util.ArrayList;

public class CustomerAccount {
	
	
	private int accountNumber;
	
	//default 1000 dollars
	private float currentBalance=1000f;
	
	private ArrayList<Transaction> previousTransactions;
	
	public CustomerAccount(){
		previousTransactions = new ArrayList<>();
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}


	public float getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}

	public ArrayList<Transaction> getPreviousTransactions() {
		return previousTransactions;
	}

	public void setPreviousTransactions(ArrayList<Transaction> previousTransactions) {
		this.previousTransactions = previousTransactions;
	}
	

}

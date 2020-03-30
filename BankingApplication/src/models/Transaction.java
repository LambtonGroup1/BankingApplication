package models;

import Utils.Utils;

public class Transaction {

	private int transactionId;

	private int fromCustomerNumber;

	private int toCustomerNumber;

	private float transactionAmount;

	private String transactionType;

	private float previousBalance;

	private float currentBalance;

	private String transactionDate;

	public Transaction(int fromCustomerNumber, int toCustomerNumber,float transactionAmount, String transactionType, float previousBalance,
			float currentBalance) {

		this.transactionId = Utils.getUniqueTransactionId();
		this.toCustomerNumber = toCustomerNumber;
		this.fromCustomerNumber = fromCustomerNumber;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
		this.previousBalance = previousBalance;
		this.currentBalance = currentBalance;
		this.transactionDate = Utils.getDateTime();

	}

	public int getFromCustomerNumber() {
		return fromCustomerNumber;
	}

	public void setFromCustomerNumber(int fromCustomerNumber) {
		this.fromCustomerNumber = fromCustomerNumber;
	}

	public int getToCustomerNumber() {
		return toCustomerNumber;
	}

	public void setToCustomerNumber(int toCustomerNumber) {
		this.toCustomerNumber = toCustomerNumber;
	}

	public float getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getFromCustomerId() {
		return fromCustomerNumber;
	}

	public void setFromCustomerId(int fromCustomerNumber) {
		this.fromCustomerNumber = fromCustomerNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public float getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(float previousBalance) {
		this.previousBalance = previousBalance;
	}

	public float getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}

}

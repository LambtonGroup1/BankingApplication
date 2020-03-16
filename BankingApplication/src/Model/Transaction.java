package Model;

public class Transaction {
	
	private int transactionId;
	
	private int fromCustomerId;
	
	private int toCustomerId;
	
	private int transactionAmount;
	
	private String transactionType;
	
	private int previousBalance;
	
	private int currentBalance;
	
	private String transactionDate;
	

	public int getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(int transactionAmount) {
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
		return fromCustomerId;
	}

	public void setFromCustomerId(int fromCustomerId) {
		this.fromCustomerId = fromCustomerId;
	}

	public int getToCustomerId() {
		return toCustomerId;
	}

	public void setToCustomerId(int toCustomerId) {
		this.toCustomerId = toCustomerId;
	}



	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(int previousBalance) {
		this.previousBalance = previousBalance;
	}

	public int getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	
	
	

}

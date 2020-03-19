package models;

public class LoginDetails {
	
	private int accountNumber;
	private String password;
	private boolean isTemp=true;
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isTemp() {
		return isTemp;
	}
	public void setTemp(boolean isTemp) {
		this.isTemp = isTemp;
	}
	
	
	
	

}

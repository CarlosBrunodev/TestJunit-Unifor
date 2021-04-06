package main;

import main.exceptions.AccountNumberInvalid;

public class Account {
	
	private Client client;
	private int accountNumber;
	private int balance;
	private AccountType accountType;
	
	public Account (Client client, int accountNumber, int balance ,AccountType accountType) {
		this.client = client;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
		
		
	}
	
	public void credit(int valor) { 
		this.balance = this.getBalance() + valor;
	}
	
	public void debit(int valor) {
		this.balance = this.getBalance() - valor;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public Client getClient() {
		return client;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public int getBalance() {
		return balance;
	}
}

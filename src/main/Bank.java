package main;

import main.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	
	private List<Account> accounts = new ArrayList<Account>();
	
	public Bank () {
	}
	
	public Bank(List<Account> accounts) {
		this.accounts =  accounts;
	}
	
	public void AccountRegister(Account account) throws AccountAlreadyExists , AccountNumberInvalid{
		
			for (Account c : accounts) {
			boolean isNomeClienteIgual = c.getClient().getNome().equals(account.getClient().getNome());
			boolean isNumeroContaIgual = c.getAccountNumber() == account.getAccountNumber();
			int isNumeroValido = account.getAccountNumber();
		
			if(isNomeClienteIgual || isNumeroContaIgual) {
				throw new AccountAlreadyExists();
			}
			
		}
		if (account.getAccountNumber() < 0) {
			throw new AccountNumberInvalid();
		} else {
		this.accounts.add(account);
	}
	}
	
	public void AccountTransfer(int numberAccountSource, int numberAccountDestination, int values ) 
		throws AccountNotAlreadyExists, AccountWithoutBalance , ValueUnavailable {
		
		if( values < 0) {
			throw new ValueUnavailable();
		}
		
		Account AccountSource = this.obterContaPorNumero(numberAccountSource); 
		Account AccountDestination = this.obterContaPorNumero(numberAccountDestination);
		
		boolean isContaOrigemExistente = AccountSource != null;
		boolean isContaDestinoExistente = AccountDestination != null;
		
		if (isContaOrigemExistente && isContaDestinoExistente) {
			
			boolean isContaOrigemPoupança = AccountSource.getAccountType().equals(AccountType.POUPANCA);
			boolean isSaldoContaOrigemNegativo = AccountSource.getBalance() - values < 0;
		
		
				if (isContaOrigemPoupança && isSaldoContaOrigemNegativo) {
						throw new AccountWithoutBalance();
				}
		
				AccountSource.debit(values);
				AccountDestination.credit(values);
		
		} else {
			throw new AccountNotAlreadyExists();
		}
		
	}
	
	public Account obterContaPorNumero(int accountNumber) {
		
		for (Account c : accounts ) {
			if(c.getAccountNumber() == accountNumber) {
				return c;
			}
		}
		return null;
	}
	
	public List<Account> getAccount(){
		return this.accounts;
	}
}

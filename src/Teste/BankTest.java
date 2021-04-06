package Teste;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Test;

import main.Bank;
import main.Client;
import main.Account;
import main.AccountType;

import main.exceptions.AccountAlreadyExists;
import main.exceptions.AccountNotAlreadyExists;
import main.exceptions.AccountNumberInvalid;
import main.exceptions.AccountWithoutBalance;
import main.exceptions.ValueUnavailable;

import java.util.Arrays;

public class BankTest {

	@Test
	public void deveCadastrarConta() throws AccountAlreadyExists,AccountNumberInvalid{
		
		//CENARIO 
		Client client = new Client("Joao");
		Account account = new Account(client,123,0,AccountType.CORRENTE);
		Bank bank = new Bank();
		
		//AÇÃO
		bank.AccountRegister(account);
		
		//VERIFICAÇÃO
		assertEquals(1,bank.getAccount().size());
	}
	
	@Test(expected = AccountAlreadyExists.class)
	public void naoDeveCadastrarNumeroRepetido() throws AccountAlreadyExists,AccountNumberInvalid{
		
		//CENARIO
		Client cliente1 = new Client("Joao");
		Account conta1 = new Account(cliente1, 123, 0 ,AccountType.CORRENTE);
		
		Client cliente2 = new Client("Maria");
		Account conta2 = new Account(cliente2, 123,0,AccountType.POUPANCA);
		
		Bank bank = new Bank();
		
		//AÇÃO
		bank.AccountRegister(conta1);
		bank.AccountRegister(conta2);
		
		
	Assert.fail();
	}
	
	@Test
	public void deveEfetuarTransferenciaContasCorrentes() throws AccountWithoutBalance, AccountNotAlreadyExists, AccountNumberInvalid, ValueUnavailable{
		
		//CENARIO
		Client client = new Client("Joao");
		Account contaOrigem = new Account(client , 123, 0 ,AccountType.CORRENTE);
		
		Client cliente2 = new Client("Maria");
		Account contaDestino= new Account(cliente2,456,0,AccountType.CORRENTE);
		
		Bank bank = new Bank(Arrays.asList(contaOrigem,contaDestino));
		
		
		//AÇÃO
		bank.AccountTransfer(123,456,1000);
		
		//VERIFICACAO
		assertEquals(-1000,contaOrigem.getBalance());
		assertEquals(1000,contaDestino.getBalance());
		
	}
	
	//SE A CONTA TIVER UM NUMERO NEGATIVO ELA LANÇA UMA EXCEPTION
	@Test(expected = AccountNumberInvalid.class)
	public void naoDeveCadastrarComNumeroInvalido() throws AccountNumberInvalid,AccountNotAlreadyExists, AccountAlreadyExists {
	
		//CENARIO 
		Client client = new Client("Joao");
		Account account = new Account(client,-140,0,AccountType.CORRENTE);
		Bank bank = new Bank();
		
		//AÇÃO
		bank.AccountRegister(account);
		
	}

	@Test(expected = AccountAlreadyExists.class)
	public void naoDeveCadastrarNomeJaExistente()
			throws AccountNumberInvalid, AccountNotAlreadyExists, AccountAlreadyExists {

		// CENARIO
		Client cliente1 = new Client("Maria");
		Account conta1 = new Account(cliente1, 003, 0, AccountType.CORRENTE);

		Client cliente2 = new Client("Maria");
		Account conta2 = new Account(cliente2, 789, 0, AccountType.POUPANCA);

		Bank bank = new Bank();

		// AÇÃO
		bank.AccountRegister(conta1);
		bank.AccountRegister(conta2);
	}
	
	//TESTE VALIDA SE O USUARIO ESTA TENTANDO TRANSFERIR UM VALOR NEGATIVO 
	@Test(expected= ValueUnavailable.class)
	public void naoDeveEfetuarTransferenciaNegativa() throws AccountWithoutBalance, AccountNotAlreadyExists, AccountNumberInvalid, ValueUnavailable{
		
		//CENARIO
		Client client = new Client("Joao");
		Account contaOrigem = new Account(client , 123, 0 ,AccountType.CORRENTE);
		
		Client cliente2 = new Client("Maria");
		Account contaDestino= new Account(cliente2,456,0,AccountType.CORRENTE);
		
		Bank bank = new Bank(Arrays.asList(contaOrigem,contaDestino));
		
		
		//AÇÃO
		bank.AccountTransfer(123,456,-1000);
		
		/*
		// VERIFICACAO
		assertEquals(-1000, contaOrigem.getSaldo());
		assertEquals(1000, contaDestino.getSaldo());
		*/
		
		
		
	}
	
}

package Teste;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import main.Client;
import main.Account;
import main.AccountType;
import main.exceptions.AccountNumberInvalid;

public class AccountTest {

	@Test 
	public void deveCreditar() throws AccountNumberInvalid {
		
		//CENARIO
		Client client = new Client("Joao");
		Account c = new Account(client , 123 ,10 , AccountType.CORRENTE);
		
		//AÇÃO
		c.credit(5);
		
		//VERIFICAÇÃO
		assertEquals(15, c.getBalance());
		assertThat(c.getBalance(),is(15));
		
	}
}

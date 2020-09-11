package model.teste.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import model.entities.Carteira;

// Se eu quiser usar o comando "mvn test" o nome da classe tem que começar com "Test"
public class TesteCarteira {
	private Carteira carteira;
	
	@Before
	public void init() {
		carteira = new Carteira("Rico Investimentos");
	}
	
	@Test
	public void testeExisteCarteira() {
		assertNotNull(carteira);
	}
	
	@Test
	public void testeCarteiraVazia() {
		int qtdInvestimentos = carteira.getInvestimentos().size();
		
		assertEquals(0, qtdInvestimentos);
	}
}

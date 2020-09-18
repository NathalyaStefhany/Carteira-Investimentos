package model.teste.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import model.arquivos.BuscaInvestimento;
import model.entities.RendaFixa;
import model.interfaces.BuscaInvestimentoService;
import model.teste.consts.InvestimentoConst;

@RunWith(MockitoJUnitRunner.class)
public class TesteBuscaInvestimento {
	@Mock
	private BuscaInvestimentoService service;
	
	@InjectMocks
	private BuscaInvestimento buscaInvestimento;
	
	@Test
	public void testeBuscaRendaFizxa() {
		String nome = "Tesouro Selic 2025";
		
		Mockito.when(service.busca(nome)).thenReturn(InvestimentoConst.RENDA_FIXA);
		
		RendaFixa rendaFixa = buscaInvestimento.getRendaFixa(nome);
		
		assertEquals(70.0, rendaFixa.getRendimento(), 0.01);
		assertEquals("Tesouro Selic 2025", rendaFixa.getNome());
		assertEquals("20/10/2020", rendaFixa.getDataVencimento());
		assertEquals(78.65, rendaFixa.getPreco(), 0.01);
		assertEquals(50.0, rendaFixa.getQuantidade(), 0.01);
	}
}

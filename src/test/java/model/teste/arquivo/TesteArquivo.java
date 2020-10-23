package model.teste.arquivo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.arquivos.Arquivo;
import model.arquivos.CsvStrategy;
import model.entities.Carteira;
import model.entities.RendaFixa;

public class TesteArquivo {
	// Referências
	private Carteira carteira;
	private RendaFixa rendaFixa;
	private Arquivo arquivo;
	//private File jsonFile;
	//private File csvFile;
	
	@Before
	public void init() {
		// Faz a instânciação do objeto
		carteira = new Carteira("Rico Investimentos");
		rendaFixa = new RendaFixa("Tesouro Selic 2025", new Date(), 78.65, 50, "20/10/2025", 2.75);
		arquivo = new Arquivo();
		//jsonFile = new File("Investimentos.json");
		//csvFile = new File("Investimentos.csv");
	}
	
	@Test
	public void testeExisteArquivoJson() {
		boolean existe = new File("Investimentos.json").exists();
		//boolean existe = jsonFile.exists();
		
		assertTrue(existe);
	}
	
	@Test
	public void testeExisteArquivoCsv() {
		boolean existe = new File("Investimentos.csv").exists();
		//boolean existe = csvFile.exists();
		
		assertTrue(existe);
	}
	
	@Test
	public void testeGerouArquivoJson() {
		carteira.getInvestimentos().add(rendaFixa);
		
		arquivo.gerar(carteira.getInvestimentos());
		
		assertNotNull(arquivo.getArquivoStrategy().getFileWriter());
	}
	
	@Test
	public void testeGerouArquivoCsv() {
		carteira.getInvestimentos().add(rendaFixa);
		
		arquivo.setArquivoStrategy(new CsvStrategy());
		arquivo.gerar(carteira.getInvestimentos());
		
		assertNotNull(arquivo.getArquivoStrategy().getFileWriter());
	}
}

package application;

import java.util.Scanner;

import model.arquivos.Arquivo;
import model.arquivos.CsvStrategy;
import model.entities.Carteira;
import model.entities.Investimento;
import model.entities.RendaFixa;
import model.interfaces.CarteiraFactory;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int op = -1;
		
		Carteira carteira = null;
		
		Arquivo arquivo = new Arquivo();
		
		while(op != 0) {
			System.out.println("[1] Criar Carteira");
			System.out.println("[2] Renda Fixa");
			System.out.println("[3] Renda Variável");
			System.out.println("[4] Gerar JSON");
			System.out.println("[5] Gerar CSV");
			System.out.println("[0] Sair");
			
			System.out.print("Entre com uma opção: ");
			op = sc.nextInt();
			
			switch(op) {
				case 1:
					carteira = CarteiraFactory.createCarteira(sc);
					break;
				case 2:
					Investimento investimento = new RendaFixa();
					carteira.adicionarInvestimento(investimento, sc, carteira);
				case 3:
					break;
				case 4:
					if(carteira != null)
						arquivo.gerar(carteira.getInvestimentos());
					break;
				case 5:
					if(carteira != null) {
						arquivo.setArquivoStrategy(new CsvStrategy());
						arquivo.gerar(carteira.getInvestimentos());
					}
					break;
				case 0:
					break;
			}
		}
		
		sc.close();
	}
}

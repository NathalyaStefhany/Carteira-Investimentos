package model.interfaces;

import java.util.Date;
import java.util.Scanner;

import model.entities.Carteira;
import model.entities.RendaFixa;

public class InvestimentoFactory {
	public static RendaFixa createRendaFixa(Scanner sc, Carteira carteira) {
		if(carteira != null) {
			sc.nextLine();
			
			System.out.print("Nome: ");
			String nome = sc.nextLine();
			
			System.out.print("Preço: ");
			double preco = sc.nextDouble();
			
			System.out.print("Quantidade: ");
			double quantidade = sc.nextDouble();
			
			sc.nextLine();
			
			System.out.print("Data de Vencimento: ");
			String dataVencimento = sc.nextLine();
			
			System.out.print("Rendimento: ");
			double rendimento = sc.nextDouble();
			
			return new RendaFixa(nome, new Date(), preco, quantidade, dataVencimento, rendimento);
		}
		return null;
	}
}

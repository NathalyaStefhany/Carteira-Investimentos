package model.interfaces;

import java.util.Scanner;

import model.entities.Carteira;

public class CarteiraFactory {
	public static Carteira createCarteira(Scanner sc) {
		sc.nextLine();
		
		System.out.print("Entre com o nome da corretora: ");
		String corretora = sc.nextLine();
		
		return new Carteira(corretora);
	}
}

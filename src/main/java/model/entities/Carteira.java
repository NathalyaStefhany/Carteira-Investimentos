package model.entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVWriter;

import model.interfaces.InvestimentoFactory;

public class Carteira {
	private String corretora;
	private double valorInvestido;
	
	List<Investimento> investimentos;
	
	public Carteira(String corretora) {	
		this.corretora = corretora;
		
		this.investimentos = new ArrayList<Investimento>();
	}

	public String getCorretora() {
		return corretora;
	}

	public void setCorretora(String corretora) {
		this.corretora = corretora;
	}

	public double getValorInvestido() {
		return valorInvestido;
	}

	public void setValorInvestido(double valorInvestido) {
		this.valorInvestido = valorInvestido;
	}
	
	public void adicionarInvestimento(Investimento investimento, Scanner sc, Carteira carteira) {
		if(investimento instanceof RendaFixa) {
			investimento = InvestimentoFactory.createRendaFixa(sc, carteira);
			
			valorInvestido += investimento.getQuantidade() * investimento.getPreco();
			
			investimentos.add(investimento);
		}
	}
	
	public void gerarJson() {
		if(investimentos != null) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(investimentos);
			
			FileWriter fileWriter;
			
			try {
				// true é para não perder as informações que já estavam no arquivo (escrever em baixo)
				fileWriter = new FileWriter("Investimentos.json", true);
				fileWriter.write(json);
				fileWriter.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void gerarCsv() {
		File file = new File("Investimentos.csv");
		FileWriter fileWriter;
		
		try {
			fileWriter = new FileWriter(file, true);
			
			CSVWriter csvWriter = new CSVWriter(fileWriter);
			
			String[] header = {"Nome", "Data de Compra", "Data de Vencimento", "Preco", "Quantidade", "Rendimento"};
			csvWriter.writeNext(header);
			
			for (Investimento investimento : investimentos) {
				if(investimento instanceof RendaFixa) {
					RendaFixa rendaFixaAux = (RendaFixa) investimento;
					
					String[] data = {
							rendaFixaAux.getNome(),
							rendaFixaAux.getDataCompra().toString(),
							rendaFixaAux.getDataVencimento(),
							String.valueOf(rendaFixaAux.getPreco()),
							String.valueOf(rendaFixaAux.getQuantidade()),
							String.valueOf(rendaFixaAux.getRendimento())
					};
					
					csvWriter.writeNext(data);
				}
			}
			csvWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

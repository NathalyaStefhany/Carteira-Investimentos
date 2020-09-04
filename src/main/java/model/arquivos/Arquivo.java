package model.arquivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVWriter;

import model.entities.Investimento;
import model.entities.RendaFixa;

public class Arquivo {
	private static File file;
	private static FileWriter fileWriter;

	public static void gerarJson(List<Investimento> investimentos) {
		if (investimentos != null) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(investimentos);

			try {
				// true é para não perder as informações que já estavam no arquivo (escrever embaixo)
				fileWriter = new FileWriter("Investimentos.json", true);
				
				fileWriter.write(json);
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void gerarCSV(List<Investimento> investimentos) {
		if (investimentos != null) {
			file = new File("Investimentos.csv");

			try {
				fileWriter = new FileWriter(file, true);

				CSVWriter csvWriter = new CSVWriter(fileWriter);

				String[] header = { "Nome", "Data de Compra", "Data de Vencimento", "Preco", "Quantidade", "Rendimento" };
				csvWriter.writeNext(header);

				for (Investimento investimento : investimentos) {
					if (investimento instanceof RendaFixa) {
						RendaFixa rendaFixaAux = (RendaFixa) investimento;

						String[] data = { 
								rendaFixaAux.getNome(), 
								rendaFixaAux.getDataCompra().toString(),
								rendaFixaAux.getDataVencimento(), 
								String.valueOf(rendaFixaAux.getPreco()),
								String.valueOf(rendaFixaAux.getQuantidade()),
								String.valueOf(rendaFixaAux.getRendimento()) };

						csvWriter.writeNext(data);
					}
				}
				csvWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

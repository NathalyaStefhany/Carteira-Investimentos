package model.arquivos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.entities.Investimento;

public class JsonStrategy extends ArquivoStrategy {
	@Override
	public void gerar(List<Investimento> investimentos) {
		if (investimentos != null) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(investimentos);

			try {
				// true � para n�o perder as informa��es que j� estavam no arquivo (escrever embaixo)
				fileWriter = new FileWriter("Investimentos.json", true);
				
				fileWriter.write(json);
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

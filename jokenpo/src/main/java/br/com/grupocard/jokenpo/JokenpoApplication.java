package br.com.grupocard.jokenpo;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.grupocard.jokenpo.model.Partida;

@SpringBootApplication
@ComponentScan("br.com.grupocard.jokenpo")
public class JokenpoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JokenpoApplication.class, args);

		//Ao iniciar a aplicacao, criar uma partida
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Partida p = new Partida();
		//Iniciar partida com status pendente
		p.setStatus("Pendente");
		p.setId(1L);

		//Criando arquivo .json que ira representar o banco de dados
		try (FileWriter writer = new FileWriter("src/main/resources/partida.json")) {
			gson.toJson(p, writer);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

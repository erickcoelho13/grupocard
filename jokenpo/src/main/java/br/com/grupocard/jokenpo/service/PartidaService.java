package br.com.grupocard.jokenpo.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import br.com.grupocard.jokenpo.model.Jogada;
import br.com.grupocard.jokenpo.model.Jogador;
import br.com.grupocard.jokenpo.model.Partida;

//Classe responsável por manipular a camada de serviço
@Component("partidaService")
public class PartidaService {

	// Arquivo Json responsável por armazenar os dados da Aplicação(Partida e seus
	// Jogos.)
	String file = "src/main/resources/partida.json";

	Gson gson = new Gson();

	// Método responsável por instanciar um jogador.
	public Jogador criarJogador(String nome) {
		Jogador j = new Jogador();
		j.setNome(nome);
		return j;
	}

	// Método responsável por instanciar uma jogada.
	public Jogada criarJogada(String descricao) {
		Jogada jogad = new Jogada();
		jogad.setDescricao(descricao);
		return jogad;
	}

	// Método responsável por instanciar uma partida, bem como controlar seus
	// detalhes
	// Recebe como parâmetro um jogador e uma jogada
	public Partida atualizarPartida(String jogador, String jogada) {
		try {
			// Ler arquivo Json com a partida salva
			FileReader fr = new FileReader(file);
			Partida partida = gson.fromJson(fr, Partida.class);
			fr.close();

			// Recebe o jogador passado como parâmetro e instancia
			Jogador j = criarJogador(jogador);

			// Recebe a jogada passada como parâmetro e instancia
			Jogada jogad = criarJogada(jogada);

			// Atualizar lista de jogadores
			List<Jogador> jogadores = partida.getJogadores();
			if (jogadores == null)
				jogadores = new ArrayList<Jogador>();
			jogadores.add(j);

			// Atualizar lista de jogadas
			List<Jogada> jogadas = partida.getJogadas();
			if (jogadas == null)
				jogadas = new ArrayList<Jogada>();
			jogadas.add(jogad);

			// Atualizar listas da partida
			partida.setJogadores(jogadores);
			partida.setJogadas(jogadas);

			// Verificar status da partida e atualizar o vencedor ou não
			if (partida.getStatus().equals("Pendente"))
				partida.setStatus("Iniciada");
			else if (partida.getStatus().equals("Iniciada")) {
				String vencedor = obterVencedor(partida);

				partida.setVencedor(vencedor);
				partida.setStatus("Finalizada");
			}

			salvarPartida(partida);

			return partida;
		} catch (JsonSyntaxException | JsonIOException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}

	//Método responsável por atualizar nosso banco de dados(Arquivo.json)
	public void salvarPartida(Partida partida) {
		FileWriter wr;
		try {
			wr = new FileWriter("src/main/resources/partida.json");
			gson.toJson(partida, wr);
			//Liberar recurso
			wr.flush();
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Método responsavel pela lógica de obtenção de resultado do jogo
	public String obterVencedor(Partida partida) throws IOException {
		
		//Caso as jogadas sejam iguais, empate
		if (partida.getJogadas().get(0).getDescricao().equalsIgnoreCase(partida.getJogadas().get(1).getDescricao()))
			return "Empate";
		
		//Comparando jogadas e verificando o vencedor
		//0:Jogador 1
		//1:Jogador 2
		switch (partida.getJogadas().get(0).getDescricao()) {
		case "pedra":
			return (partida.getJogadas().get(1).getDescricao().equalsIgnoreCase("TESOURA")
					? partida.getJogadores().get(0).getNome()
					: partida.getJogadores().get(1).getNome());
		case "papel":
			return (partida.getJogadas().get(1).getDescricao().equalsIgnoreCase("TESOURA")
					? partida.getJogadores().get(1).getNome()
					: partida.getJogadores().get(0).getNome());
		case "tesoura":
			return (partida.getJogadas().get(1).getDescricao().equalsIgnoreCase("PAPEL")
					? partida.getJogadores().get(0).getNome()
					: partida.getJogadores().get(1).getNome());
		}

		return "";
	}

	//Método responsável por consultar a partida(Modelo) no banco de dados(Arquivo.json)
	public Partida consultarPartida() {
		try {
			FileReader fr = new FileReader(file);
			Partida partida = gson.fromJson(fr, Partida.class);
			fr.close();

			return partida;
		} catch (JsonSyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (JsonIOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}

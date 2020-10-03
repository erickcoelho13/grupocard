package br.com.grupocard.jokenpo.model;

import java.util.List;

//Classe responsável pelo Model Partida
public class Partida {
	
	private Long id;
	private String vencedor;
	private List<Jogador> jogadores;
	private List<Jogada> jogadas;
	private String status;
	
	public String getVencedor() {
		return vencedor;
	}
	public void setVencedor(String vencedor) {
		this.vencedor = vencedor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Jogador> getJogadores() {
		return jogadores;
	}
	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	public List<Jogada> getJogadas() {
		return jogadas;
	}
	public void setJogadas(List<Jogada> jogadas) {
		this.jogadas = jogadas;
	}

}

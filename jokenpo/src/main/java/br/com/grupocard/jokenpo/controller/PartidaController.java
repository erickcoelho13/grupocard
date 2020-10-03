package br.com.grupocard.jokenpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupocard.jokenpo.model.Partida;
import br.com.grupocard.jokenpo.service.PartidaService;

@RestController
@RequestMapping(path = "/jogadas")
public class PartidaController {

	// Injeção de Dependência:DI
	@Autowired
	private PartidaService partidaService;

	// Método responsável por inserir jogada
	@PostMapping(path = "/{jogada}/{jogador}")
	public @ResponseBody Partida setJogada(@PathVariable String jogada, @PathVariable String jogador) {
		Partida partida = partidaService.atualizarPartida(jogador, jogada);

		return partida;
	}

	// Método responsável por consultar o resultado da partida
	@GetMapping(path = "/resultado")
	// Autorizar consulta para o front-end
	@CrossOrigin(origins = "http://localhost:8000")
	public @ResponseBody Partida getResultado() {
		Partida partida = partidaService.consultarPartida();

		return partida;
	}

}
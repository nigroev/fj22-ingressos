package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.junit.Assert;

public class GerenciadordeSessaoTest {
	
	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario()
	{
		Filme filme =new Filme("Rogue One", Duration.ofMinutes(120), "SCI_FI", BigDecimal.ONE);
		LocalTime horario = LocalTime.parse("10:00:00");
		
		Sala sala = new Sala("");
		List<Sessao> sessoes = Arrays.asList(new Sessao(horario,filme,sala));
		
		Sessao sessao = new Sessao(horario,filme,sala);
		
		GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciadorDeSessao.cabe(sessao));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente() {
		Filme filme =new Filme("Rogue One", Duration.ofMinutes(120), "SCI_FI",BigDecimal.ONE);
		LocalTime horario = LocalTime.parse("10:00:00");
		
		Sala sala = new Sala("");
		List<Sessao> sessoes = Arrays.asList(new Sessao(horario,filme,sala));
		
		Sessao sessao = new Sessao(horario.minusHours(1),filme,sala);
		
		GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciadorDeSessao.cabe(sessao));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente() {
		Filme filme =new Filme("Rogue One", Duration.ofMinutes(120), "SCI_FI",BigDecimal.ONE);
		LocalTime horario = LocalTime.parse("10:00:00");
		
		Sala sala = new Sala("");
		List<Sessao> sessoes = Arrays.asList(new Sessao(horario,filme,sala));
		
		Sessao sessao = new Sessao(horario.plusHours(1),filme,sala);
		
		GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciadorDeSessao.cabe(sessao));
	}
	
	@Test
	public void garanteQueDevePermitirUmaIsercaoEntreDoisFilMes() {
		Sala sala = new Sala("");
		
		Filme filme1 =new Filme("Rogue One", Duration.ofMinutes(120), "SCI_FI",BigDecimal.ONE);
		LocalTime dezHoras = LocalTime.parse("10:00:00");
		Sessao sessaoDasDez = new Sessao(dezHoras,filme1,sala);
		
		Filme filme2 =new Filme("Rogue One", Duration.ofMinutes(120), "SCI_FI",BigDecimal.ONE);
		LocalTime dezoitoHoras = LocalTime.parse("10:00:00");
		Sessao sessaoDasDezoito = new Sessao(dezoitoHoras,filme2,sala);
		
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez,sessaoDasDezoito);
		
		GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessoes);
		
		Assert.assertTrue(gerenciadorDeSessao.cabe(new Sessao(LocalTime.parse("13:00:00"),filme2,sala)));
		
		
		
		

	}
}

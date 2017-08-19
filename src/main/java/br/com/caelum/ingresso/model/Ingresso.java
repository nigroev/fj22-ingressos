package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Ingresso {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Sessao sessao;
	private BigDecimal preco;
	
	@ManyToOne
	private Lugar lugar;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	@Enumerated
	private TipoDeIngresso tipoDeIngresso;
	
	@Deprecated
	public Ingresso(){
		
	}
	
	public TipoDeIngresso getTipoDeIngresso() {
		return tipoDeIngresso;
	}

	public void setTipoDeIngresso(TipoDeIngresso tipoDeIngresso) {
		this.tipoDeIngresso = tipoDeIngresso;
	}

	public Ingresso(Sessao sessao, TipoDeIngresso tipoDeIngresso, Lugar lugar){
		this.sessao = sessao;
		this.tipoDeIngresso = tipoDeIngresso;
		this.preco = this.tipoDeIngresso.aplicarDesconto(sessao.getPreco());
		this.lugar = lugar;
	}
	
	public Sessao getSessao(){
		return sessao;
	}
	
	public BigDecimal getPreco(){
		return preco;
	}

	public Lugar getLugar() {
		return lugar;
	}

}

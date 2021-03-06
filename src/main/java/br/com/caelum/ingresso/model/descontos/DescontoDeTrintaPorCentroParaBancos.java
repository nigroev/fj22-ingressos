package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoDeTrintaPorCentroParaBancos implements Desconto {
	
	private BigDecimal percentualDeDesconto = new BigDecimal("0.3");
	
	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.subtract(trintaPorCentroSobre(precoOriginal));
	}
	
	private BigDecimal trintaPorCentroSobre(BigDecimal precoOriginal){
		return precoOriginal.multiply(percentualDeDesconto);
	}
	
	@Override
	public String getDescricao() {
		return "Desconto Banco";
	}

}

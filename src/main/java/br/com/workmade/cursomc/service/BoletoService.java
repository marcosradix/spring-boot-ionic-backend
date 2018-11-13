package br.com.workmade.cursomc.service;

import java.util.Date;

import br.com.workmade.cursomc.domain.PagamentoComBoleto;

public interface BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagB, Date instantePedido);

}

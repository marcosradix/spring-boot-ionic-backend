package br.com.workmade.cursomc.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.workmade.cursomc.domain.PagamentoComBoleto;
@Service
public class BoletoService implements Serializable{

	private static final long serialVersionUID = -5929321353230029590L;

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagB, Date instantePedido) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantePedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagB.setDataVencimento(cal.getTime());
		
	}

}

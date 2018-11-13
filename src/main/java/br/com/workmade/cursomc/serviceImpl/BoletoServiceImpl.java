package br.com.workmade.cursomc.serviceImpl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.workmade.cursomc.domain.PagamentoComBoleto;
import br.com.workmade.cursomc.service.BoletoService;
@Service
public class BoletoServiceImpl implements BoletoService ,Serializable{

	private static final long serialVersionUID = -5929321353230029590L;

	@Override
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagB, Date instantePedido) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantePedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagB.setDataVencimento(cal.getTime());
		
	}

}

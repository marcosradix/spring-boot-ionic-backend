package br.com.workmade.cursomc.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.cursomc.domain.ItemPedido;
import br.com.workmade.cursomc.repositories.ItemPedidoRepository;
import br.com.workmade.cursomc.service.ItemPedidoService;
@Service
public class ItemPedidoImpl implements ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Override
	public ItemPedido salvar(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}

	@Override
	public Collection<ItemPedido> salvarTodos(Collection<ItemPedido> itensPedidos) {
		return itemPedidoRepository.saveAll(itensPedidos);
	}

}

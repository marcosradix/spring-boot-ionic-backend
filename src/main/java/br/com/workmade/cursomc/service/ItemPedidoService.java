package br.com.workmade.cursomc.service;

import java.util.Collection;

import br.com.workmade.cursomc.domain.ItemPedido;

public interface ItemPedidoService {

public ItemPedido salvar(ItemPedido itemPedido);
	
	public Collection<ItemPedido> salvarTodos(Collection<ItemPedido> itensPedidos);
	
	
	
}

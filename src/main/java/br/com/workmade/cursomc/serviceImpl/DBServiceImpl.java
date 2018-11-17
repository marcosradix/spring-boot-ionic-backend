package br.com.workmade.cursomc.serviceImpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.workmade.cursomc.domain.Categoria;
import br.com.workmade.cursomc.domain.Cidade;
import br.com.workmade.cursomc.domain.Cliente;
import br.com.workmade.cursomc.domain.Endereco;
import br.com.workmade.cursomc.domain.Estado;
import br.com.workmade.cursomc.domain.ItemPedido;
import br.com.workmade.cursomc.domain.Pagamento;
import br.com.workmade.cursomc.domain.PagamentoComBoleto;
import br.com.workmade.cursomc.domain.PagamentoComCartao;
import br.com.workmade.cursomc.domain.Pedido;
import br.com.workmade.cursomc.domain.Produto;
import br.com.workmade.cursomc.domain.enums.EstadoPagamento;
import br.com.workmade.cursomc.domain.enums.Perfil;
import br.com.workmade.cursomc.domain.enums.TipoCliente;
import br.com.workmade.cursomc.repositories.CategoriaRepository;
import br.com.workmade.cursomc.repositories.CidadeRepository;
import br.com.workmade.cursomc.repositories.ClienteRepository;
import br.com.workmade.cursomc.repositories.EnderecoRepository;
import br.com.workmade.cursomc.repositories.EstadoRepository;
import br.com.workmade.cursomc.repositories.ItemPedidoRepository;
import br.com.workmade.cursomc.repositories.PagamentoRepository;
import br.com.workmade.cursomc.repositories.PedidoRepository;
import br.com.workmade.cursomc.repositories.ProdutoRepository;
import br.com.workmade.cursomc.service.DBService;
@Service
public class DBServiceImpl implements DBService, Serializable {

	private static final long serialVersionUID = -8914813014781603818L;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void instantiateDataBase() throws ParseException{
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", new BigDecimal(2000.00));
		Produto p2 = new Produto(null, "Impressora", new BigDecimal(800.00));
		Produto p3 = new Produto(null, "Mouse", new BigDecimal(80.00));
		Produto p4 = new Produto(null, "Mesa de escritório",new BigDecimal(300.00));
		Produto p5 = new Produto(null, "Toalha", new BigDecimal(50.00));
		Produto p6 = new Produto(null, "Colcha", new BigDecimal(200.00));
		Produto p7 = new Produto(null, "TV true color", new BigDecimal(1200.00));
		Produto p8 = new Produto(null, "Roçadeira", new BigDecimal(800.00));
		Produto p9 = new Produto(null, "Abajour", new BigDecimal(100.00));
		Produto p10 = new Produto(null, "Pendente", new BigDecimal(180.00));
		Produto p11 = new Produto(null, "Shampoo", new BigDecimal(90.00));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Ceará");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade(null, "Fortaleza", est3);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		est3.getCidades().addAll(Arrays.asList(c4));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		
		Cliente cli1 = new Cliente(null, "Marcos Silva", "marcosradix@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA, bCryptPasswordEncoder.encode("1234567890"));
		Cliente cli2 = new Cliente(null, "Marcos Ferreira", "marcosradix@outlook.com", "04127612380", TipoCliente.PESSOA_FISICA, bCryptPasswordEncoder.encode("1234567890"));
		cli2.getTelefones().addAll(Arrays.asList("85999575446", "85985386973"));
		cli2.addPerfil(Perfil.ADMIN);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco(null, "Rua dos coelhos", "115", "B", "Maraponga", "60710705", cli2, c4);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().add(e3);
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
				
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, new BigDecimal(0.00), 1, p1.getPreco());
		ItemPedido ip2 = new ItemPedido(ped1, p3, new BigDecimal(0.00), 2, p3.getPreco());
		ItemPedido ip3 = new ItemPedido(ped2, p2, new BigDecimal(100.00), 1, p2.getPreco());
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));	
		
		


	}

}

package pedido;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


import usuario.Usuario;

public class Pedido {
	
	private int idPedido;
	
	private Date moment;
	private StatusPedido status;
	private Usuario usuario;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private List<ItemPedido> items = new ArrayList<>();
	
	// CONSTRUTOR
	public Pedido(Usuario usuario) {
		this.idPedido = ThreadLocalRandom.current().nextInt(100, 1000);
		this.moment = new Date();
		this.status = StatusPedido.ABERTO;
		this.usuario = usuario;
	}

	
	// GETTERS
	public int getIdPedido() {
		return idPedido;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public StatusPedido getStatus() {
		return status;
	}
	
	public double getTotal() {
		return calcularTotal();
	}
	
	public Date getMoment() {
		return moment;
	}
	
	public List<ItemPedido> getItems() {
	    return items;
	}
	
	// SETTERS
	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	
	// PUBLICO
	public void adicionarItem(ItemPedido itemPedido) {
		items.add(itemPedido);
	}
	
	public void removerItem(ItemPedido itemPedido) {
		items.remove(itemPedido);
	}
	
	public double calcularTotal() {
		double soma = 0.0;
		
		for(ItemPedido it : items) {
			soma += it.calcularSubTotal();
		}
		return soma;
	}
	
	public boolean finalizarPedido() {
		if(status == StatusPedido.ABERTO) {
			this.status = StatusPedido.FINALIZADO;
			return true;
		}
		return false;
	}
	
	public boolean cancelarPedido(StatusPedido respostaUsuario) {
		if(StatusPedido.CANCELADO == respostaUsuario) {
			this.status = StatusPedido.CANCELADO;
			return true;
		}
		return false;	
	}
	
public void exibirListaPedido () {
		
		System.out.println("╔══════════════════════════════════════════════════╗");
		System.out.println("║                  LISTA DE PEDIDOS                ║");
		System.out.println("╚══════════════════════════════════════════════════╝");
		System.out.println("Nome do cliente: "+ usuario.getNome());
		System.out.println("ID do cliente: "+ usuario.getIdUsuario());
		System.out.println("Telefone: "+ usuario.getTelefone());
		System.out.println("CPF: "+ usuario.getCpf());
		System.out.println("e-mail: "+ usuario.getEmail());
		
		for (ItemPedido it: items) {
			System.out.println(
					"Nome do produto: " + it.getProduto().getNomeProduto() +
					"ID do produto: " + it.getProduto().getIdProduto() +
					"Data do pedido: " + sdf.format(moment) +
					"Preço unitário: R$ " + it.getPrecoUnitario() +
					"Quantidade: " + it.getQuantidade()
					);
		}
		System.out.printf("Valor Total do pedido: R$ %.2f%n", getTotal());	
	}	

}

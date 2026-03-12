package pedido;

import produto.Produto;

public class ItemPedido {
	
	private int quantidade;
	private double precoUnitario;
	
	private Produto produto;
	
	
	// CONSTRUTOR
	public ItemPedido(int quantidade, Produto produto) {
		setQuantidade(quantidade);
		setPrecoUnitario(produto.getPreco());
		this.produto = produto;
	}

	// GETTERS
	public int getQuantidade() {
		return quantidade;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public double getPrecoUnitario() {
		return precoUnitario;
	}

	// SETTERS
	public void setQuantidade(int quantidade) {
		this.quantidade = Math.max(1, quantidade);
	}

	private void setPrecoUnitario(double precoUnitario) {
		if(precoUnitario < 0) {
			this.precoUnitario = 0;
		}
		else {
			this.precoUnitario = precoUnitario;
		}	
	}

	// PUBLICO
	public double calcularSubTotal() {
		return precoUnitario * quantidade;
	}
	
	public void adicionarQuantidade(int quantidade) {
		setQuantidade(this.quantidade + quantidade);
	}
	
	public boolean removerQuantidade(int quantidade) {
		if(this.quantidade >= quantidade) {
			setQuantidade(this.quantidade - quantidade);
			return true;
		}
		return false;
	}
}

package produto;

import java.util.concurrent.ThreadLocalRandom;

public class Produto {
	
	private String nomeProduto;
	private int idProduto;
	private double preco;
	
	// CONSTRUTOR
	
	public Produto() {
	}
	public Produto(String nomeProduto, double preco) {
		this.nomeProduto = nomeProduto;
		this.idProduto = ThreadLocalRandom.current().nextInt(1000, 10000);
		this.preco = preco;
	}
	
	// GETTERS
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public double getPreco() {
		return preco;
	}
}
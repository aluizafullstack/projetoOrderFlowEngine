package produto;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
	
	private List<Item> estoque = new ArrayList<>();
	
	//CONSTRUTOR
	public Estoque() {
		
	}
	
	// PRIVADO
	
	private Item buscarItem (int idProduto) {
		
		for (Item item : estoque) {
			if(item.getProduto().getIdProduto() == idProduto) {
				return item;
			}
		}
		return null;
	}
	
	// PUBLICO
	
	public Produto buscarProdutoPorNome(String nomeProduto) {
		for(Item item : estoque) {
			if(item.getProduto().getNomeProduto().equalsIgnoreCase(nomeProduto)) {
				return item.getProduto();
			}
		}
		return null;
	}
			
	public void adicionarProduto (Produto produto, int quantidade) {
		
		Item item = buscarItem(produto.getIdProduto());
		
		if(item != null) {
			item.adicionarQuantidade(quantidade);
		}
		else {
			estoque.add(new Item(produto, quantidade));
		}
	}
	
	public boolean removerProduto (Produto produto, int quantidade) {
		
		Item item = buscarItem(produto.getIdProduto());
		
		if (item == null) {
			return false;
		}
		else {
			return item.removerQuantidade(quantidade);
		}
	}
	
	public int consultarQuantidade(int idProduto) {
		Item item = buscarItem(idProduto);
		if(item == null) {
			return 0;
		}
		return item.getQuantidade();
	}
	
	public void exibirEstoque () {
		
		System.out.println("╔══════════════════════════════════════════════════╗");
		System.out.println("║                PRODUTO EM ESTOQUE                ║");
		System.out.println("╚══════════════════════════════════════════════════╝");
		
		for (Item item: estoque) {
			Produto p = item.getProduto();
			System.out.println(
					"Nome: " + p.getNomeProduto() +
					"ID: " + p.getIdProduto() +
					"Preço: R$ " + p.getPreco() +
					"Quantidade: " + item.getQuantidade()
					);
		}
	}
}
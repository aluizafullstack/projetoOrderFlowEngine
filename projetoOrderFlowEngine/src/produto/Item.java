package produto;

public class Item {
	
	private Produto produto;
	private int quantidade;
	
	// CONSTRUTOR
	
	public Item(Produto produto, int quantidade) {
		this.produto = produto;
		setQuantidade(quantidade);
	}
	
	// GETTERS
	
	public Produto getProduto() {
		return produto;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	// PRIVADO

	private void setQuantidade(int quantidade) {
		this.quantidade = Math.max(0, quantidade);
	}
	
	// PUBLICO
	
	public void adicionarQuantidade (int quantidade) {
		setQuantidade(this.quantidade + quantidade);
	}
	
	public boolean removerQuantidade(int quantidade) {
		
		if (this.quantidade >= quantidade) {
			setQuantidade(this.quantidade - quantidade);
			return true;
		}
		return false;
	}
}
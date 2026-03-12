package pedido;

import produto.Estoque;
import produto.Produto;
import usuario.Usuario;

public class SistemaPedido {
	
	private Pedido pedidoAtual;
	
	// CONSTRUTOR
	public SistemaPedido() {
		
	}
	
	// GETTERS
	public Pedido getPedidoAtual() {
		return pedidoAtual;
	}
	
	// PRIVADO
	private boolean temEstoque(Estoque estoque, Produto produto, int quantidade) {
	    if (estoque == null || produto == null) {
	        return false;
	    }

	    int disponivel = estoque.consultarQuantidade(produto.getIdProduto());
	    return disponivel >= quantidade;
	}
	
	// PUBLICOS
	public Pedido CriarPedido(Usuario usuario) {
		if(usuario == null) {
			return null;
		}
		Pedido pedido = new Pedido(usuario);
		return pedido;
	}
	
	
	public boolean verificarEstoque(Estoque estoque, ItemPedido itemPedidoAtual) {
        int disponivel = estoque.consultarQuantidade(
            itemPedidoAtual.getProduto().getIdProduto()
        );

        return disponivel >= itemPedidoAtual.getQuantidade();
    }
	
	
	public boolean adicionarProdutoPedido(Pedido pedidoAtual, Estoque estoque, ItemPedido itemPedidoAtual) {
		
		if(!verificarEstoque(estoque, itemPedidoAtual)) {
			return false;
		}
		
		pedidoAtual.adicionarItem(itemPedidoAtual);
		estoque.removerProduto(itemPedidoAtual.getProduto(),itemPedidoAtual.getQuantidade());
		return true;
		
	}
	
	public boolean removerProdutoPedido(Pedido pedidoAtual, Estoque estoque, ItemPedido itemPedidoAtual) {
		
		if(pedidoAtual.getItems().isEmpty() || pedidoAtual.getStatus() == StatusPedido.CANCELADO) {
			return false;
		}
		
		pedidoAtual.removerItem(itemPedidoAtual);
		estoque.adicionarProduto(itemPedidoAtual.getProduto(), itemPedidoAtual.getQuantidade());
		return true;
			
	}
	
	public ResultadoOperacaoPedido adicionarQuantidadeProdutoPedido(
            Pedido pedidoAtual,
            Estoque estoque,
            Produto produto,
            int quantidade
    ) {

        if (pedidoAtual == null || pedidoAtual.getStatus() != StatusPedido.ABERTO) {
            return ResultadoOperacaoPedido.PEDIDO_INVALIDO;
        }

        ItemPedido itemEncontrado = null;

        for (ItemPedido it : pedidoAtual.getItems()) {
            if (it.getProduto().getIdProduto() == produto.getIdProduto()) {
                itemEncontrado = it;
                break;
            }
        }

        if (itemEncontrado == null) {
            return ResultadoOperacaoPedido.PRODUTO_NAO_ESTA_NO_PEDIDO;
        }

        if (!temEstoque(estoque, produto, quantidade)) {
            return ResultadoOperacaoPedido.ESTOQUE_INSUFICIENTE;
        }

        itemEncontrado.adicionarQuantidade(quantidade);
        estoque.removerProduto(produto, quantidade);

        return ResultadoOperacaoPedido.SUCESSO;
    }
	
	public boolean removerQuantidadeProdutoPedido(Pedido pedidoaAtual, Estoque estoque, ItemPedido itemPedidoAtual, int quantidade) {
		if(pedidoAtual.getStatus() != StatusPedido.ABERTO) {
			return false;
		}
		
		boolean removeu = itemPedidoAtual.removerQuantidade(quantidade);
		if(!removeu) {
			return false;
		}
		
		estoque.adicionarProduto(itemPedidoAtual.getProduto(), quantidade);
		
		if(itemPedidoAtual.getQuantidade() == 0) {
			pedidoaAtual.removerItem(itemPedidoAtual);
		}
		return true;
	}
	
	public boolean finalizarPedido(Pedido pedidoAtual, Estoque estoque) {
		if(pedidoAtual.getItems() == null || pedidoAtual.getStatus() == StatusPedido.CANCELADO) {
			return false;
		}
		return pedidoAtual.finalizarPedido();
	}
	
	public boolean cancelarPedido(Pedido pedidoAtual, Estoque estoque) {
		
		if(pedidoAtual.getStatus() != StatusPedido.ABERTO) {
			return false;
		}
		
		for(ItemPedido it : pedidoAtual.getItems()) {
			estoque.adicionarProduto(it.getProduto(),it.getQuantidade());
		}
		return true;
	}
		
}

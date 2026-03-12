package orderFlowEngine;

import java.util.Scanner;

import pedido.ItemPedido;
import pedido.Pedido;
import pedido.ResultadoOperacaoPedido;
import pedido.SistemaPedido;
import produto.Estoque;
import produto.Produto;
import usuario.Usuario;


public class MenuUsuario {
	
	private SistemaPedido sistemaPedido;
	
	public MenuUsuario() {
	}
	
	public MenuUsuario(SistemaPedido sistemaPedido) {
		this.sistemaPedido = sistemaPedido;
	}

	// CRIAÇÃO DO MENU
	public void ExibirMenuUsuario(Usuario usuario, Estoque estoque, Scanner sc) {
		
		Pedido pedidoAtual = null;;
		int opMenuUsuario;
		
		do {
			System.out.println("╔══════════════════════════════════════════════════╗");
			System.out.println("║             BEM VINDO AO MENU USUÁRIO            ║");
			System.out.println("╚══════════════════════════════════════════════════╝");
			System.out.println("1 - Criar novo pedido");
			System.out.println("2 - Adicionar produto ao pedido");
			System.out.println("3 - Remover produto ao pedido");
			System.out.println("4 - Adicionar quantidade ao produto pedido");
			System.out.println("5 - Remover quantidade ao produto pedido");
			System.out.println("6 - Ver pedido ");
			System.out.println("7 - Finalizar Pedido");
			System.out.println("8 - Cancelar Pedido");
			System.out.println("0 - Voltar");
			System.out.print("Escolha as opções: ");
			opMenuUsuario = sc.nextInt();
			sc.nextLine();
			
			switch(opMenuUsuario) {
			
			case 1:
				
				if(pedidoAtual != null) {
					System.out.println("⚠ Já existe um pedido em andamento.");
					break;
				}
				
				sc.nextLine();
				pedidoAtual = sistemaPedido.CriarPedido(usuario);
				System.out.print("Quantos produtos você deseja comprar: ");
				System.out.println("");
				int qt = sc.nextInt();
				sc.nextLine();
				
				for(int i = 1; i <= qt; i++) {
					System.out.print("Nome do Produto: ");
					String nomeProduto = sc.nextLine();
					
					System.out.print("Quantidade do produto: ");
					int quantidadeProduto = sc.nextInt();
					sc.nextLine();
					
					Produto produto = estoque.buscarProdutoPorNome(nomeProduto);
					if(produto == null) {
						System.out.print("❌ Produto não encontrado no estoque.");
						i--;
						continue;
					}
					
					ItemPedido itemPedidoAtual = new ItemPedido(quantidadeProduto, produto);
					boolean pedidoSucesso = sistemaPedido.adicionarProdutoPedido(pedidoAtual, estoque, itemPedidoAtual);
					if(!pedidoSucesso) {
						System.out.print("⚠ Quantidade do produto em estoque insuficiente!");
						System.out.print("Chamando menu da loja...");
						return;
					}
					System.out.print("✅ Pedidos criados com sucesso!");
				}
				break;
			
			case 2:
				
				if(pedidoAtual == null) {
					System.out.println("❌ Nenhum pedido criado. Crie um pedido primeiro.");
			        break;
				}
				
				sc.nextLine();
				System.out.print("Nome do Produto que você deseja adicionar: ");
				String nomeProdutoAdicionar = sc.nextLine();
				System.out.print("Quantidade do produto: ");
				int quantidadeProduto = sc.nextInt();
				sc.nextLine();
				
				Produto produto = estoque.buscarProdutoPorNome(nomeProdutoAdicionar);
				if(produto == null) {
					System.out.print("❌ Produto não encontrado no estoque.");
					System.out.print("Chamando menu da loja...");
					return;
				}
				
				ItemPedido itemPedidoAtual = new ItemPedido(quantidadeProduto, produto);
				boolean pedidoSucesso = sistemaPedido.adicionarProdutoPedido(pedidoAtual, estoque, itemPedidoAtual);
				if(!pedidoSucesso) {
					System.out.print("⚠ Quantidade do produto em estoque insuficiente!");
					System.out.print("Chamando menu da loja...");
					return;
				}
				System.out.print("✅ Produto adicionado com sucesso");
				break;
				
			case 3:
				
				if(pedidoAtual == null) {
					System.out.println("❌ Nenhum pedido criado. Crie um pedido primeiro.");
			        break;
				}
				
				sc.nextLine();
				System.out.print("Nome do Produto que você deseja remover: ");
				String nomeProdutoRemover = sc.nextLine();

				ItemPedido itemParaRemover = null;
				
				for(ItemPedido it : pedidoAtual.getItems()) {
					if(it.getProduto().getNomeProduto().equalsIgnoreCase(nomeProdutoRemover)) {
						itemParaRemover = it;
						break;
					}
				}
				
				if(itemParaRemover == null) {
					System.out.println("❌ Produto não encontrado no pedido.");
			        break;
				}
				
				sistemaPedido.removerProdutoPedido(pedidoAtual, estoque, itemParaRemover);
				System.out.print("✅ Produto removido com sucesso");
				break;
				
			case 4:
				
				if(pedidoAtual == null) {
					System.out.println("❌ Nenhum pedido criado. Crie um pedido primeiro.");
			        break;
				}
				
				sc.nextLine();
				System.out.print("Nome do Produto que você deseja adicionar mais quantidade: ");
				String nomeProdutoAdicionarQuantidade = sc.nextLine();
				System.out.print("Quantidade que você deseja adicionar ao produdo "+ nomeProdutoAdicionarQuantidade + ": ");
				int quantidadeProdutoAdicionar = sc.nextInt();
				sc.nextLine();
				
				Produto produtoPedidoAtual = estoque.buscarProdutoPorNome(nomeProdutoAdicionarQuantidade);
				if(produtoPedidoAtual == null) {
					System.out.print("❌ Produto não encontrado no estoque.");
					System.out.print("Chamando menu da loja...");
					return;
				}
				
				ResultadoOperacaoPedido resultado = sistemaPedido.adicionarQuantidadeProdutoPedido(pedidoAtual, estoque, produtoPedidoAtual, quantidadeProdutoAdicionar);

                switch (resultado) {
                    case SUCESSO:
                        System.out.println("✅ Quantidade adicionada com sucesso!");
                        break;
                    case PRODUTO_NAO_ESTA_NO_PEDIDO:
                        System.out.println("❌ Produto não está no pedido.");
                        break;
                    case ESTOQUE_INSUFICIENTE:
                        System.out.println("⚠ Estoque insuficiente.");
                        break;
                    case PEDIDO_INVALIDO:
                        System.out.println("❌ Pedido inválido.");
                        break;
                }
                break;
				
			case 5:
				
				if(pedidoAtual == null) {
					System.out.println("❌ Nenhum pedido criado. Crie um pedido primeiro.");
			        break;
				}
				
				sc.nextLine();
				System.out.print("Nome do Produto que você deseja remover quantidade: ");
				String nomeProdutoRemoverQuantidade = sc.nextLine();
				System.out.print("Quantidade que você deseja remover ao produdo "+ nomeProdutoRemoverQuantidade + ": ");
				int quantidadeProdutoRemover = sc.nextInt();
				sc.nextLine();

				ItemPedido itemQuantidadeProdutoRemover = null;
				
				for(ItemPedido it : pedidoAtual.getItems()) {
					if(it.getProduto().getNomeProduto().equalsIgnoreCase(nomeProdutoRemoverQuantidade)) {
						itemQuantidadeProdutoRemover = it;
						break;
					}
				}
				
				if(itemQuantidadeProdutoRemover == null) {
					System.out.println("❌ Produto não encontrado no pedido.");
			        break;
				}
				
				boolean removeuQuantidadeProdutoPedido =  sistemaPedido.removerQuantidadeProdutoPedido(pedidoAtual, estoque, itemQuantidadeProdutoRemover, quantidadeProdutoRemover);
				
				if (!removeuQuantidadeProdutoPedido) {
                    System.out.println("⚠ Quantidade inválida.");
                } else {
                    System.out.println("✅ Quantidade removida.");
                }
                break;
				
			case 6:
				
				if (pedidoAtual == null) {
                    System.out.println("❌ Nenhum pedido criado.");
                    break;
                }
                pedidoAtual.exibirListaPedido();
                break;
                
			case 7:
			    if (pedidoAtual == null) {
			        System.out.println("❌ Nenhum pedido criado.");
			        break;
			    }

			    boolean finalizado = sistemaPedido.finalizarPedido(pedidoAtual, estoque);

			    if (!finalizado) {
			        System.out.println("⚠ Não foi possível finalizar o pedido.");
			    } else {
			        System.out.println("✅ Pedido finalizado com sucesso!");
			        pedidoAtual = null; // encerra o pedido atual
			    }
			    break;
			
			case 8:
			    if (pedidoAtual == null) {
			        System.out.println("❌ Nenhum pedido criado.");
			        break;
			    }

			    boolean cancelado = sistemaPedido.cancelarPedido(pedidoAtual, estoque);

			    if (!cancelado) {
			        System.out.println("⚠ Não foi possível cancelar o pedido.");
			    } else {
			        System.out.println("❌ Pedido cancelado com sucesso!");
			        pedidoAtual = null;
			    }
			    break;
			    
			case 0:
                System.out.println("🔙 Voltando...");
                break;
                
			default:
				System.out.println("❌ Opção inválida.");
			}
				
		} while (opMenuUsuario != 0);
		sc.close();
	}
}

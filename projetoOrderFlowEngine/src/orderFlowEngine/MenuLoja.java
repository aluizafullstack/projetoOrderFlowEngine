package orderFlowEngine;

import java.util.Scanner;

import produto.Estoque;
import produto.Produto;

public class MenuLoja {
	
	@SuppressWarnings("unused")
	private Estoque estoque;

	public MenuLoja() {
	}
	
	public MenuLoja(Estoque estoque) {
		this.estoque = estoque;
	}
	
	public void exibirMenuLoja(Estoque estoque, Scanner sc) {
		
		int opMenuLoja;
		do {
			System.out.println("╔══════════════════════════════════════════════════╗");
			System.out.println("║             BEM VINDO AO MENU DA LOJA            ║");
			System.out.println("╚══════════════════════════════════════════════════╝");
			System.out.println("1 - Criar produto");
			System.out.println("2 - Adicionar quantidade ao produto no estoque");
			System.out.println("3 - Ver estoque");
			System.out.println("0 - Voltar");
			System.out.print("Escolha as opções: ");
			opMenuLoja = sc.nextInt();
			sc.nextLine();
			
			switch (opMenuLoja) {
			
			case 1:
				
				System.out.print("Quantos produtos você deseja adicionar: ");
				System.out.println("");
				int qt = sc.nextInt();
				sc.nextLine();
				
				for(int i = 1; i <= qt; i++) {
					
					System.out.print("Nome do Produto: ");
					String nomeProduto = sc.nextLine();
					
					System.out.print("Preço unitário do produto: ");
					double preco = sc.nextDouble();
					
					
					System.out.print("Quantidade do produto: ");
					int quantidade = sc.nextInt();
					sc.nextLine();
					
					Produto produto = new Produto(nomeProduto, preco);
					estoque.adicionarProduto(produto, quantidade);
				}	
				System.out.print("✅ Produtos criados com sucesso!");
				break;
			
			case 2:
				
				System.out.print("Nome do Produto: ");
				String nomeProduto = sc.nextLine();
				Produto produto = estoque.buscarProdutoPorNome(nomeProduto);
					
				System.out.print("Quantidade do produto: ");
				int quantidadeAdicionar = sc.nextInt();
				sc.nextLine();
				
				if(produto == null) {
					System.out.print("Preço unitário do produto: ");
					double preco = sc.nextDouble();
					sc.nextLine();
					produto = new Produto(nomeProduto, preco);
				}
				
				estoque.adicionarProduto(produto, quantidadeAdicionar);
				System.out.println("✅ Produto atualizado no estoque.");
				
				break;
			case 3:
				estoque.exibirEstoque();
				break;
			
			default:
	            System.out.println("Opção inválida!");   
			}
		}while(opMenuLoja !=0);
		sc.close();
	}	
}

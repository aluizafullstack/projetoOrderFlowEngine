package orderFlowEngine;

import java.util.Scanner;

import pedido.SistemaPedido;
import produto.Estoque;
import usuario.Usuario;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Estoque estoque = new Estoque();
		SistemaPedido sistemaPedido = new SistemaPedido();
		MenuUsuario menuUsuario = new MenuUsuario(sistemaPedido);
		MenuLoja menuLoja = new MenuLoja(estoque); 
		Usuario usuario = null;
		
		// CRIAÇÃO DO MENU
		
		int opcaoPrincipal;
		
		do {
			System.out.println("╔══════════════════════════════════════════════════╗");
			System.out.println("║        BEM VINDO A LOJA ORDER FLOW ENGINE        ║");
			System.out.println("╚══════════════════════════════════════════════════╝");
			System.out.println("1 - Criar um Usuário");
			System.out.println("2 - Área do Cliente");
			System.out.println("3 - Área da loja");
			System.out.println("4 - Sair");
			System.out.print("Escolha as opções: ");
			opcaoPrincipal = sc.nextInt();
			sc.nextLine();
			
			
			switch (opcaoPrincipal) {
			
			case 1:
				System.out.println("Digite o seu nome: ");
				String nome = sc.next();
				System.out.println("Digite o seu e-mail: ");
				String email = sc.next();
				System.out.println("Digite o seu telefone: ");
				String telefone = sc.next();
				System.out.println("Digite o seu CPF: ");
				String cpf = sc.next();
				
				usuario = new Usuario(nome, email, telefone, cpf);
				System.out.println("Usuario criado com sucesso");
				break;
				
			case 2:
				if (usuario == null) {
					System.out.println("Nenhum usuario criado ainda");
				}
				else {
					System.out.println("Entrando na Área do Cliente");
					menuUsuario.ExibirMenuUsuario(usuario, estoque, sc);
				}
				break;
				
			case 3:
				System.out.println("Entrando na Área da loja");
				menuLoja.exibirMenuLoja(estoque, sc);
				break;
			case 4:
				System.out.println("Encerrando o sistema");
				break;
				
			default:
				System.out.println("Opção invalida");		
			}
		} while (opcaoPrincipal != 4);
		sc.close();
	}
}

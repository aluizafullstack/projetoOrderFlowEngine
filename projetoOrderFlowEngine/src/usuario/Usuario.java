package usuario;

import java.util.concurrent.ThreadLocalRandom;

public class Usuario {
	
	private String nome;
	private String email;
	private int idUsuario;
	private String telefone;
	private String cpf;
	
	// CONSTRUTOR
	
	public Usuario(String nome, String email, String telefone, String cpf) {
		this.nome = nome;
		setEmail(email);
		this.idUsuario = ThreadLocalRandom.current().nextInt(100, 1000);
		setTelefone(telefone);
		setCpf(cpf);
	}

	// SETTERS
	
	public boolean setEmail(String email) {
		if (!emailValido(email)) {
			return false;
		}
		this.email = email;
		return true;
	}
	
	public boolean setTelefone(String telefone) {
		if (!telefoneValido(telefone)) {
			return false;
		}
		this.telefone = telefone;
		return true;
	}
	
	public boolean setCpf(String cpf) {
		if (!cpfValido(cpf)) {
			return false;
		}
		this.cpf = cpf;
		return true;
	}
	
	// GETTERS
	
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCpf() {
		return cpf;
	}
	
	// PRIVADO

	private boolean emailValido(String email) {
		if (email != null && !email.isBlank() && email.matches("^[^@]+@[^@]+\\.[^@]+$")) {
			return true;
		}
		return false;	
	}
	
	private boolean telefoneValido(String telefone) {
		if(telefone != null && !telefone.isBlank() && telefone.matches("\\d{10,11}")) {
			return true;
		}
		return false;
	}
	
	private boolean cpfValido(String cpf) {
		if(cpf != null && !cpf.isBlank() && cpf.matches("\\d{11}")) {
			return true;
		}
		return false;
	}
	
	// PUBLICO
	
	public void dadosUsuario() {
		
		System.out.println("╔══════════════════════════════════════════════════╗");
		System.out.println("║                  DADOS DO USUARIO                ║");
		System.out.println("╚══════════════════════════════════════════════════╝");
		System.out.println("Nome: "+ nome);
		System.out.println("ID: "+ idUsuario);
		System.out.println("e-mail: "+ email);
		System.out.println("Telefone: "+ telefone);
		System.out.println("CPF: "+ cpf);
	}
}
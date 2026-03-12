<div align="center">

# 📦 `<OrderFlowEngine/>`

📦 Onde a lógica organiza o fluxo! Sistema de gerenciamento de pedidos e estoque em Java com foco em programação orientada a objetos, garantindo a integridade entre usuários, produtos e transações. Apresenta validações rigorosas, controle de saldo e processamento de estados. 100% Java puro, sem dependências. Aprenda encapsulamento, coleções e regras de negócio enquanto gerencia sua própria loja! 🛒

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-100%25-success?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen?style=for-the-badge)

### ⚙️ Precision in Every Order

*Gerenciador de fluxo logístico que demonstra Programação Orientada a Objetos de forma robusta*

[🎮 Como Executar](#-como-executar) • [💡 Conceitos](#-conceitos-poo-aplicados) • [🚀 Features](#-funcionalidades)

</div>

---

# 🧠 Sobre o Projeto

**`<OrderFlowEngine/>`** transforma a complexidade de um sistema de ERP em uma implementação prática e organizada. Cadastre usuários, manipule o estoque em tempo real e processe pedidos garantindo que os dados estejam sempre consistentes.

**Diferencial:**
Não é apenas um sistema de vendas — é uma demonstração prática de robustez e tratamento de dados. O nome (`<OrderFlowEngine/>`) destaca a engenharia por trás do fluxo de pedidos, tratando cada transação com precisão matemática.

### Ideal para

* ✅ Estudantes de Java e POO
* ✅ Portfólio de desenvolvedores Backend
* ✅ Demonstração de lógica de sistemas corporativos

---

# 🚀 Funcionalidades

## 👥 Gestão de Usuários

* Cadastro completo com validação de **E-mail, Telefone e CPF (RegEx)**
* Geração automática de **ID único** para cada cliente
* Proteção de integridade de dados via **encapsulamento**

## 🛡️ Controle de Estoque

* Adição e remoção inteligente de produtos com atualização de saldo
* Sistema de busca otimizado por **nome ou ID do produto**
* Prevenção de inconsistências (**não permite estoque negativo**)

## 🛒 Sistema de Pedidos

* Cálculo dinâmico de subtotais e valor total (**Preço x Quantidade**)
* Estados de pedido: `ABERTO`, `FINALIZADO` e `CANCELADO`
* Estorno automático de estoque em caso de remoção ou cancelamento

---

# 🧱 Estrutura do Projeto

```text
projetoOrderFlowEngine/
├── src/
│   ├── usuario/
│   │   └── Usuario.java
│   │
│   ├── produto/
│   │   ├── Produto.java
│   │   ├── Item.java
│   │   └── Estoque.java
│   │
│   ├── pedido/
│   │   ├── Pedido.java
│   │   ├── ItemPedido.java
│   │   ├── SistemaPedido.java
│   │   ├── StatusPedido.java
│   │   └── ResultadoOperacaoPedido.java
│   │
│   └── orderFlowEngine/
│       └── Main.java
```

---

# 🧠 Conceitos POO Aplicados

## ✅ Encapsulamento & Validação

```java
public boolean setCpf(String cpf) {
    if (!cpfValido(cpf)) return false;
    this.cpf = cpf;
    return true;
}
```

## ✅ Composição de Objetos

```java
private List<ItemPedido> items = new ArrayList<>();
```

## ✅ Uso de Enums

```java
public enum StatusPedido {
    ABERTO, FINALIZADO, CANCELADO;
}
```

## ✅ Regras de Negócio (Service Layer)

```java
if(!verificarEstoque(estoque, itemPedidoAtual)) {
    return false;
}
```

## ✅ Geração de Dados Dinâmicos

```java
this.idUsuario = ThreadLocalRandom.current().nextInt(100, 1000);
```

---

# 🖥️ Exemplo de Uso

```text
╔══════════════════════════════════════════════════╗
║        BEM VINDO A LOJA ORDER FLOW ENGINE        ║
╚══════════════════════════════════════════════════╝

👤 Usuário: Ana Luiza (ID: 482)
📧 E-mail: a.luiza.fullstack@gmail.com

╔══════════════════════════════════════════════════╗
║                  LISTA DE PEDIDOS                ║
╚══════════════════════════════════════════════════╝

Nome do produto: Teclado Mecânico | Preço: R$ 250,00 | Qtd: 1
Nome do produto: Mouse Gamer      | Preço: R$ 150,00 | Qtd: 2

💰 Valor Total do pedido: R$ 550,00
✅ Status: FINALIZADO
```

---

# ⚙️ Como Executar

## Pré-requisitos

* Java **JDK 11+**

## Passos

```bash
# Clone o repositório
git clone https://github.com/aluizafullstack/projetoOrderFlowEngine.git

# Entre na pasta
cd projetoOrderFlowEngine

# Compile os arquivos
javac src/usuario/*.java src/produto/*.java src/pedido/*.java src/orderFlowEngine/*.java

# Execute o sistema
java -cp src orderFlowEngine.Main
```

---

# 🔧 Tecnologias

* ✔ **Java 17+**
* ✔ **Programação Orientada a Objetos**
* ✔ **RegEx para validações**
* ✔ **Java Collections**

---

# 📌 Melhorias Futuras

* [ ] Persistência de dados (`.txt` ou `.json`)
* [ ] Criar API com **Spring Boot**
* [ ] Diferentes tipos de produtos (Digitais vs Físicos)
* [ ] Testes unitários com **JUnit 5**

---

# 🎓 Aprendizados

## 💡 O que aprendi

* Validações complexas com **RegEx**
* Separação entre **Pedido e SistemaPedido**
* Sincronização entre **estoque e pedidos**

## 🚧 Desafios

* Orquestração entre **remoção de estoque e cancelamento**
* Melhorar a **experiência visual no console**

---

# 👩‍💻 Sobre

Sou **Ana Luiza**, em transição de carreira para tecnologia.
Este projeto demonstra minha evolução no aprendizado de **arquitetura Java** e meu compromisso em criar soluções para problemas reais de fluxo logístico.

### 🎯 Filosofia

> "Aprender construindo. Destacar-se sendo criativa."

---

# 📫 Contato

📧 Email: **[a.luiza.fullstack@gmail.com](mailto:a.luiza.fullstack@gmail.com)**

💼 LinkedIn:
https://linkedin.com/in/analuizafullstack

🐙 GitHub:
https://github.com/aluizafullstack

---

**Desenvolvido com ☕ Java e 📦 foco em processos**

### `<OrderFlowEngine/>`

*Precision in Every Order*

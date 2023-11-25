import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cliente {
	private String cpf;
	private String nome;
	private String username;
	private Integer idade;

	public Cliente(String cpf, String nome, String username, Integer idade) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.username = username;
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

    public static boolean escolherOpcao(Usuario usuarioLogado, List<Empresa> empresas, List<Produto> produtos, List<Cliente> clientes, List<Produto> carrinho, List<Usuario> usuarios, List<Venda> vendas) {
		System.out.println("1 - Relizar Compras");
		System.out.println("2 - Ver Compras");
		System.out.println("0 - Deslogar");
		
		Scanner sc = new Scanner(System.in);
		String escolha = sc.next();
		
		switch (escolha) {
			case "1": {
				boolean continuarLogado = realizarCompras(usuarioLogado, empresas, produtos, carrinho, clientes, vendas);
				
				if (!continuarLogado) return false;

				System.out.println("Escolha uma opção");
				return true;				
			}
			case "2": {
				boolean continuarLogado = verCompras(usuarioLogado, vendas);
				
				if (!continuarLogado) return false;

				System.out.println("Escolha uma opção");
				return true;
			}
			default: {
				return false;
			}
		}
    }

	public static boolean verCompras(Usuario usuarioLogado, List<Venda> vendas) {
		System.out.println();
		System.out.println("************************************************************");

		if (vendas.stream().anyMatch(venda -> venda.getCliente().getUsername().equals(usuarioLogado.getUsername()))) {

			System.out.println("COMPRAS EFETUADAS");
			vendas.stream().forEach(venda -> {
				if (venda.getCliente().getUsername().equals(usuarioLogado.getUsername())) {
					System.out.println("************************************************************");
					System.out.println("Compra de código: " + venda.getCódigo() + " na empresa "
							+ venda.getEmpresa().getNome() + ": ");
					venda.getItens().stream().forEach(x -> {
						System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
					});
					System.out.println("Total: R$" + venda.getValor());
					System.out.println("************************************************************");
				}
			});
		}
		else {
			System.out.println("NENHUM COMPRA FOI REALIZADA");
		}
		
		System.out.println("Deseja continuar realizando operações ?\nSe sim, digite 1");

		Scanner sc = new Scanner(System.in);
		boolean continuarLogado = false;

		if (sc.next().equals("1")) continuarLogado = true;

		return continuarLogado;
	}

	public static boolean realizarCompras(Usuario usuarioLogado, List<Empresa> empresas, List<Produto> produtos, List<Produto> carrinho, List<Cliente> clientes, List<Venda> vendas) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Para realizar uma compra, escolha a empresa onde deseja comprar: ");
		empresas.stream().forEach(x -> {
			System.out.println(x.getId() + " - " + x.getNome());
		});

		String empresaIdSelecionada = sc.next();

		boolean permissao = false;
		permissao = empresas.stream()
			.anyMatch(empresa -> empresa.getId().toString().equals(empresaIdSelecionada));

		if (!permissao) {
			System.out.println("\nEmpresa não cadastrada\n");
			realizarCompras(usuarioLogado, empresas, produtos, carrinho, clientes, vendas);
		};

		Integer escolhaEmpresa = Integer.parseInt(empresaIdSelecionada);
		
		Integer escolhaProduto = -1;
		do {
			System.out.println("Escolha os seus produtos: ");
			produtos.stream().forEach(x -> {
				if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
					System.out.println(x.getId() + " - " + x.getNome());
				}
			});
			System.out.println("0 - Finalizar compra");

			String produtoEmpresa = sc.next();

			escolhaProduto = Integer.parseInt(produtoEmpresa);

			for (Produto produtoSearch : produtos) {
				if (produtoSearch.getId().equals(escolhaProduto) && produtoSearch.getEmpresa().getId().equals(escolhaEmpresa))
					carrinho.add(produtoSearch);
			}
		} while (escolhaProduto != 0);

		if (carrinho.size() > 0) {
			System.out.println("************************************************************");
			System.out.println("Resumo da compra: ");
			carrinho.stream().forEach(x -> {
				if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
					System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
				}
			});
			Empresa empresaEscolhida = empresas.stream().filter(x -> x.getId().equals(escolhaEmpresa))
					.collect(Collectors.toList()).get(0);
			Cliente clienteLogado = clientes.stream()
					.filter(x -> x.getUsername().equals(usuarioLogado.getUsername()))
					.collect(Collectors.toList()).get(0);
			Venda venda = Main.criarVenda(carrinho, empresaEscolhida, clienteLogado, vendas);
			System.out.println("Total: R$" + venda.getValor());
			System.out.println("************************************************************");
			carrinho.clear();
		}

		boolean continuarLogado = false;

		System.out.println("Deseja continuar realizando operações ?\nSe sim, digite 1");

		if (sc.next().equals("1")) continuarLogado = true;

		return continuarLogado;
	}
}

import java.util.List;
import java.util.Scanner;

public class Empresa {
	private Integer id;
	private String nome;
	private String cnpj;
	private Double taxa;
	private Double saldo;

	public Empresa() {
		super();
	}

	public Empresa(Integer id, String nome, String cnpj, Double taxa, Double saldo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.taxa = taxa;
		this.saldo = saldo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public static boolean listarProdutos(Usuario usuarioLogado, List<Produto> produtos, List<Empresa> empresas) {
		if (usuarioLogado.getCliente() == null && usuarioLogado.getEmpresa() == null) {
			System.out.println();
			System.out.println("************************************************************");
			System.out.println("PRODUTOS");
			produtos.stream().forEach(produto -> {
				System.out.println("************************************************************");
				System.out.println("Código: " + produto.getId());
				System.out.println("Produto: " + produto.getNome());
				System.out.println("Quantidade em estoque: " + produto.getQtdEstoque());
				System.out.println("Valor: R$" + produto.getPreco() + "\n");								
			});

			System.out.println("Deseja continuar realizando operações ?\nSe sim, digite 1");
		}
		else {
			System.out.println();
			System.out.println("************************************************************");
			System.out.println("MEUS PRODUTOS");
			produtos.stream().forEach(produto -> {
				if (produto.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
					System.out.println("************************************************************");
					System.out.println("Código: " + produto.getId());
					System.out.println("Produto: " + produto.getNome());
					System.out.println("Quantidade em estoque: " + produto.getQtdEstoque());
					System.out.println("Valor: R$" + produto.getPreco());								
					System.out.println("************************************************************");
				}

			});
			System.out.println("Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
			System.out.println("************************************************************\n");

			System.out.println("Deseja continuar realizando operações ?\nSe sim, digite 1");
		}

		Scanner sc = new Scanner(System.in);
		boolean continuarLogado = false;

		if (sc.next().equals("1")) continuarLogado = true;

		return continuarLogado;
	}

	public static boolean listarVendas(Usuario usuarioLogado, List<Venda> vendas) {
		if (usuarioLogado.getCliente() == null && usuarioLogado.getEmpresa() == null) {
			System.out.println();
			System.out.println("************************************************************");
			if (vendas.size() > 0) {
				System.out.println("VENDAS EFETUADAS");
			}
			else {
				System.out.println("NENHUMA VENDA EFETUADA");
			}
			vendas.stream().forEach(venda -> {
				System.out.println("************************************************************");
				System.out.println("Venda de código: " + venda.getCódigo() + " no CPF "
						+ venda.getCliente().getCpf() + ": ");
				venda.getItens().stream().forEach(x -> {
					System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
				});
				System.out.println("Total Venda: R$" + venda.getValor());
				System.out.println("Total Taxa a ser paga: R$" + venda.getComissaoSistema());
				System.out.println("Total Líquido para empresa: R$"
						+ (venda.getValor() - venda.getComissaoSistema()));
				System.out.println("************************************************************");
			});
		}
		else {
			System.out.println();
			System.out.println("************************************************************");
			System.out.println("VENDAS EFETUADAS");
			vendas.stream().forEach(venda -> {
				if (venda.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
					System.out.println("************************************************************");
					System.out.println("Venda de código: " + venda.getCódigo() + " no CPF "
							+ venda.getCliente().getCpf() + ": ");
					venda.getItens().stream().forEach(x -> {
						System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
					});
					System.out.println("Total Venda: R$" + venda.getValor());
					System.out.println("Total Taxa a ser paga: R$" + venda.getComissaoSistema());
					System.out.println("Total Líquido para empresa: R$"
							+ (venda.getValor() - venda.getComissaoSistema()));
					System.out.println("************************************************************");
				}
			});
			System.out.println("Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
			System.out.println("************************************************************");
		}

		System.out.println("Deseja continuar realizando operações ?\nSe sim, digite 1");

		Scanner sc = new Scanner(System.in);
		boolean continuarLogado = false;

		if (sc.next().equals("1")) continuarLogado = true;

		return continuarLogado;
	}

	public static boolean escolherOpcao(Usuario usuarioLogado, List<Produto> produtos, List<Venda> vendas, List<Empresa> empresas) {
		System.out.println("1 - Listar vendas");
		System.out.println("2 - Ver produtos");
		System.out.println("3 - Listar empresas");
		System.out.println("0 - Deslogar");

		Scanner sc = new Scanner(System.in);
		String escolha = sc.next();

		switch (escolha) {
			case "1": {
				boolean continuarLogado = Empresa.listarVendas(usuarioLogado, vendas);
				
				if (!continuarLogado) return false;

				System.out.println("Escolha uma opção");
				return true;
			}
			case "2": {
				boolean continuarLogado = Empresa.listarProdutos(usuarioLogado, produtos, empresas);

				if (!continuarLogado) return false;

				System.out.println("Escolha uma opção");
				return true;
			}
			case "3": {
				boolean continuarLogado = false;

				if (usuarioLogado.getCliente() == null && usuarioLogado.getEmpresa() == null) {
					continuarLogado = Empresa.listarEmpresas(empresas);
				}
				else {
					System.out.println("\nSomente administradores tem acesso\n");
					System.out.println("Deseja continuar realizando operações ?\nSe sim, digite 1");

					if (!sc.next().equals("1")) continuarLogado = false;

					return true;
				}

				if (!continuarLogado) return false;

				System.out.println("Escolha uma opção");
				return true;
			}
			default: {
				return false;
			}
		}
	}

	private static boolean listarEmpresas(List<Empresa> empresas) {
		empresas.stream().forEach(empresa -> {
			System.out.println("************************************************************");
			System.out.println("Nome: " + empresa.getNome());
			System.out.println("CNPJ: " + empresa.getCnpj());
			System.out.println("Taxa: " + empresa.getTaxa());
			System.out.println("Saldo: " + empresa.getSaldo());
			System.out.println("************************************************************");
		});

		Scanner sc = new Scanner(System.in);
		boolean continuarLogado = false;

		System.out.println("Deseja continuar realizando operações ?\nSe sim, digite 1");

		if (sc.next().equals("1")) continuarLogado = true;

		return continuarLogado;
	}
}
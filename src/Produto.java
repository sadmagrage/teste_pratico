
public class Produto {
	private Integer id;
	private String nome;
	private Integer qtdEstoque;
	private Double preco;
	private Empresa empresa;

	public Produto(Integer id,String nome, Integer qtdEstoque, Double preco, Empresa empresa) {
		super();
		this.id = id;
		this.nome = nome;
		this.qtdEstoque = qtdEstoque;
		this.preco = preco;
		this.empresa = empresa;
	}

	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
